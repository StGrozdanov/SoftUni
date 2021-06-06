package T06_DefiningClasses.Exercise.CarSalesman;

public class Engine {
    private String model;
    private int power;
    private String displacement;
    private String efficiency;

    public Engine(String model, int power, String displacement, String efficiency) {
        this.model = model;
        this.power = power;
        this.displacement = displacement;
        this.efficiency = efficiency;
    }

    public String getModel() {
        return model;
    }

    public Engine(String model, int power) {
        this(model, power, "n/a", "n/a");
    }

    public Engine(String model, int power, String displacement) {
        this(model, power, displacement, "n/a");
    }

    public Engine(String model, String efficiency, int power) {
        this(model, power, "n/a", efficiency);
    }

    @Override
    public String toString() {
        return String.format("%s:%nPower: %d%nDisplacement: %s%nEfficiency: %s%n", this.model, this.power, this.displacement,
                this.efficiency);
    }
}
