package Methods05;

import java.util.Scanner;

public class Calculations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String method = scanner.nextLine();
        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());

        mathOperationsByStringBetweenFirstAndSecondNum(method, num1, num2);

        }

    public static void mathOperationsByStringBetweenFirstAndSecondNum(String method, int num1, int num2){
        int result;
        switch (method) {
            case "add":
                result = num1 + num2;
                System.out.println(result);
                break;
            case "multiply":
                result = num1 * num2;
                System.out.println(result);
                break;
            case "subtract":
                result = num1 - num2;
                System.out.println(result);
                break;
            case "divide":
                result = num1 / num2;
                System.out.println(result);
                break;
        }
    }
}
