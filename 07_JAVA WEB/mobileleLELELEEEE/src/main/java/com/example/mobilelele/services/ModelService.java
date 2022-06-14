package com.example.mobilelele.services;

import com.example.mobilelele.models.dtos.OfferDTOs.AddOfferDTO;
import com.example.mobilelele.models.entities.BrandEntity;
import com.example.mobilelele.models.entities.ModelEntity;
import com.example.mobilelele.models.enums.BrandEnum;
import com.example.mobilelele.models.enums.CategoryEnum;
import com.example.mobilelele.repositories.ModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class ModelService {
    private ModelRepository modelRepository;
    private ModelMapper modelMapper;
    private BrandService brandService;

    public ModelService(ModelRepository modelRepository, ModelMapper modelMapper, BrandService brandService) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
    }

    public Optional<ModelEntity> getModelByName(String modelName) {
        return this.modelRepository.findByModelName(modelName);
    }

    public ModelEntity createNewModel(AddOfferDTO offerDTO) {
        ModelEntity model = this.modelMapper.map(offerDTO, ModelEntity.class);

        model.setCategory(CategoryEnum.CAR);
        model.setStartYear(offerDTO.getYear());
        model.setModelName(offerDTO.getModel());

        Optional<BrandEntity> brand = this.brandService.findBrandByModel(offerDTO.getModel());

        model.setBrand(brand.get());

        return this.modelRepository.save(model);
    }
}
