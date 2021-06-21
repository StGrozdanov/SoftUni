package TestExams.Е01;

import java.util.*;
import java.util.stream.Collectors;

public class P01_Cooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> liquids = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(ingredients::push);

        Map<String, Integer> foodTargets = new LinkedHashMap<>();
        foodTargets.put("Bread", 25);
        foodTargets.put("Cake", 50);
        foodTargets.put("Pastry", 75);
        foodTargets.put("Fruit Pie", 100);

        Map<String, Integer> cookedFoods = new TreeMap<>();
        cookedFoods.put("Bread", 0);
        cookedFoods.put("Cake", 0);
        cookedFoods.put("Pastry", 0);
        cookedFoods.put("Fruit Pie", 0);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int mix = ingredients.peek();
            int sum = liquids.poll() + mix;
            boolean foodCooked = false;
            for (Map.Entry<String, Integer> entry : foodTargets.entrySet()) {
                if (entry.getValue().equals(sum)) {
                    int newValue = cookedFoods.get(entry.getKey()) + 1;
                    cookedFoods.put(entry.getKey(), newValue);
                    ingredients.pop();
                    foodCooked = true;
                }
            }
            if (!foodCooked) {
                int oldValue = ingredients.pop();
                int newValue = oldValue + 3;
                ingredients.push(newValue);
            }
        }
        boolean cookedEverything = true;
        for (Integer value : cookedFoods.values()) {
            if (value == 0) {
                cookedEverything = false;
                break;
            }
        }
        if (cookedEverything) {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to to cook everything.");
        }
        if (liquids.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            System.out.print("Liquids left: ");
            System.out.print(String.join(", ", liquids.toString().replaceAll("[\\[\\]]", "")));
            System.out.println();
        }
        if (ingredients.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            System.out.print("Ingredients left: ");
            System.out.print(String.join(", ", ingredients.toString().replaceAll("[\\[\\]]", "")));
            System.out.println();
        }
        cookedFoods.forEach((f, n) -> System.out.printf("%s: %d%n", f, n));
    }
}
