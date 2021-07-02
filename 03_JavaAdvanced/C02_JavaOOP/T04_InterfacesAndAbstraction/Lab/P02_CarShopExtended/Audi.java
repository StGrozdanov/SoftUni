package T04_InterfacesAndAbstraction.Lab.P02_CarShopExtended;

public class Audi extends CarImpl implements Rentable {

    private Integer minRentDay;
    private Double pricePerDay;

    public Audi(String model, String color, Integer horsePower, String country, Integer minRent, Double price) {
        super(model, color, horsePower, country);
        this.minRentDay = minRent;
        this.pricePerDay = price;
    }

    @Override
    public Integer getMinRentDay() {
        return this.minRentDay;
    }

    @Override
    public Double getPricePerDay() {
        return this.pricePerDay;
    }

    @Override
    public String toString() {
        return super.toString() +
                System.lineSeparator() +
                String.format("Minimum rental period of %d days. Price per day %f", this.minRentDay, this.pricePerDay);
    }
}
