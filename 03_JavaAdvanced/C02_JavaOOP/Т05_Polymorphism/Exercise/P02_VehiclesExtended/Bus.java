package Т05_Polymorphism.Exercise.P02_VehiclesExtended;

public class Bus extends Vehicle {

    private final double baseConsumption;

    protected Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        this.baseConsumption = fuelConsumption;
        setFuelConsumption(fuelConsumption);
    }

    @Override
    public void setFuelConsumption(double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + 1.4);
    }

    public void driveEmpty(double distance) {
        super.setFuelConsumption(baseConsumption);
        super.drive(distance);
    }
}
