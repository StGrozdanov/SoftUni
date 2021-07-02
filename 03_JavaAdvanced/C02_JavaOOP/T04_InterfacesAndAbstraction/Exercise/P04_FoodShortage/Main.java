package T04_InterfacesAndAbstraction.Exercise.P04_FoodShortage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<foodBuyers> foodBuyers = new ArrayList<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            if (tokens.length == 4) {
                foodBuyers.add(new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]));
            } else if (tokens.length == 3) {
                foodBuyers.add(new Rebel(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
            }
        }

        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String name = input;
            foodBuyers.stream().filter(p -> p.getName().equals(name)).forEach(b -> b.buyFood());
            input = scanner.nextLine();
        }

        int sum = foodBuyers.stream().mapToInt(b -> Integer.parseInt(String.valueOf(b.getFood()))).sum();

        System.out.println(sum);
    }
}
