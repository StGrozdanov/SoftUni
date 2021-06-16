package MidExamPreparation.P18_CatLady;

public class StreetExtraordinaire {
    private String name;
    private double decibels;

    public StreetExtraordinaire(String name, double decibels) {
        this.name = name;
        this.decibels = decibels;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", getClass().getSimpleName(), this.name, this.decibels);
    }

}
