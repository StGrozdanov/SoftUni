package TestExams.Е06;

import java.util.*;
import java.util.stream.Collectors;

public class P02_MakeASalad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> vegetables = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> calories = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(calories::push);

        Map<String, Integer> products = new LinkedHashMap<>();
        products.put("tomato", 80);
        products.put("carrot", 136);
        products.put("lettuce", 109);
        products.put("potato", 215);

        List<Integer> saladsMade = new ArrayList<>();
        boolean workingOnSalad = false;
        boolean lastSaladNotMade = false;

        while (!vegetables.isEmpty() && !calories.isEmpty()) {
            String product = vegetables.poll();
            if (calories.isEmpty() || calories.peek() < 0 && !products.containsKey(product)){
                continue;
            }
            int salad = calories.peek();
            if (!workingOnSalad) {
                saladsMade.add(salad);
            }

            if (products.get(product) >= salad) {
                calories.pop();
                workingOnSalad = false;
                lastSaladNotMade = false;
            } else {
                salad -= products.get(product);
                calories.pop();
                calories.push(salad);
                workingOnSalad = true;
                lastSaladNotMade = true;
            }
        }
        if (lastSaladNotMade){
            calories.pop();
        }
        if (!saladsMade.isEmpty()) {
            saladsMade.forEach(s -> System.out.print(s + " "));
            System.out.println();
        }

        if (!calories.isEmpty()) {
            calories.forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
        if (!products.isEmpty()) {
            vegetables.forEach(p -> System.out.print(p + " "));
            System.out.println();
        }
    }
}
