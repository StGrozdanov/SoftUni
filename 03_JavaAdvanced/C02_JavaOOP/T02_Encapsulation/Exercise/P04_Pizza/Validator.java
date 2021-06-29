package T02_Encapsulation.Exercise.P04_Pizza;

public class Validator {

    public static void geometryValidator(double unit, String type) {
        if (unit <= 0) {
            throw new IllegalStateException(String.format("%s cannot be zero or negative.", type));
        }
    }

    public static void ageValidator(int age) {
        if (age < 0 || age > 15) {
            throw new IllegalStateException("Age should be between 0 and 15.");
        }
    }

    public static void nameValidator(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Name cannot be empty");
        }
    }

    public static void moneyValidator(double money) {
        if (money < 0) {
            throw new IllegalStateException("Money cannot be negative");
        }
    }

    public static void flourTypeValidator(String flour) {
        if (!flour.equals("White") && !flour.equals("Wholegrain")) {
            throw new IllegalStateException("Invalid type of dough.");
        }
    }

    public static void bakingTechniqueValidator(String technique) {
        if (!technique.equals("Crispy") && !technique.equals("Chewy") && !technique.equals("Homemade")) {
            throw new IllegalStateException("Invalid type of dough.");
        }
    }

    public static void doughWeightValidator(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalStateException("T02_Encapsulation.Exercise.T02_Encapsulation.Exercise.P04_Pizza.Dough weight should be in the range [1..200].");
        }
    }

    public static void toppingTypeValidator(String topping) {
        if (!topping.equals("Meat") && !topping.equals("Veggies") && !topping.equals("Cheese") &&
                !topping.equals("Sauce")) {
            throw new IllegalStateException(String.format("Cannot place %s on top of your pizza.", topping));
        }
    }

    public static void toppingWeightValidator(double weight, String name) {
        if (weight < 1 || weight > 50) {
            throw new IllegalStateException(String.format("%s weight should be in the range [1..50].", name));
        }
    }

    public static void pizzaNameValidator(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 15 || name.length() < 1) {
            throw new IllegalStateException("T02_Encapsulation.Exercise.T02_Encapsulation.Exercise.P04_Pizza.Pizza name should be between 1 and 15 symbols.");
        }
    }

    public static void numberOfToppingsValidator(int toppingNumber) {
        if (toppingNumber < 0 || toppingNumber > 10) {
            throw new IllegalStateException("Number of toppings should be in range [0..10].");
        }
    }
}
