package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.offers.OffersCollectionImportDTO;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.SellerService;
import softuni.exam.util.interfaces.ValidationUtil;
import softuni.exam.util.interfaces.XMLTool;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OfferServiceImpl implements OfferService {
    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final CarService carService;
    private final SellerService sellerService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XMLTool xmlTool;
    private final StringBuilder stringBuilder;

    public OfferServiceImpl(OfferRepository offerRepository, CarService carService, SellerService sellerService, ModelMapper modelMapper, ValidationUtil validator, XMLTool xmlTool, StringBuilder stringBuilder) {
        this.offerRepository = offerRepository;
        this.carService = carService;
        this.sellerService = sellerService;
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

        this.xmlTool.fromXML(OFFERS_FILE_PATH, OffersCollectionImportDTO.class)
                .getOffers()
                .forEach(offerDTO -> {
                    if (this.validator.isValid(offerDTO)) {
                        Offer offer = this.modelMapper.map(offerDTO, Offer.class);

                        Seller seller = this.sellerService.findSellerBYId(offerDTO.getSeller().getId());
                        Car car = this.carService.findCarById(offerDTO.getCar().getId());

                        try {
                            offer.setCar(car);
                            offer.setSeller(seller);
                            offer.setPictures(car.getPictures());

                            Offer savedOffer = this.offerRepository.save(offer);

                            this.stringBuilder
                                    .append(String.format("Successfully import offer %s - %s",
                                            savedOffer.getAddedOn(), savedOffer.getHasGoldStatus()))
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

    private void appendInvalidOffer() {
        this.stringBuilder.append("Invalid Offer").append(System.lineSeparator());
    }
}
