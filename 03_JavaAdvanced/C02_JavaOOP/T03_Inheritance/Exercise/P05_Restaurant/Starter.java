package T03_Inheritance.Exercise.P05_Restaurant;

import java.math.BigDecimal;

public class Starter extends Food{
    public Starter(String name, BigDecimal price, double grams) {
        super(name, price, grams);
    }
}
