package T05_FunctionalProgramming.Lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SortEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> evenNumbers = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(evenNumbers.toString().replaceAll("[\\[\\]]", ""));

        List<Integer> sorted = evenNumbers.stream().sorted((n1, n2) -> n1.compareTo(n2))
                .collect(Collectors.toList());

        System.out.println(sorted.toString().replaceAll("[\\[\\]]", ""));
    }
}
