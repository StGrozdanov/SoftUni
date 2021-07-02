package Т05_Polymorphism.Exercise.P01_Vehicles;

import java.text.DecimalFormat;

public class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    protected Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        setFuelConsumption(fuelConsumption);
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void drive(double distance){
        double requiredLiters = distance * this.fuelConsumption;
        if (fuelQuantity >= requiredLiters){
            fuelQuantity -= requiredLiters;
            System.out.println(travelled(distance));
        } else {
            System.out.println(noFuel());
        }
    }
    public void refuel(double quantity){
        this.fuelQuantity += quantity;
    }

    private String travelled(double distance){
        return String.format("%s travelled %s km", getClass().getSimpleName(),
                new DecimalFormat("0.##").format(distance));
    }

    private String noFuel(){
        return String.format("%s needs refueling", getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", getClass().getSimpleName(), this.fuelQuantity);
    }
}
