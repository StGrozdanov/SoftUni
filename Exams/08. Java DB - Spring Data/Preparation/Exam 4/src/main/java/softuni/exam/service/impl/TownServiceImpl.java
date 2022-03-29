package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.TownImportDTO;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.interfaces.ValidationUtil;

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
        if (this.areImported()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(this.gson.fromJson(this.readTownsFileContent(), TownImportDTO[].class))
                .forEach(townDTO -> {
                    if (this.validator.isValid(townDTO)) {
                        Town town = this.modelMapper.map(townDTO, Town.class);

                        try {
                            this.townRepository.save(town);
                        } catch (DataIntegrityViolationException e) {
                            appendInvalidTown(stringBuilder);
                        }

                        stringBuilder
                                .append(String.format("Successfully imported Town %s - %d",
                                town.getName(), town.getPopulation()))
                                .append(System.lineSeparator());
                    } else {
                        appendInvalidTown(stringBuilder);
                    }
                });
        return stringBuilder.toString().trim();
    }

    @Override
    public Town findTownByTownName(String townName) {
        return this.townRepository.findByName(townName);
    }

    private void appendInvalidTown(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid Town").append(System.lineSeparator());
    }
}
