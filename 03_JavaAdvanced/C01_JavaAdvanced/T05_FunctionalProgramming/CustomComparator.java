package T05_FunctionalProgramming.Exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomComparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());

        Comparator<Integer> comparator = (num1, num2) -> {
            if (num1 % 2 == 0 && num2 % 2 != 0){
                return -1; // няма промяна, не ги размествай
            } else if (num1 % 2 != 0 && num2 % 2 == 0){
                return 1; // размени им местата
            }
            return num1.compareTo(num2);
        };

        numbers.stream().sorted(comparator).forEach(e -> System.out.print(e + " "));

    }
}
