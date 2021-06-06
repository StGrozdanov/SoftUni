package Methods05;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MathOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double num1 = Double.parseDouble(scanner.nextLine());
        String method = scanner.nextLine();
        double num2 = Double.parseDouble(scanner.nextLine());

        double result = mathOperationsByStringBetweenFirstAndSecondNum(num1, method, num2);

        System.out.println(new DecimalFormat("0.####").format(result));

    }

    private static double mathOperationsByStringBetweenFirstAndSecondNum(double num1, String method, double num2){
        double result = 0;
        switch (method) {
            case "+":
                result = num1 + num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }
}

