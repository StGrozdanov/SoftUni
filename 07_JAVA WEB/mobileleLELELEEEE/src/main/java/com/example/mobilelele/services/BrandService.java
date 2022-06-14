package com.example.mobilelele.services;

import com.example.mobilelele.models.entities.BrandEntity;
import com.example.mobilelele.models.enums.BrandEnum;
import com.example.mobilelele.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandService {
    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    public Optional<BrandEntity> findBrandByModel(String modelName) {
        String brandName = BrandEnum.getBrandByModel(modelName).replace("_", " ");
        return this.brandRepository.findByBrandName(brandName);
    }
}
