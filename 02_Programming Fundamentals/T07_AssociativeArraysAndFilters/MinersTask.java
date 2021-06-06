package AssociativeArraysFiltersExercise12;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MinersTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Integer> totals = new LinkedHashMap<>();

        while(!"stop".equals(input)){
            String resource = input;
            int quantity = Integer.parseInt(scanner.nextLine());
            if (!totals.containsKey(resource)){
                totals.put(resource, quantity);
            }else if (totals.containsKey(resource)){
                totals.put(resource, totals.get(resource) + quantity);
            }
            input = scanner.nextLine();
        }
        totals.forEach((key, value) -> System.out.printf("%s -> %d%n", key, value));
    }
}
