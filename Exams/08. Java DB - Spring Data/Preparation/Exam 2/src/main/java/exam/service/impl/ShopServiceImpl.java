package exam.service.impl;

import exam.model.dtos.shops.ShopCollectionImportDTO;
import exam.model.entities.Shop;
import exam.model.entities.Town;
import exam.repository.ShopRepository;
import exam.service.ShopService;
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
public class ShopServiceImpl implements ShopService {
    private static final String SHOP_FILE_PATH = "src/main/resources/files/xml/shops.xml";

    private final ShopRepository shopRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final XMLTool xmlTool;
    private final ValidationUtil validator;

    public ShopServiceImpl(ShopRepository shopRepository, TownService townService, ModelMapper modelMapper, XMLTool xmlTool, ValidationUtil validator) {
        this.shopRepository = shopRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.xmlTool = xmlTool;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(SHOP_FILE_PATH));
    }

    @Override
    public String importShops() throws JAXBException, IOException {
        if (!this.areImported()) {
            StringBuilder stringBuilder = new StringBuilder();

            this.xmlTool
                    .fromXML(SHOP_FILE_PATH, ShopCollectionImportDTO.class)
                    .getShops()
                    .forEach(shopDTO -> {
                        if (!this.validator.isValid(shopDTO)) {
                            appendInvalidShop(stringBuilder);
                        } else {
                            Town townByName = this.townService.findTownByName(shopDTO.getTown().getName());

                            try {
                                Shop shop = this.modelMapper.map(shopDTO, Shop.class);
                                shop.setTown(townByName);

                                this.shopRepository.save(shop);

                                stringBuilder
                                        .append(String.format("Successfully imported Shop %s - %.0f",
                                                shopDTO.getName(), shopDTO.getIncome()))
                                        .append(System.lineSeparator());
                            } catch (Exception e) {
                                appendInvalidShop(stringBuilder);
                            }
                        }
                    });
            return stringBuilder.toString().trim();
        }
        return null;
    }

    @Override
    public Shop findShopByName(String name) {
        return this.shopRepository.findByName(name);
    }

    private void appendInvalidShop(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid shop").append(System.lineSeparator());
    }
}
