package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dtos.laptops.LaptopImportDTO;
import exam.model.entities.Laptop;
import exam.model.entities.Shop;
import exam.model.entities.WarrantyType;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.util.interfaces.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class LaptopServiceImpl implements LaptopService {
    private static final String LAPTOP_FILE_PATH = "src/main/resources/files/json/laptops.json";

    private final LaptopRepository laptopRepository;
    private final ShopService shopService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopService shopService, ModelMapper modelMapper, Gson gson, ValidationUtil validator) {
        this.laptopRepository = laptopRepository;
        this.shopService = shopService;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }


    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(LAPTOP_FILE_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        if (!this.areImported()) {
            StringBuilder stringBuilder = new StringBuilder();

            Arrays.stream(this.gson.fromJson(this.readLaptopsFileContent(), LaptopImportDTO[].class))
                    .forEach(laptopDto -> {
                        if (!this.validator.isValid(laptopDto)) {
                            appendInvalidLaptop(stringBuilder);
                        } else {
                            Laptop laptop = this.modelMapper.map(laptopDto, Laptop.class);

                            Shop shop = this.shopService.findShopByName(laptopDto.getShop().getName());

                            laptop.setShop(shop);

                            try {
                                laptop.setWarrantyType(WarrantyType.valueOf(laptopDto.getWarrantyType()));
                                this.laptopRepository.save(laptop);

                                stringBuilder
                                        .append(String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                                                laptopDto.getMacAddress(),
                                                laptopDto.getCpuSpeed(),
                                                laptopDto.getRam(),
                                                laptopDto.getStorage()))
                                        .append(System.lineSeparator());

                            } catch (Exception e) {
                                appendInvalidLaptop(stringBuilder);
                            }
                        }

                    });
            return stringBuilder.toString().trim();
        }
        return null;
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder stringBuilder = new StringBuilder();

        this.laptopRepository
                .findAllByOrderByCpuSpeedDescRamDescStorageDescMacAddress()
                .forEach(stringBuilder::append);

        return stringBuilder.toString().trim();
    }

    private void appendInvalidLaptop(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid Laptop").append(System.lineSeparator());
    }
}
