package ProgrammingBasics;

import java.util.Scanner;

public class ToyShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double target = Double.parseDouble(scanner.nextLine());
        int puzzles = Integer.parseInt(scanner.nextLine());
        int pupils = Integer.parseInt(scanner.nextLine());
        int bears = Integer.parseInt(scanner.nextLine());
        int minions = Integer.parseInt(scanner.nextLine());
        int trucks = Integer.parseInt(scanner.nextLine());

        double puzzlePrice = puzzles * 2.6;
        double pupilsPrice = pupils * 3;
        double bearPrice = bears * 4.1;
        double minionPrice = minions * 8.2;
        double truckPrice = trucks * 2;

        double totalPrice = puzzlePrice + pupilsPrice + bearPrice + minionPrice + truckPrice;
        double toyCount = puzzles + pupils + bears + minions + trucks;

        if (toyCount >= 50){
            totalPrice = totalPrice * 0.75;
        }

        double priceAfterRent = totalPrice * 0.9;
        double moneyLeft = Math.abs(priceAfterRent - target);

        if (priceAfterRent >= target){
            System.out.printf("Yes! %.2f lv left.", moneyLeft);
        } else {
            System.out.printf("Not enough money! %.2f lv needed.", moneyLeft);
        }
    }
}
