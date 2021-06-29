package T02_Encapsulation.Exercise.P03_ShoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        Validator.nameValidator(name);
        this.name = name;
    }

    private void setMoney(double money) {
        Validator.moneyValidator(money);
        this.money = money;
    }

    public String getName(){
        return this.name;
    }

    public void buyProduct(Product product){
        if (this.money >= product.getCost()){
            this.products.add(product);
            this.money -= product.getCost();
            System.out.printf("%s bought %s%n", this.name, product.getName());
        } else {
            System.out.printf("%s can't afford %s", this.name, product.getName());
        }
    }

    @Override
    public String toString() {
        if (this.products.isEmpty()){
            return String.format("%s - Nothing bought", this.name);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s - ", this.name)).append(String.join(", ", products.toString()));
        return builder.toString().trim();
    }
}
