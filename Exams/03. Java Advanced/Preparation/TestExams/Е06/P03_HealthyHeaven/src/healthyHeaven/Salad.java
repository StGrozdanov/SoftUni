package TestExams.Е06.P03_HealthyHeaven.src.healthyHeaven;

import java.util.ArrayList;
import java.util.List;

public class Salad {
    private String name;
    private List<Vegetable> products;

    public Salad(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getTotalCalories() {
        int sum = 0;
        for (Vegetable product : products) {
            sum += product.getCalories();
        }

        return sum;
    }

    public int getProductCount() {
        return this.products.size();
    }

    public void add(Vegetable product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("* Salad %s is %d calories and have %d products:",
                this.name, getTotalCalories(), this.products.size()));
        builder.append(System.lineSeparator());
        for (Vegetable product : products) {
            builder.append(product).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
