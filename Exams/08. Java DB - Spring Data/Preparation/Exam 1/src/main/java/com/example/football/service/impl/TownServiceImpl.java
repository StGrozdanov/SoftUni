package com.example.football.service.impl;

import com.example.football.models.dto.TownImportDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.validator.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


@Service
public class TownServiceImpl implements TownService {
    private static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final Gson gson;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validator, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        if (!this.areImported()) {
            Arrays.stream(this.gson.fromJson(this.readTownsFileContent(), TownImportDto[].class))
                    .forEach(townDto -> {
                        if (!this.validator.isValid(townDto)) {
                            this.appendInvalidTown(stringBuilder);
                        } else {
                            Town town = this.modelMapper.map(townDto, Town.class);
                            try {
                                this.townRepository.save(town);
                                stringBuilder
                                        .append(String.format("Successfully imported Town %s - %d",
                                                town.getName(), town.getPopulation()))
                                        .append(System.lineSeparator());

                            } catch (Exception e) {
                                this.appendInvalidTown(stringBuilder);
                            }
                        }
                    });
            return stringBuilder.toString().trim();
        }

        return null;
    }

    @Override
    public Town getTownByName(String name) {
        return this.townRepository.findTownByNameEquals(name).orElse(null);
    }

    private void appendInvalidTown(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid Town").append(System.lineSeparator());
    }
}
