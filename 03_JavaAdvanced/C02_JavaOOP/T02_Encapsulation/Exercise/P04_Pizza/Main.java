package T02_Encapsulation.Exercise.P04_Pizza;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaTokens = scanner.nextLine().split("\\s+");
        Pizza pizza = new Pizza(pizzaTokens[1], Integer.parseInt(pizzaTokens[2]));
        String[] doughTokens = scanner.nextLine().split("\\s+");
        Dough dough = new Dough(doughTokens[1], doughTokens[2], Double.parseDouble(doughTokens[3]));

        pizza.setDough(dough);

        String input = scanner.nextLine();

        while (!"END".equals(input)){
            String[] toppingTokens = input.split("\\s+");
            Topping topping = new Topping(toppingTokens[1], Double.parseDouble(toppingTokens[2]));
            pizza.addTopping(topping);
            input = scanner.nextLine();
        }
        System.out.printf("%s - %.2f%n", pizza.getName(), pizza.getOverallCalories());
    }
}
