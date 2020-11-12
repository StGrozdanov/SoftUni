package SameOlShit;

import java.util.Scanner;

public class VendingMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        double sum = 0;
        double productPrice = 0;

        while (!"Start".equals(input)) {
            double coin = Double.parseDouble(input);
            if (coin == 0.1 || coin == 0.2 || coin == 0.5 || coin == 1 || coin == 2) {
                sum += coin;
            } else {
                System.out.printf("Cannot accept %.2f%n", coin);
            }
            input = scanner.nextLine();
        }
        String product = scanner.nextLine();
        while (!"End".equals(product)) {
            if ("Nuts".equals(product)) {
                productPrice = 2;
            } else if ("Water".equals(product)) {
                productPrice = 0.7;
            } else if ("Crisps".equals(product)) {
                productPrice = 1.5;
            } else if ("Soda".equals(product)) {
                productPrice = 0.8;
            } else if ("Coke".equals(product)) {
                productPrice = 1;
            } else {
                System.out.println("Invalid product");
            }
            if (product.equals("Nuts") || product.equals("Water") || product.equals("Crisps") || product.equals("Soda") || product.equals("Coke")) {
                if (sum < productPrice) {
                    System.out.println("Sorry, not enough money");
                } else {
                    System.out.printf("Purchased %s%n", product);
                    sum -= productPrice;
                }
            }
            product = scanner.nextLine();
        }
        if (sum >= 0){
            System.out.printf("Change: %.2f", sum);
        }
        else {
            System.out.println("Change: 0.00");
        }
    }
}