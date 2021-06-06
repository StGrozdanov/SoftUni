package T05_FunctionalProgramming.Exercise;

import java.util.Scanner;
import java.util.function.Predicate;

public class PredicateForNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lengthTarget = Integer.parseInt(scanner.nextLine());
        String[] tokens = scanner.nextLine().split("\\s+");
        Predicate<String> validation = element -> element.length() <= lengthTarget;

        for (String token : tokens) {
            if (validation.test(token)){
                System.out.println(token);
            }
        }

    }
}
