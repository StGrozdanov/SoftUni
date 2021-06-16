package MidExamPreparation.P17_SpeedRacing;

public class Car {
    private String model;
    private double fuelAmount;
    private double fuelCostPerKm;
    private int distanceTraveled;

    public Car(String model, double fuelAmount, double fuelCostPerKm) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.fuelCostPerKm = fuelCostPerKm;
        this.distanceTraveled = 0;
    }

    public String getModel() {
        return model;
    }

    public void Drive(int distance) {
        double requiredFuelForTheDrive = distance * this.fuelCostPerKm;
        if (this.fuelAmount >= requiredFuelForTheDrive) {
            this.distanceTraveled += distance;
            this.fuelAmount -= requiredFuelForTheDrive;
        } else {
            System.out.println("Insufficient fuel for the drive");
        }
    }

    @Override
    public String toString() {
        return String.format("%s %.2f %d", this.model, this.fuelAmount, this.distanceTraveled);
    }
}
