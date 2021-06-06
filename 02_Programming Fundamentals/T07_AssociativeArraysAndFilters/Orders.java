package AssociativeArraysFiltersExercise12;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Double> productPrice = new LinkedHashMap<>();
        Map<String, Integer> startingProduct = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while(!"buy".equals(input)){
            String[] tokens = input.split(" ");
            String product = tokens[0];
            double price = Double.parseDouble(tokens[1]);
            int quantity = Integer.parseInt(tokens[2]);

            startingProduct.putIfAbsent(product, 0);
            startingProduct.put(product, startingProduct.get(product) + quantity);
            productPrice.put(product, price);

            input = scanner.nextLine();
        }
        productPrice.forEach((key, value) -> System.out.printf("%s -> %.2f%n", key, value *
                startingProduct.get(key)));
    }
}
