package T05_FunctionalProgramming.Exercise;

import java.util.Scanner;
import java.util.function.Consumer;

public class KnightsOfHonor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Consumer<String> consumer = element -> System.out.println("Sir " + element);
        String[] tokens = scanner.nextLine().split("\\s+");
        for (String token : tokens) {
            consumer.accept(token);
        }
    }
}
