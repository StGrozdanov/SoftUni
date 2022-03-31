package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.sellers.SellerCollectionImportDTO;
import softuni.exam.models.entities.Rating;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.interfaces.ValidationUtil;
import softuni.exam.util.interfaces.XMLTool;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerServiceImpl implements SellerService {
    private static final String SELLERS_FILE_PATH = "src/main/resources/files/xml/sellers.xml";

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XMLTool xmlTool;
    private final StringBuilder stringBuilder;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, ValidationUtil validator, XMLTool xmlTool, StringBuilder stringBuilder) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlTool = xmlTool;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        if (this.areImported()) {
            return null;
        }

        this.xmlTool.fromXML(SELLERS_FILE_PATH, SellerCollectionImportDTO.class)
                .getSellers()
                .forEach(sellerDTO -> {
                    if (this.validator.isValid(sellerDTO)) {
                        Seller seller = this.modelMapper.map(sellerDTO, Seller.class);

                        try {
                            seller.setRating(Rating.valueOf(sellerDTO.getRating()));
                            this.sellerRepository.save(seller);

                            this.stringBuilder
                                    .append(String.format("Successfully import seller %s - %s",
                                    seller.getLastName(), seller.getEmail()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
                            appendInvalidSeller();
                        }
                    } else {
                        appendInvalidSeller();
                    }
                });

        return this.stringBuilder.toString().trim();
    }

    @Override
    public Seller findSellerBYId(Long id) {
        return this.sellerRepository.findById(id).orElse(null);
    }

    private void appendInvalidSeller() {
        this.stringBuilder.append("Invalid Seller").append(System.lineSeparator());
    }
}
