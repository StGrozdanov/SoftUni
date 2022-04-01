package hiberspring.services.impl;

import hiberspring.models.dtos.products.ProductCollectionImportDTO;
import hiberspring.models.entities.Branch;
import hiberspring.models.entities.Product;
import hiberspring.repositories.ProductRepository;
import hiberspring.services.BranchService;
import hiberspring.services.ProductService;
import hiberspring.utils.interfaces.ValidationUtil;
import hiberspring.utils.interfaces.XMLTool;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCTS_FILE_PATH = "src/main/resources/files/products.xml";

    private final ProductRepository productRepository;
    private final BranchService branchService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XMLTool xmlTool;
    private final StringBuilder stringBuilder;

    public ProductServiceImpl(ProductRepository productRepository, BranchService branchService, ModelMapper modelMapper, ValidationUtil validator, XMLTool xmlTool, StringBuilder stringBuilder) {
        this.productRepository = productRepository;
        this.branchService = branchService;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlTool = xmlTool;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return Files.readString(Path.of(PRODUCTS_FILE_PATH));
    }

    @Override
    public String importProducts() throws JAXBException, IOException {
        if (this.productsAreImported()) {
            return null;
        }

        this.xmlTool
                .fromXML(PRODUCTS_FILE_PATH, ProductCollectionImportDTO.class)
                .getProducts()
                .forEach(productDTO -> {
                    if (this.validator.isValid(productDTO)) {
                        Product product = this.modelMapper.map(productDTO, Product.class);

                        try {
                            Branch branch = this.branchService.findBranchByName(productDTO.getBranch());
                            product.setBranch(branch);

                            this.productRepository.save(product);

                            this.stringBuilder
                                    .append(String.format("Successfully imported Product %s.",
                                            product.getName()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
                            appendInvalidProduct();
                        }
                    } else {
                        appendInvalidProduct();
                    }
                });
        return this.stringBuilder.toString().trim();
    }

    private void appendInvalidProduct() {
        this.stringBuilder.append("Invalid Product").append(System.lineSeparator());
    }
}
