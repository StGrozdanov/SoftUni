package Т05_Polymorphism.Exercise.P02_VehiclesExtended;

import java.text.DecimalFormat;

public class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    protected Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        setFuelQuantity(fuelQuantity);
        setFuelConsumption(fuelConsumption);
        this.tankCapacity = tankCapacity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setFuelQuantity(double fuelQuantity) {
        if (fuelQuantity < 0) {
            System.out.println("Fuel must be a positive number");
        } else {
            this.fuelQuantity = fuelQuantity;
        }
    }

    public void drive(double distance) {
        double requiredLiters = distance * this.fuelConsumption;
        if (fuelQuantity >= requiredLiters) {
            fuelQuantity -= requiredLiters;
            System.out.println(travelled(distance));
        } else {
            System.out.println(noFuel());
        }
    }

    public void refuel(double quantity) {
        if (quantity <= 0) {
            System.out.println("Fuel must be a positive number");
        } else if (this.fuelQuantity + quantity > tankCapacity) {
            System.out.println("Cannot fit fuel in tank");
        } else {
            this.fuelQuantity += quantity;
        }
    }

    private String travelled(double distance) {
        return String.format("%s travelled %s km", getClass().getSimpleName(),
                new DecimalFormat("0.##").format(distance));
    }

    private String noFuel() {
        return String.format("%s needs refueling", getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", getClass().getSimpleName(), this.fuelQuantity);
    }
}