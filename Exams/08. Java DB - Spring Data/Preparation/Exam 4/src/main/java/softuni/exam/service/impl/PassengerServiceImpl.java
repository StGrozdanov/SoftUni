package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.PassengerImportDTO;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.interfaces.ValidationUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PassengerServiceImpl implements PassengerService {
    private static final String PASSENGER_FILE_PATH = "src/main/resources/files/json/passengers.json";

    private final PassengerRepository passengerRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final Gson gson;
    private final StringBuilder stringBuilder;

    public PassengerServiceImpl(PassengerRepository passengerRepository, TownService townService, ModelMapper modelMapper, ValidationUtil validator, Gson gson, StringBuilder stringBuilder) {
        this.passengerRepository = passengerRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PASSENGER_FILE_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        if (this.areImported()) {
            return null;
        }
        Arrays.stream(this.gson.fromJson(this.readPassengersFileContent(), PassengerImportDTO[].class))
                .forEach(passengerDTO -> {
                    if (this.validator.isValid(passengerDTO)) {
                        Town town = this.townService.findTownByTownName(passengerDTO.getTown());
                        Passenger passenger = this.modelMapper.map(passengerDTO, Passenger.class);

                        try {
                            passenger.setTown(town);

                            this.passengerRepository.save(passenger);

                            stringBuilder
                                    .append(String.format("Successfully imported Passenger %s - %s",
                                            passenger.getLastName(), passenger.getEmail()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException e) {
                            appendInvalidPassenger();
                        }
                    } else {
                        appendInvalidPassenger();
                    }
                });

        return stringBuilder.toString().trim();
    }

    @Override
    @Transactional
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        stringBuilder.setLength(0);

        this.passengerRepository
                .findAllOrderedByTicketsCountDescAndEmailAsc()
                .forEach(passenger -> stringBuilder.append(passenger).append(System.lineSeparator()));

        return stringBuilder.toString().trim();
    }

    @Override
    public Passenger findPassengerByEmail(String email) {
        return this.passengerRepository.findByEmail(email);
    }

    private void appendInvalidPassenger() {
        stringBuilder.append("Invalid Passenger").append(System.lineSeparator());
    }
}
