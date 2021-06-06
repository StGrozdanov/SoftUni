package T03_SetsAndMapsAdvanced.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AMinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Integer> resources = new LinkedHashMap<>();
        int counter = 0;
        String resource = "";

        while (!"stop".equals(input)){
            counter++;
            if (counter % 2 == 0){
                int quantity = Integer.parseInt(input);
                int previousQuantity = resources.get(resource);
                resources.put(resource, quantity + previousQuantity);
            } else {
                resource = input;
                resources.putIfAbsent(input, 0);
            }
            input = scanner.nextLine();
        }
        resources.forEach((key, value) -> System.out.printf("%s -> %d%n", key, value));
    }
}
