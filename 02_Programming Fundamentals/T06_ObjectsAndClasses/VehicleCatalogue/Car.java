package ObjectsAndClassesExercise10.VehicleCatalogue;

public class Car {
    private String type;
    private String model;
    private String color;
    private int horsePower;

    public Car(String type, String model, String color, int horsePower) {
        this.type = type;
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public double getHorsePower() {
        return horsePower;
    }

}
