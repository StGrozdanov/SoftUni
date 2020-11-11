package ProgrammingBasics.WhileCycle.Exercises;

import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double moneyNeeded = Double.parseDouble(scanner.nextLine());
        double cash = Double.parseDouble(scanner.nextLine());

        int spendDaysInRow = 0;
        double totalIncome = 0;
        int daysCounter = 0;

        while (totalIncome < moneyNeeded) {
            String action = scanner.nextLine();
            double income = Double.parseDouble(scanner.nextLine());
            if (action.equals("spend")) {
                daysCounter++;
                spendDaysInRow++;
                cash = cash - income;
                totalIncome = cash;
                if (totalIncome < 0){
                    totalIncome = 0;
                    cash = 0;
                }
            } else {
                daysCounter++;
                spendDaysInRow = 0;
                cash = cash + income;
                totalIncome = cash;
            }
            if (spendDaysInRow == 5) {
                break;
            }
        }
        if (totalIncome >= moneyNeeded) {
            System.out.printf("You saved the money for %d days.", daysCounter);
        } else {
            System.out.println("You can't save the money.");
            System.out.println(daysCounter);
        }
    }
}