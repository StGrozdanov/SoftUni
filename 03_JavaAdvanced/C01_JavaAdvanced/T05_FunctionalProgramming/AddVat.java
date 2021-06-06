package T05_FunctionalProgramming.Lab;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class AddVat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] prices = Arrays.stream(scanner.nextLine().split(", "))
                .mapToDouble(Double::parseDouble).toArray();

        UnaryOperator<Double> vat = value -> value * 1.2;

        System.out.println("Prices with VAT:");
        for (double price : prices) {
            System.out.printf("%.2f%n", vat.apply(price));
        }

    }
}
