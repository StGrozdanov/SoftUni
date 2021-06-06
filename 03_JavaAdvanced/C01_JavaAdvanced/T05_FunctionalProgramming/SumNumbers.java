package T05_FunctionalProgramming.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        System.out.printf("Count = %d%n", numbers.length);
        System.out.printf("Sum = %d", Arrays.stream(numbers).sum());

    }
}
