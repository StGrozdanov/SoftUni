package TestExams.Е05;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P01_DatingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> males = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(males::push);

        ArrayDeque<Integer> females = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        males.removeIf(e -> e <= 0);
        females.removeIf(e -> e <= 0);

        int matchesCount = 0;

        while (!males.isEmpty() && !females.isEmpty()) {
            int female = females.peek();
            int male = males.peek();

            if (female % 25 == 0) {
                females.pop();
                if (!females.isEmpty()) {
                    females.pop();
                } else {
                    break;
                }
                continue;
            }
            if (male % 25 == 0) {
                males.poll();
                if (!males.isEmpty()) {
                    males.poll();
                } else {
                    break;
                }
                continue;
            }
            if (female == male) {
                males.poll();
                females.pop();
                matchesCount++;
            } else {
                females.pop();
                male -= 2;
                males.poll();
                if (male > 0) {
                    males.push(male);
                }
            }
        }

        System.out.printf("Matches: %d%n", matchesCount);
        if (males.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            System.out.print("Males left: ");
            System.out.println(String.join(", ", males.toString()
                    .replaceAll("[\\[\\]]", "")));
        }
        if (females.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            System.out.print("Females left: ");
            System.out.println(String.join(", ", females.toString()
                    .replaceAll("[\\[\\]]", "")));
        }
    }
}
