package T05_FunctionalProgramming.Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class CountUppercaseWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        List<String> uppercaseWords = findUppercaseWords(tokens);

        System.out.println(uppercaseWords.size());
        uppercaseWords.forEach(System.out::println);

    }

    private static List<String> findUppercaseWords(String[] tokens) {
        Predicate<String> testSolution;
        List<String> upper = new ArrayList<>();

        for (String token : tokens) {
            testSolution = text -> Character.isUpperCase(token.charAt(0));
            if (testSolution.test(String.valueOf(token.charAt(0)))){
                upper.add(token);
            }
        }
        return upper;
    }
}
