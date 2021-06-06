package T03_SetsAndMapsAdvanced.Lab;

import java.util.*;

public class ProductShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Map<String, Double>> shops = new TreeMap<>();

        while (!"Revision".equals(input)){
            String[] tokens = input.split(",\\s+");
            String shop = tokens[0];
            String product = tokens[1];
            double price = Double.parseDouble(tokens[2]);

            shops.putIfAbsent(shop, new LinkedHashMap<>());
            shops.get(shop).put(product, price);

            input = scanner.nextLine();
        }

        shops.forEach((key, value) -> {
            System.out.println(key+"->");
            value.forEach((k, v) -> {
                System.out.printf("Product: %s, Price: %.1f%n", k, v);
            });
        });
    }
}
