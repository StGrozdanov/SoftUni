package ProgrammingBasics;

import java.util.Scanner;

public class GodzillaVSKong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budget = Double.parseDouble(scanner.nextLine());
        int people = Integer.parseInt(scanner.nextLine());
        double price = Double.parseDouble(scanner.nextLine());

        if (people > 150){
            price = price * 0.9;
        }
        double decorPrice = budget * 0.1;
        double clothPrice = people * price;
        double totalPrice = decorPrice + clothPrice;
        double fail = 0;

        if (totalPrice > budget){
            fail = totalPrice - budget;
            System.out.println("Not enough money!");
            System.out.printf("Wingard needs %.2f leva more.", fail);
        } else {
            fail = budget - totalPrice;
            System.out.println("Action!");
            System.out.printf("Wingard starts filming with %.2f leva left.", fail);
        }
    }
}
