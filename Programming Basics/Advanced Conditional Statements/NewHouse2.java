package ProgrammingBasics.AdvancedConditionalStatements.AdvConditionsExercise;

import java.util.Scanner;

public class NewHouse2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String flower = scanner.nextLine();
        int count = Integer.parseInt(scanner.nextLine());
        int budget = Integer.parseInt(scanner.nextLine());

        double rosePrice = 0.0;
        double dahliaPrice = 0.0;
        double tulipsPrice = 0.0;
        double narcissusPrice = 0.0;
        double gladiolusPrice = 0.0;
        double moneyLeft = 0.0;

        switch (flower){
            case "Roses":
                if (count > 80){
                    rosePrice = (5 * count)*0.9;
                } else {
                    rosePrice = 5 * count;
                } moneyLeft = budget - rosePrice;
                if (budget >= rosePrice){
                    System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", count, flower, Math.abs(moneyLeft));
                } else {
                    System.out.printf("Not enough money, you need %.2f leva more.", Math.abs(moneyLeft));
                }
                break;
            case "Dahlias":
                if (count > 90){
                    dahliaPrice = (3.8 * count)*0.85;
                } else {
                    dahliaPrice = 3.8 * count;
                } moneyLeft = budget - dahliaPrice;
                if (budget >= dahliaPrice){
                    System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", count, flower, Math.abs(moneyLeft));
                } else {
                    System.out.printf("Not enough money, you need %.2f leva more.", Math.abs(moneyLeft));
                }
                break;
            case "Tulips":
                if (count > 80){
                    tulipsPrice = (2.8 * count)*0.85;
                } else {
                    tulipsPrice = 2.8 * count;
                } moneyLeft = budget - tulipsPrice;
                if (budget >= tulipsPrice){
                    System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", count, flower, Math.abs(moneyLeft));
                } else {
                    System.out.printf("Not enough money, you need %.2f leva more.", Math.abs(moneyLeft));
                }
                break;
            case "Narcissus":
                if (count < 120){
                    narcissusPrice = (3 * count)*1.15;
                } else {
                    narcissusPrice = 3 * count;
                } moneyLeft = budget - narcissusPrice;
                if (budget >= narcissusPrice){
                    System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", count, flower, Math.abs(moneyLeft));
                } else {
                    System.out.printf("Not enough money, you need %.2f leva more.", Math.abs(moneyLeft));
                }
                break;
            case "Gladiolus":
                if (count < 80){
                    gladiolusPrice = (2.5 * count)*1.2;
                } else {
                    gladiolusPrice = 2.5 * count;
                } moneyLeft = budget - gladiolusPrice;
                if (budget >= gladiolusPrice){
                    System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", count, flower, Math.abs(moneyLeft));
                } else {
                    System.out.printf("Not enough money, you need %.2f leva more.", Math.abs(moneyLeft));
                }
                break;
        }

    }
}
