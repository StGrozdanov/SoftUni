package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.offers.OfferCollectionImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.OfferService;
import softuni.exam.util.interfaces.ValidationUtil;
import softuni.exam.util.interfaces.XMLTool;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class OfferServiceImpl implements OfferService {
    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final ApartmentService apartmentService;
    private final AgentService agentService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XMLTool xmlTool;
    private final StringBuilder stringBuilder;

    public OfferServiceImpl(OfferRepository offerRepository, ApartmentService apartmentService, AgentService agentService, ModelMapper modelMapper, ValidationUtil validator, XMLTool xmlTool, StringBuilder stringBuilder) {
        this.offerRepository = offerRepository;
        this.apartmentService = apartmentService;
        this.agentService = agentService;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlTool = xmlTool;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        if (this.areImported()) {
            return null;
        }
        this.xmlTool
                .fromXML(OFFERS_FILE_PATH, OfferCollectionImportDTO.class)
                .getOffers()
                .forEach(offerDTO -> {
                    if (this.validator.isValid(offerDTO)) {
                        Offer offer = this.modelMapper.map(offerDTO, Offer.class);

                        LocalDate date = LocalDate.parse(offerDTO.getPublishedOn(),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        offer.setPublishedOn(date);

                        try {
                            Agent agent = this.agentService.findAgentByName(offerDTO.getAgent().getName());
                            Apartment apartment = this.apartmentService.findApartmentById(offerDTO.getApartment().getId());

                            offer.setAgent(agent);
                            offer.setApartment(apartment);

                            this.offerRepository.save(offer);

                            this.stringBuilder
                                    .append(String.format("Successfully imported offer %.2f",
                                            offer.getPrice()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
                            appendInvalidOffer();
                        }

                    } else {
                        appendInvalidOffer();
                    }
                });

        return this.stringBuilder.toString().trim();
    }

    @Override
    public String exportOffers() {
        this.stringBuilder.setLength(0);

        this.offerRepository
                .findAllByApartmentApartmentTypeOrderByApartmentAreaDescPrice(ApartmentType.THREE_ROOMS)
                .forEach(offer -> this.stringBuilder.append(offer).append(System.lineSeparator()));

        return this.stringBuilder.toString().trim();
    }

    private void appendInvalidOffer() {
        this.stringBuilder.append("Invalid Offer").append(System.lineSeparator());
    }
}
