package restaurant.entities.drinks;

public class Smoothie extends BaseBeverage{
    private final static double PRICE = 4.50;
    public Smoothie(String name, int counter, String brand) {
        super(name, counter, PRICE, brand);
    }
}
