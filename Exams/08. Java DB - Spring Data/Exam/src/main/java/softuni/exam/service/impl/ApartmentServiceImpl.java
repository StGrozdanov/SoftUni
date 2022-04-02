package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.apartments.ApartmentCollectionImportDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.TownService;
import softuni.exam.util.interfaces.ValidationUtil;
import softuni.exam.util.interfaces.XMLTool;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private static final String APARTMENTS_FILE_PATH = "src/main/resources/files/xml/apartments.xml";

    private final ApartmentRepository apartmentRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final XMLTool xmlTool;
    private final ValidationUtil validator;
    private final StringBuilder stringBuilder;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownService townService, ModelMapper modelMapper, XMLTool xmlTool, ValidationUtil validator, StringBuilder stringBuilder) {
        this.apartmentRepository = apartmentRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.xmlTool = xmlTool;
        this.validator = validator;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of(APARTMENTS_FILE_PATH));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        if (this.areImported()) {
            return null;
        }
        this.xmlTool
                .fromXML(APARTMENTS_FILE_PATH, ApartmentCollectionImportDTO.class)
                .getApartments()
                .forEach(apartmentDTO -> {
                    if (this.validator.isValid(apartmentDTO)) {
                        if (this.apartmentRepository
                                .existsByTownTownNameAndArea(apartmentDTO.getTown(), apartmentDTO.getArea())) {
                            appendInvalidApartment();
                            return;
                        }
                        Apartment apartment = this.modelMapper.map(apartmentDTO, Apartment.class);
                        String apartmentTypeAsString = apartmentDTO.getApartmentType().toUpperCase();

                        try {
                            ApartmentType apartmentType = ApartmentType.valueOf(apartmentTypeAsString);
                            apartment.setApartmentType(apartmentType);

                            Town town = this.townService.findTownByName(apartmentDTO.getTown());
                            apartment.setTown(town);

                            this.apartmentRepository.save(apartment);

                            this.stringBuilder
                                    .append(String.format("Successfully imported apartment %s - %.2f",
                                            apartmentDTO.getApartmentType(), apartmentDTO.getArea()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
                            appendInvalidApartment();
                        }
                    } else {
                        appendInvalidApartment();
                    }
                });
        return this.stringBuilder.toString().trim();
    }

    @Override
    public Apartment findApartmentById(Long id) {
        return this.apartmentRepository.findById(id).orElse(null);
    }

    private void appendInvalidApartment() {
        this.stringBuilder.append("Invalid Apartment").append(System.lineSeparator());
    }
}
