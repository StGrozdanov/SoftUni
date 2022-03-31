package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.CarImportDTO;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.interfaces.ValidationUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class CarServiceImpl implements CarService {
    private final static String CARS_FILE_PATH = "src/main/resources/files/json/cars.json";

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final StringBuilder stringBuilder;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, StringBuilder stringBuilder) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        if (this.areImported()) {
            return null;
        }

        Arrays.stream(this.gson.fromJson(this.readCarsFileContent(), CarImportDTO[].class))
                .forEach(carDTO -> {
                    if (this.validator.isValid(carDTO)) {
                        Car car = this.modelMapper.map(carDTO, Car.class);

                        LocalDate registerDate = LocalDate.parse(carDTO.getRegisteredOn(),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        car.setRegisteredOn(registerDate);

                        this.carRepository.save(car);

                        this.stringBuilder
                                .append(String.format("Successfully imported car - %s - %s",
                                        car.getMake(), car.getModel()))
                                .append(System.lineSeparator());
                    } else {
                        appendInvalidCar();
                    }
                });

        return this.stringBuilder.toString().trim();
    }

    private void appendInvalidCar() {
        this.stringBuilder.append("Invalid car").append(System.lineSeparator());
    }

    @Override
    @Transactional
    public String getCarsOrderByPicturesCountThenByMake() {
        this.stringBuilder.setLength(0);

        this.carRepository
                .findAllByPicturesCountDescAndMakeAsc()
                .forEach(car -> this.stringBuilder.append(car).append(System.lineSeparator()));

        return this.stringBuilder.toString().trim();
    }

    @Override
    public Car findCarById(Long car) {
        return this.carRepository.findById(car).orElse(null);
    }
}
