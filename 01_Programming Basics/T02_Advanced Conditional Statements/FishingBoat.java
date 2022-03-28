package ProgrammingBasics.AdvancedConditionalStatements.AdvConditionsExercise;

import java.util.Scanner;

public class FishingBoat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int budget = Integer.parseInt(scanner.nextLine());
        String season = scanner.nextLine();
        int people = Integer.parseInt(scanner.nextLine());

        double price = 0.0;
        double moneyLeft = 0.0;

        switch (season){
            case "Spring":
                price = 3000;
                break;
            case "Summer":
            case "Autumn":
                price = 4200;
                break;
            case "Winter":
                price = 2600;
                break;
        } if (people <= 6){
            price = price * 0.9;
        } else if (people <= 11){
            price = price * 0.85;
        } else {
            price = price * 0.75;
        } if (people % 2 == 0 && !season.equals("Autumn")){
            price = price * 0.95;
        } moneyLeft = budget - price;
        if (budget >= price){
            System.out.printf("Yes! You have %.2f leva left.", Math.abs(moneyLeft));
        } else {
            System.out.printf("Not enough money! You need %.2f leva.", Math.abs(moneyLeft));
        }

    }
}
