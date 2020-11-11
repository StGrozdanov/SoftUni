package ProgrammingBasics.AdvancedCycles.Exercise;

import java.util.Scanner;

public class EqualSumsEvenOddPosition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());

        for (int i = num1; i <= num2; i++) {
            String currentNum = "" + i;
            int oddSum = 0;
            int nonOddSum = 0;
            for (int j = 0; j < 6; j++) {
                int position = Integer.parseInt("" + currentNum.charAt(j));
                if (j % 2 == 0) {
                    nonOddSum += position;
                } else {
                    oddSum += position;
                }
            }
            if (nonOddSum == oddSum) {
                System.out.print(i + " ");
            }
        }
        }
    }
