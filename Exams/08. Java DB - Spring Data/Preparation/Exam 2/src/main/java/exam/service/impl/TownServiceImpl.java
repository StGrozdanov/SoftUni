package exam.service.impl;

import exam.model.dtos.towns.TownCollectionImportDTO;
import exam.model.entities.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.interfaces.ValidationUtil;
import exam.util.interfaces.XMLTool;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {
    private static final String TOWNS_FILE_PATH = "src/main/resources/files/xml/towns.xml";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XMLTool xmlTool;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validator, XMLTool xmlTool) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlTool = xmlTool;
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
    public String importTowns() throws JAXBException, IOException {
        if (!this.areImported()) {
            StringBuilder stringBuilder = new StringBuilder();

            this.xmlTool
                    .fromXML(TOWNS_FILE_PATH, TownCollectionImportDTO.class)
                    .getTowns()
                    .forEach(townDTO -> {
                        if (!this.validator.isValid(townDTO)) {
                            appendInvalidTown(stringBuilder);
                        } else {
                            Town town = this.modelMapper.map(townDTO, Town.class);
                            try {
                                this.townRepository.save(town);

                                stringBuilder
                                        .append(String.format("Successfully imported Town %s", town.getName()))
                                        .append(System.lineSeparator());
                            } catch (Exception e) {
                                appendInvalidTown(stringBuilder);
                            }
                        }
                    });
            return stringBuilder.toString().trim();
        }
        return null;
    }

    @Override
    public Town findTownByName(String townName) {
        return this.townRepository.findByName(townName);
    }

    private void appendInvalidTown(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid town").append(System.lineSeparator());
    }
}
