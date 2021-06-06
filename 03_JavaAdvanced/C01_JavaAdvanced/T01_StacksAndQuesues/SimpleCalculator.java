package T01_StacksAndQuesues.Lab;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> elements = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayDeque::new));

        while (elements.size() > 1) {
            int first = Integer.parseInt(elements.pop());
            String operator = elements.pop();
            int second = Integer.parseInt(elements.pop());
            if ("+".equals(operator)) {
                elements.push(String.valueOf(first + second));
            } else {
                elements.push(String.valueOf(first - second));
            }
        }
        System.out.println(elements.pop());
    }
}
