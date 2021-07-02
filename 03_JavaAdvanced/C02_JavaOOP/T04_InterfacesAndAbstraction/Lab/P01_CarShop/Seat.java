package T04_InterfacesAndAbstraction.Lab.P01_CarShop;

public class Seat implements Car {
    private String model;
    private String color;
    private Integer horsePower;
    private String country;

    public Seat(String model, String color, Integer horsePower, String country) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.country = country;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String countryProduced() {
        return this.country;
    }

    @Override
    public String toString() {
        return String.format(
                "This is %s produced in %s and have %d tires",
                this.model,
                this.country,
                this.TIRES);
    }
}
