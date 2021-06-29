package T02_Encapsulation.Exercise.P04_Pizza;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        setName(name);
        setToppings(numberOfToppings);
    }

    private void setToppings(int toppingCount) {
        Validator.numberOfToppingsValidator(toppingCount);
        this.toppings = new ArrayList<>(10);
    }

    private void setName(String name) {
        Validator.pizzaNameValidator(name);
        this.name = name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public String getName() {
        return this.name;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public double getOverallCalories() {
        double doughCalories = this.dough.calculateCalories();
        //toppings.stream().mapToDouble(t -> t.calculateCalories()).sum();
        double toppingCalories = 0;
        for (Topping topping : toppings) {
            toppingCalories += topping.calculateCalories();
        }
        return doughCalories + toppingCalories;
    }
}
