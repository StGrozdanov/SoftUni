package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.PictureImportDTO;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.interfaces.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURES_FILE_PATH = "src/main/resources/files/json/pictures.json";

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final StringBuilder stringBuilder;
    private final ValidationUtil validator;
    private final CarService carService;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, Gson gson, StringBuilder stringBuilder, ValidationUtil validator, CarService carService) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.stringBuilder = stringBuilder;
        this.validator = validator;
        this.carService = carService;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        if (this.areImported()) {
            return null;
        }

        Arrays.stream(this.gson.fromJson(this.readPicturesFromFile(), PictureImportDTO[].class))
                .forEach(pictureDTO -> {
                    if (this.validator.isValid(pictureDTO)) {
                        Picture picture = this.modelMapper.map(pictureDTO, Picture.class);
                        Car car = this.carService.findCarById(pictureDTO.getCar());

                        try {
                            picture.setCar(car);
                            this.pictureRepository.save(picture);

                            this.stringBuilder
                                    .append(String.format("Successfully import picture - %s",
                                            pictureDTO.getName()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
                            appendInvalidPicture();
                        }

                    } else {
                        appendInvalidPicture();
                    }
                });

        return this.stringBuilder.toString().trim();
    }

    private void appendInvalidPicture() {
        this.stringBuilder.append("Invalid Picture").append(System.lineSeparator());
    }
}
