package MidExamPreparation.P18_CatLady;

public class Siamese {
    private String name;
    private double earSize;

    public Siamese(String name, double earSize) {
        this.name = name;
        this.earSize = earSize;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", getClass().getSimpleName(), this.name, this.earSize);
    }
}
