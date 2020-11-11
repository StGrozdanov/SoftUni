package ProgrammingBasics.WhileCycle;

import java.util.Scanner;

public class AccountBalance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String amount = scanner.nextLine();

        double sum = 0;

        while (!amount.equals("NoMoreMoney")) {
            double price = Double.parseDouble(amount);
            if (price < 0) {
                System.out.println("Invalid operation!");
                break;
            }
                sum += price;
                System.out.printf("Increase: %.2f%n", price);
                amount = scanner.nextLine();
        }System.out.printf("Total: %.2f", sum);
    }
}
