package ProgrammingBasics.AdvancedCycles;

import java.util.Scanner;

public class Trip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String destination = scanner.nextLine();

        while (!destination.equals("End")) {
            double moneyNeeded = Double.parseDouble(scanner.nextLine());
            double sum = 0;
            while (moneyNeeded > sum) {
                double invest = Double.parseDouble(scanner.nextLine());
                sum += invest;
            }
            System.out.printf("Going to %s!%n", destination);
            destination = scanner.nextLine();
        }
    }
}