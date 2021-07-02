package T04_InterfacesAndAbstraction.Exercise.P04_FoodShortage;

public interface foodBuyers extends Person, Buyer {
    String getName();
    void buyFood();
    int getFood();
}
