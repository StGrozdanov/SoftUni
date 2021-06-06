package MethodsExercise06;

import java.util.Scanner;

public class AddAndSubtract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());
        int num3 = Integer.parseInt(scanner.nextLine());

        int sum = sum(num1, num2);

        subtractTheSumAndPrint(sum, num3);

    }
    private static int sum(int num1, int num2){
        return num1 + num2;
    }
    public static void subtractTheSumAndPrint(int sum, int num3){
        int result = sum - num3;
        System.out.println(result);
    }
}
