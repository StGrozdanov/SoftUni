package T05_FunctionalProgramming.Exercise;

import java.util.Scanner;
import java.util.function.Consumer;

public class ConsumerPrint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Consumer<String> consumer = System.out::println;
        String[] tokens = scanner.nextLine().split("\\s+");
        for (String token : tokens) {
            consumer.accept(token);
        }

    }
}
