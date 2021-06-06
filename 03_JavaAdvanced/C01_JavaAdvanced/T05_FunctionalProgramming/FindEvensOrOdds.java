package T05_FunctionalProgramming.Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindEvensOrOdds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int lowerBound = Integer.parseInt(tokens[0]);
        int upperBound = Integer.parseInt(tokens[1]);
        String command = scanner.nextLine();

        findNumbersByEvenOddCriteria(lowerBound, upperBound, command);

    }

    private static void findNumbersByEvenOddCriteria(int lowerBound, int upperBound, String command) {
        List<Integer> numbers = new ArrayList<>();
        switch (command) {
            case "even":
                for (int i = lowerBound; i <= upperBound; i++) {
                    if (i % 2 == 0) {
                        numbers.add(i);
                    }
                }
                break;
            case "odd":
                for (int i = lowerBound; i <= upperBound; i++) {
                    if (i % 2 != 0) {
                        numbers.add(i);
                    }
                }
                break;
        }
        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
    }
}
