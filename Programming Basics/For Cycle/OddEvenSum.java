package ProgrammingBasics.ForCycle;

import java.util.Scanner;

public class OddEvenSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());

        int sumEven =0;
        int sumOdd = 0;

        for (int i = 1; i <= num; i++){
            int result = Integer.parseInt(scanner.nextLine());
            if (i % 2 == 0){
                sumEven += result;
            } else {
                sumOdd += result;
            }
        } if (sumEven == sumOdd){
            System.out.printf("Yes%n");
            System.out.printf("Sum = %d", sumEven);
        } else {
            System.out.printf("No%n");
            System.out.printf("Diff = %d", Math.abs(sumEven-sumOdd));
        }
    }
}
