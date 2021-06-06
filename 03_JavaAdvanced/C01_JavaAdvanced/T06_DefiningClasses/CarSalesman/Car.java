package T06_DefiningClasses.Exercise.CarSalesman;

public class Car {
    private String model;
    private Engine engine;
    private String weight;
    private String color;

    public Car(String model, Engine engine, String weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }

    public Car(String model, Engine engine) {
        this(model, engine, "n/a", "n/a");
    }

    public Car(String model, Engine engine, String weight) {
        this(model, engine, weight, "n/a");
    }

    public Car(String model, String color, Engine engine) {
        this(model, engine, "n/a", color);
    }

    @Override
    public String toString() {
        return String.format("%s:%n%sWeight: %s%nColor: %s", this.model, this.engine.toString(), this.weight, this.color);
    }
}
