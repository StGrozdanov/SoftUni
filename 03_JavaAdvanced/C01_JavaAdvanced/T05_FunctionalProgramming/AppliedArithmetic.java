package T05_FunctionalProgramming.Exercise;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class AppliedArithmetic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        String command = scanner.nextLine();
        while (!"end".equals(command)) {
            if (command.equals("print")) {
                Arrays.stream(numbers).forEach(x -> System.out.print(x + " "));
                System.out.println();
            } else {
                numbers = modifyNumbersByCommand(numbers, command);
            }
            command = scanner.nextLine();
        }
    }

    private static int[] modifyNumbersByCommand(int[] numbers, String command) {

        Function<int[], int[]> action;
        int[] num = new int[0];

        switch (command) {
            case "add":
                action = number -> Arrays.stream(number).map(e -> e += 1).toArray();
                num = action.apply(numbers);
                break;
            case "multiply":
                action = number -> Arrays.stream(number).map(e -> e *= 2).toArray();
                num = action.apply(numbers);
                break;
            case "subtract":
                action = number -> Arrays.stream(number).map(e -> e -= 1).toArray();
                num = action.apply(numbers);
                break;
        }
        return num;
    }
}
