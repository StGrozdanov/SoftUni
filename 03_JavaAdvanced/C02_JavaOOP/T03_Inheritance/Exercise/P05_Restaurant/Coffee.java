package T03_Inheritance.Exercise.P05_Restaurant;

import java.math.BigDecimal;

public class Coffee extends HotBeverage{
    private double COFFEE_MILLILITERS;
    private BigDecimal COFFEE_PRICE;
    private double caffeine;

    public Coffee(String name, BigDecimal price, double milliliters, double caffeine) {
        super(name, price, milliliters);
        this.COFFEE_MILLILITERS = 50.00;
        this.COFFEE_PRICE = BigDecimal.valueOf(3.50);
        this.caffeine = caffeine;
    }

    public double getCaffeine() {
        return caffeine;
    }
}
