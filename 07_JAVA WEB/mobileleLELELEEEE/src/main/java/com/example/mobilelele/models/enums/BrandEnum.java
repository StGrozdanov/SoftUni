package com.example.mobilelele.models.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BrandEnum {
    AUDI,
    BMW,
    FORD,
    MERCEDES_BENZ,
    OPEL,
    VOLKSWAGEN,
    PEUGEOT,
    ALFA_ROMEO,
    ASTON_MARTIN,
    BENTLEY,
    MASERATI,
    MCLAREN,
    ROLLS_ROYCE,
    CHEVROLET,
    JEEP,
    LAND_ROVER,
    JAGUAR;

    public static List<String> getAllModelsForAGivenBrand(String brand) {
        return Arrays.stream(BrandEnum.Models.values())
                .filter(model -> model.getBrand().equals(brand))
                .map(BrandEnum.Models::name)
                .collect(Collectors.toList());
    }

    public static String getBrandByModel(String model) {
        return Models.valueOf(model).getBrand();
    }

    public enum Models {
        //AUDI
        A3(BrandEnum.AUDI.name()),
        A4(BrandEnum.AUDI.name()),
        A6(BrandEnum.AUDI.name()),
        A8(BrandEnum.AUDI.name()),
        Q7(BrandEnum.AUDI.name()),
        //BMW
        M3(BrandEnum.BMW.name()),
        M5(BrandEnum.BMW.name()),
        M6(BrandEnum.BMW.name()),
        X3(BrandEnum.BMW.name()),
        X5(BrandEnum.BMW.name()),
        //FORD
        MUSTANG(BrandEnum.FORD.name()),
        BRONCO(BrandEnum.FORD.name()),
        FOCUS(BrandEnum.FORD.name()),
        FIESTA(BrandEnum.FORD.name()),
        ESCORT(BrandEnum.FORD.name()),
        //MERCEDES-BENZ
        SL55AMG(BrandEnum.MERCEDES_BENZ.name()),
        SLR(BrandEnum.MERCEDES_BENZ.name()),
        S600(BrandEnum.MERCEDES_BENZ.name()),
        E500_COUPE(BrandEnum.MERCEDES_BENZ.name()),
        C63AMG(BrandEnum.MERCEDES_BENZ.name()),
        //VOLKSWAGEN
        PASSAT(BrandEnum.VOLKSWAGEN.name()),
        GOLF(BrandEnum.VOLKSWAGEN.name()),
        TOUAREG(BrandEnum.VOLKSWAGEN.name()),
        BORA(BrandEnum.VOLKSWAGEN.name()),
        PHAETON(BrandEnum.VOLKSWAGEN.name());

        private final String brand;

        Models(String brand) {
            this.brand = brand;
        }

        public String getBrand() {
            return brand;
        }
    }
}
