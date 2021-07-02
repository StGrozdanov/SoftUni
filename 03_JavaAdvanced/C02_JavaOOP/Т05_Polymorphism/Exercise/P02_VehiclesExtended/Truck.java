package Т05_Polymorphism.Exercise.P02_VehiclesExtended;

public class Truck extends Vehicle {

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public void setFuelConsumption(double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + 1.6);
    }

    @Override
    public void refuel(double quantity) {
        quantity *= 0.95;
        super.refuel(quantity);
    }
}
