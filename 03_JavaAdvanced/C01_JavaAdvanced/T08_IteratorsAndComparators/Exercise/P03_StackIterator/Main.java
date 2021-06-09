package T08_IteratorsAndComparators.Exercise.P03_StackIterator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] elements = Arrays.stream(scanner.nextLine().replace("Push", " ").trim()
                .split(",\\s+")).mapToInt(Integer::parseInt).toArray();

        Stack stack = new Stack();
        stack.push(elements);

        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] tokens = input.split("\\s+");
            String cmd = tokens[0];
            switch (cmd) {
                case "Push":
                    int[] intsToPush = Arrays.stream(input.replace("Push", " ").trim()
                            .split(",\\s+")).mapToInt(Integer::parseInt).toArray();

                    stack.push(intsToPush);
                    break;
                case "Pop":
                    stack.pop();
                    break;
            }
            input = scanner.nextLine();
        }
        if (!stack.isEmpty()) {
            System.out.println(stack);
            for (Integer integer : stack) {
                System.out.println(integer);
            }
        }
    }
}
