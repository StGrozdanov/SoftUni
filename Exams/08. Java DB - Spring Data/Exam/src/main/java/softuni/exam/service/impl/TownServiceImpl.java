package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownImportDTO;
import softuni.exam.models.entity.Town;
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
    private final Gson gson;
    private final ValidationUtil validator;
    private final StringBuilder stringBuilder;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, StringBuilder stringBuilder) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.stringBuilder = stringBuilder;
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
        Arrays.stream(this.gson.fromJson(this.readTownsFileContent(), TownImportDTO[].class))
                .forEach(townDTO -> {
                    if (this.validator.isValid(townDTO)) {
                        Town town = this.modelMapper.map(townDTO, Town.class);

                        try {
                            this.townRepository.save(town);

                            this.stringBuilder
                                    .append(String.format("Successfully imported town %s - %d",
                                            townDTO.getTownName(), townDTO.getPopulation()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException e) {
                            //town name is unique, so if we try to add the same name twice it would return this error
                            //without the need of additional repository load with boolean exists method
                            appendInvalidTown();
                        }
                    } else {
                        appendInvalidTown();
                    }
                });
        return this.stringBuilder.toString().trim();
    }

    @Override
    public Town findTownByName(String townName) {
        return this.townRepository.findByTownName(townName);
    }

    private void appendInvalidTown() {
        this.stringBuilder.append("Invalid Town").append(System.lineSeparator());
    }
}
