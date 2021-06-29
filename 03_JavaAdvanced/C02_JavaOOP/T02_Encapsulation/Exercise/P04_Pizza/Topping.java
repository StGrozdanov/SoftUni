package T02_Encapsulation.Exercise.P04_Pizza;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        Validator.toppingTypeValidator(toppingType);
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        Validator.toppingWeightValidator(weight, this.toppingType);
        this.weight = weight;
    }

    public double calculateCalories() {
        switch (this.toppingType) {
            case "Meat":
                return (this.weight * 2) * 1.2;
            case "Veggies":
                return (this.weight * 2) * 0.8;
            case "Cheese":
                return (this.weight * 2) * 1.1;
            case "Sauce":
                return (this.weight * 2) * 0.9;
        }
        return -1;
    }
}
