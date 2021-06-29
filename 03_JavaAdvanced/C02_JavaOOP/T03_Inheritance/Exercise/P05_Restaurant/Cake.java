package T03_Inheritance.Exercise.P05_Restaurant;

import java.math.BigDecimal;

public class Cake extends Dessert{
    private double CAKE_GRAMS;
    private double CAKE_CALORIES;
    private BigDecimal CAKE_PRICE;


    public Cake(String name, BigDecimal price, double grams, double calories) {
        super(name, price, grams, calories);
        this.CAKE_GRAMS = 250;
        this.CAKE_CALORIES = 1000;
        this.CAKE_PRICE = BigDecimal.valueOf(5);
    }
}
