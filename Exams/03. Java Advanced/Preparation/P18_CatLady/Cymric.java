package MidExamPreparation.P18_CatLady;

public class Cymric {
    private String name;
    private double furLength;

    public Cymric(String name, double furLength) {
        this.name = name;
        this.furLength = furLength;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", getClass().getSimpleName(), this.name, this.furLength);
    }

}
