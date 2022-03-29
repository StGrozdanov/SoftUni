package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.planes.PlaneCollectionImportDTO;
import softuni.exam.models.dtos.planes.PlaneDTO;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.interfaces.ValidationUtil;
import softuni.exam.util.interfaces.XMLTool;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PlaneServiceImpl implements PlaneService {
    private static final String PLANES_FILE_PATH = "src/main/resources/files/xml/planes.xml";

    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XMLTool xmlTool;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, ValidationUtil validator, XMLTool xmlTool) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlTool = xmlTool;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PLANES_FILE_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException, IOException {
        if (this.areImported()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();

        this.xmlTool
                .fromXML(PLANES_FILE_PATH, PlaneCollectionImportDTO.class)
                .getPlanes()
                .forEach(planeDTO -> {
                    if (this.validator.isValid(planeDTO)) {
                        Plane plane = this.modelMapper.map(planeDTO, Plane.class);

                        try {
                            this.planeRepository.save(plane);
                        } catch (DataIntegrityViolationException e) {
                            appendInvalidPlane(stringBuilder);
                        }

                        stringBuilder
                                .append(String.format("Successfully imported Plane %s", plane.getRegisterNumber()))
                                .append(System.lineSeparator());
                    } else {
                        appendInvalidPlane(stringBuilder);
                    }
                });

        return stringBuilder.toString().trim();
    }

    @Override
    public Plane findPlaneByRegisterNumber(String registerNumber) {
        return this.planeRepository.findByRegisterNumber(registerNumber);
    }

    private void appendInvalidPlane(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid Plane").append(System.lineSeparator());
    }
}
