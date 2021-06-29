package T03_Inheritance.Exercise.P05_Restaurant;

import java.math.BigDecimal;

public class Salmon extends MainDish{
    private double SALMON_GRAMS;

    public Salmon(String name, BigDecimal price, double grams) {
        super(name, price, grams);
        this.SALMON_GRAMS = 22;
    }
}
