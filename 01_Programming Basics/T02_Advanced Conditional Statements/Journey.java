package ProgrammingBasics.AdvancedConditionalStatements.AdvConditionsExercise;

import java.util.Scanner;

public class Journey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budget = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine();

        double expenses = 0.0;

        if (budget <= 100) {
            switch (season) {
                case "summer":
                    expenses = budget * 0.3;
                    System.out.println("Somewhere in Bulgaria");
                    System.out.printf("Camp - %.2f ", expenses);
                    break;
                case "winter":
                    expenses = budget * 0.7;
                    System.out.println("Somewhere in Bulgaria");
                    System.out.printf("Hotel - %.2f ", expenses);
                    break;
            }

        } else if (budget <= 1000) {
            switch (season) {
                case "summer":
                    expenses = budget * 0.4;
                    System.out.println("Somewhere in Balkans");
                    System.out.printf("Camp - %.2f ", expenses);
                    break;
                case "winter":
                    expenses = budget * 0.8;
                    System.out.println("Somewhere in Balkans");
                    System.out.printf("Hotel - %.2f ", expenses);
                    break;
            }
        } else {
                    expenses = budget * 0.9;
                    System.out.println("Somewhere in Europe");
                    System.out.printf("Hotel - %.2f ", expenses);
            }
        }

    }
