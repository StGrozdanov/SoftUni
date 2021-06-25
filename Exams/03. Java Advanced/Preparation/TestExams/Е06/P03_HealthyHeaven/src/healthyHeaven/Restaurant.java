package TestExams.Е06.P03_HealthyHeaven.src.healthyHeaven;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<Salad> data;

    public Restaurant(String name) {
        this.name = name;
        this.data = new ArrayList<>();
    }

    public void add(Salad salad) {
        this.data.add(salad);
    }

    public boolean buy(String name) {
        for (Salad salad : data) {
            if (salad.getName().equals(name)) {
                data.remove(salad);
                return true;
            }
        }
        return false;
    }

    public Salad getHealthiestSalad() {
        int lowestCal = Integer.MAX_VALUE;
        String saladName = "";
        for (Salad salad : data) {
            if (salad.getTotalCalories() < lowestCal) {
                lowestCal = salad.getTotalCalories();
                saladName = salad.getName();
            }
        }
        String finalSaladName = saladName;
        return data.stream().filter(s -> s.getName().equals(finalSaladName)).findFirst().orElse(null);
    }
    public String generateMenu(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s have %d salads:", this.name, this.data.size())).append(System.lineSeparator());
        for (Salad salad : data) {
            builder.append(salad);
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
