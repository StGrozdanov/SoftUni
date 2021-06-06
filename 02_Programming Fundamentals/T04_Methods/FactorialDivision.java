package MethodsExercise06;

import java.util.Scanner;

public class FactorialDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());

        double division = 1.0 * factorialOfNum(num1) / factorialOfNum(num2);

        System.out.printf("%.2f", division);

    }
    private static long factorialOfNum(int num){
        long factorial = 1;
        for (int i = 1; i <= num; i++){
            factorial *= i;
        }
        return factorial;
    }
}
