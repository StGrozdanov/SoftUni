package MethodsExercise06;

import java.util.Scanner;

public class TopNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        checkIfWeHaveTopNumber(n);

    }

    public static void checkIfWeHaveTopNumber(int n) {
        for (int i = 10; i <= n; i++) {
                String hi = String.valueOf(i);
                String[] intAsString = hi.split("");
                int[] array = new int[intAsString.length];
                for (int j = 0; j < array.length; j++) {
                    array[j] = Integer.parseInt(intAsString[j]);
                }
                int sumOfDigits = 0;
                boolean holdsOddDigit = false;
                for (int j = 0; j < array.length; j++) {
                    sumOfDigits += array[j];
                    if (!(array[j] % 2 == 0)) {
                        holdsOddDigit = true;
                    }
                }
                if (holdsOddDigit && sumOfDigits % 8 == 0) {
                    System.out.println(i);
                }
            }
        }
    }