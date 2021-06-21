package TestExams.Е02;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P01_FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> lilies = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .forEach(lilies::push);

        ArrayDeque<Integer> roses = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int wreaths = 0;
        int flowersLeft = 0;

        while (!lilies.isEmpty() && !roses.isEmpty()) {
            int lillie = lilies.peek();
            int rose = roses.peek();
            int flowersSum = lillie + rose;
            while (flowersSum > 15) {
                lillie -= 2;
                flowersSum = lillie + rose;
            }
            if (flowersSum == 15) {
                wreaths++;
                lilies.pop();
                roses.poll();
            }
            if (flowersSum < 15) {
                flowersLeft += flowersSum;
                lilies.pop();
                roses.poll();
            }
        }
        flowersLeft /= 15;
        wreaths += flowersLeft;
        if (wreaths >= 5) {
            System.out.printf("You made it, you are going to the competition with %d wreaths!", wreaths);
        } else {
            int wreathsNeeded = 5 - wreaths;
            System.out.printf("You didn't make it, you need %d wreaths more!", wreathsNeeded);
        }
    }
}
