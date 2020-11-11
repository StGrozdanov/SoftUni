package ProgrammingBasics.AdvancedConditionalStatements.AdvConditionsExercise;

import java.util.Scanner;

public class OperationsBetweenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());
        String operation = scanner.nextLine();

        double result = 0;

        switch (operation){
            case "+":
                result = num1 + num2;
                if (result % 2 == 0){
                    System.out.printf("%d %s %d = %.0f - even", num1, operation, num2, result);
                } else {
                    System.out.printf("%d %s %d = %.0f - odd", num1, operation, num2, result);
                }
                break;
            case "-":
                result = num1 - num2;
                if (result % 2 == 0){
                    System.out.printf("%d %s %d = %.0f - even", num1, operation, num2, result);
                } else {
                    System.out.printf("%d %s %d = %.0f - odd", num1, operation, num2, result);
                }
                break;
            case "*":
                result = num1 * num2;
                if (result % 2 == 0){
                    System.out.printf("%d %s %d = %.0f - even", num1, operation, num2, result);
                } else {
                    System.out.printf("%d %s %d = %.0f - odd", num1, operation, num2, result);
                }
                break;
            case "/":
                if (num2 == 0){
                    System.out.printf("Cannot divide %d by zero", num1);
                } else {
                    double divResult = 1.0 * num1 / num2;
                    System.out.printf("%d / %d = %.2f", num1, num2, divResult);
                }
                break;
            case "%":
                if (num2 == 0){
                    System.out.printf("Cannot divide %d by zero", num1);
                } else {
                    result = num1 % num2;
                    System.out.printf("%d %% %d = %.0f", num1, num2, result);
                }
                break;
        }

    }
}
