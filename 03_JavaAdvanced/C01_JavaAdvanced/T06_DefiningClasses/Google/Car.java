package T06_DefiningClasses.Exercise.Google;

public class Car {
    private String model;
    private String speed;

    public Car(String model, String speed) {
        this.model = model;
        this.speed = speed;
    }

    @Override
    public String toString() {
        try {
            return String.format("%s %s%n", this.model, this.speed);
        } catch (NullPointerException exception) {
            return "";
        }
    }
}
