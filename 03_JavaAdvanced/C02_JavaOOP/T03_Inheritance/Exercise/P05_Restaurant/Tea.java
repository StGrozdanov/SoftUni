package T03_Inheritance.Exercise.P05_Restaurant;

import java.math.BigDecimal;

public class Tea extends HotBeverage{
    public Tea(String name, BigDecimal price, double milliliters) {
        super(name, price, milliliters);
    }
}
