package hiberspring.services.impl;

import com.google.gson.Gson;
import hiberspring.models.dtos.TownImportDTO;
import hiberspring.models.entities.Town;
import hiberspring.repositories.TownRepository;
import hiberspring.services.TownService;
import hiberspring.utils.interfaces.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TownServiceImpl implements TownService {
    private static final String TOWNS_FILE_PATH = "src/main/resources/files/towns.json";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final Gson gson;
    private final StringBuilder stringBuilder;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validator, Gson gson, StringBuilder stringBuilder) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        if (this.townsAreImported()) {
            return null;
        }
        Arrays.stream(this.gson.fromJson(this.readTownsJsonFile(), TownImportDTO[].class))
                .forEach(townDTO -> {
                    if (this.validator.isValid(townDTO)) {
                        Town town = this.modelMapper.map(townDTO, Town.class);

                        this.townRepository.save(town);

                        this.stringBuilder
                                .append(String.format("Successfully imported Town %s.", town.getName()))
                                .append(System.lineSeparator());
                    } else {
                        this.stringBuilder.append("Invalid Town").append(System.lineSeparator());
                    }
                });
        return this.stringBuilder.toString().trim();
    }

    @Override
    public Town findTownByName(String town) {
        return this.townRepository.findByName(town);
    }
}
