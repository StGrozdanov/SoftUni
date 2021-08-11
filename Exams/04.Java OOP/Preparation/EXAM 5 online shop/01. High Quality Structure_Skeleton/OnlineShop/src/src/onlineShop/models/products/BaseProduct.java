package onlineShop.models.products;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.PRODUCT_TO_STRING;

public abstract class BaseProduct implements Product {
    private int id;
    private String manufacturer;
    private String model;
    private double price;
    private double overallPerformance;

    protected BaseProduct(int id, String manufacturer, String model, double price, double overallPerformance) {
        this.setId(id);
        this.setManufacturer(manufacturer);
        this.setModel(model);
        this.setPrice(price);
        this.setOverallPerformance(overallPerformance);
    }


    private void setId(int id) {
        if (falseValueValidation(id)){
            throw new IllegalArgumentException(INVALID_PRODUCT_ID);
        }
        this.id = id;
    }

    private void setManufacturer(String manufacturer) {
        if (falseStringValidation(manufacturer)){
            throw new IllegalArgumentException(INVALID_MANUFACTURER);
        }
        this.manufacturer = manufacturer;
    }

    private void setModel(String model) {
        if (falseStringValidation(model)){
            throw new IllegalArgumentException(INVALID_MODEL);
        }
        this.model = model;
    }

    private void setPrice(double price) {
        if (falseValueValidation(price)){
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }

    protected void setOverallPerformance(double overallPerformance) {
        if (falseValueValidation(overallPerformance)){
            throw new IllegalArgumentException(INVALID_OVERALL_PERFORMANCE);
        }
        this.overallPerformance = overallPerformance;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public double getOverallPerformance() {
        return this.overallPerformance;
    }

    @Override
    public String toString() {
        return String.format(PRODUCT_TO_STRING, this.getOverallPerformance(),
                this.getPrice(), this.getClass().getSimpleName(), this.getManufacturer(), this.getModel(), this.getId());
    }

    private boolean falseValueValidation(int value){
        return value <= 0;
    }
    private boolean falseValueValidation(double value){
        return value <= 0;
    }
    private boolean falseStringValidation(String parameter){
        return parameter == null || parameter.trim().isEmpty();
    }

}
