package restaurant.entities.drinks;

public class Fresh extends BaseBeverage{
    private final static double PRICE = 3.50;
    public Fresh(String name, int counter, String brand) {
        super(name, counter, PRICE, brand);
    }
}
