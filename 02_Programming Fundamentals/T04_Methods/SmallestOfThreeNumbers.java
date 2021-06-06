package MethodsExercise06;

import java.util.Scanner;

public class SmallestOfThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());
        int num3 = Integer.parseInt(scanner.nextLine());

        SmallestNum(num1, num2, num3);

    }
    public static void SmallestNum(int num1, int num2, int num3){
        int smallestNumber;
        if (num1 < num2 && num1 < num3){
            smallestNumber = num1;
        }else if (num2 < num1 && num2 < num3){
            smallestNumber = num2;
        }else {
            smallestNumber = num3;
        }
        System.out.println(smallestNumber);
    }
}
