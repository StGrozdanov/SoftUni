package DataTypesAndVariables;

import java.util.Scanner;

public class IntegerOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstNum = Integer.parseInt(scanner.nextLine());
        int secondNum = Integer.parseInt(scanner.nextLine());
        int thirdNum = Integer.parseInt(scanner.nextLine());
        int fourthNum = Integer.parseInt(scanner.nextLine());

        int sum = firstNum + secondNum;
        int divide = sum / thirdNum;
        int multiply = divide * fourthNum;

        System.out.println(multiply);

    }
}
