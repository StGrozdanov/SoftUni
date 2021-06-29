package T02_Encapsulation.Exercise.P03_ShoppingSpree;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    private void setName(String name) {
        Validator.nameValidator(name);
        this.name = name;
    }

    private void setCost(double cost) {
        Validator.moneyValidator(cost);
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public double getCost() {
        return this.cost;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
