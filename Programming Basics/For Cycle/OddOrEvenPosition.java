package ProgrammingBasics.ForCycle.Exercise;

import java.util.Scanner;

public class OddOrEvenPosition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());

        double oddMax = -1000000000.0;
        double oddMin = 1000000000.0;
        double evenMax = -1000000000.0;
        double evenMin = 1000000000.0;

        double OddSum = 0;
        double OddMin = 0;
        double OddMax = 0;
        double EvenSum = 0;
        double EvenMin = 0;
        double EvenMax = 0;

        for (int i = 1; i <= num; i++) {
            double num2 = Double.parseDouble(scanner.nextLine());
            if (i % 2 == 0) {
                EvenSum += num2;
                if (evenMin > num2) {
                    evenMin = num2;
                    EvenMin = num2;
                }if (evenMax < num2) {
                    evenMax = num2;
                    EvenMax = num2;
                }
            } else {
                OddSum += num2;
                if (oddMin > num2) {
                    oddMin = num2;
                    OddMin = num2;
                } if (oddMax < num2) {
                    oddMax = num2;
                    OddMax = num2;
                }
            }
        }
        System.out.printf("OddSum=%.2f,%n", OddSum);
        if (OddMin == 0) {
            System.out.println("OddMin=No,");
        } else {
            System.out.printf("OddMin=%.2f,%n", OddMin);
        }
        if (OddMax == 0) {
            System.out.println("OddMax=No,");
        } else {
            System.out.printf("OddMax=%.2f,%n", OddMax);
        }
        System.out.printf("EvenSum=%.2f,%n", EvenSum);
        if (EvenMin == 0) {
            System.out.println("EvenMin=No,");
        } else {
            System.out.printf("EvenMin=%.2f,%n", EvenMin);
        }
        if (EvenMax == 0) {
            System.out.println("EvenMax=No");
        } else {
            System.out.printf("EvenMax=%.2f", EvenMax);
        }
    }
}