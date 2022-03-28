package ProgrammingBasics.AdvancedConditionalStatements;

import java.util.Scanner;

public class TradeComm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String city = scanner.nextLine();
        double s = Double.parseDouble(scanner.nextLine());

        double money = 0.00;

        if (city.equals("Sofia")) {
            if (s >= 0 && s <= 500) {
                money = 0.05 * s;
                System.out.printf("%.2f", money);
            } else if (s > 500 && s <= 1000) {
                money = 0.07 * s;
                System.out.printf("%.2f", money);
            } else if (s > 1000 && s <= 10000) {
                money = 0.08 * s;
                System.out.printf("%.2f", money);
            } else if (s > 10000) {
                money = 0.12 * s;
                System.out.printf("%.2f", money);
            } else {
                System.out.println("error");
            }
        } else if (city.equals("Plovdiv")) {
            if (s >= 0 && s <= 500) {
                money = 0.055 * s;
                System.out.printf("%.2f", money);
            } else if (s > 500 && s <= 1000) {
                money = 0.08 * s;
                System.out.printf("%.2f", money);
            } else if (s > 1000 && s <= 10000) {
                money = 0.12 * s;
                System.out.printf("%.2f", money);
            } else if (s > 10000) {
                money = 0.145 * s;
                System.out.printf("%.2f", money);
            } else {
                System.out.println("error");
            }
        } else if (city.equals("Varna")) {
            if (s >= 0 && s <= 500) {
                money = 0.045 * s;
                System.out.printf("%.2f", money);
            } else if (s > 500 && s <= 1000) {
                money = 0.075 * s;
                System.out.printf("%.2f", money);
            } else if (s > 1000 && s <= 10000) {
                money = 0.1 * s;
                System.out.printf("%.2f", money);
            } else if (s > 10000) {
                money = 0.13 * s;
                System.out.printf("%.2f", money);
            } else {
                System.out.println("error");
            }
        } else {
            System.out.println("error");
        }
    }
}