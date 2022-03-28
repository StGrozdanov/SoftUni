package ProgrammingBasics.ForCycle;

import java.util.Scanner;

public class LeftAndRightSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        int sumLeft = 0;
        int sumRight = 0;

        for (int i = 0; i < num; i++){
            int left = Integer.parseInt(scanner.nextLine());
            sumLeft += left;
        }
        for (int i = 0; i < num; i++){
            int right = Integer.parseInt(scanner.nextLine());
            sumRight += right;
        }
        if (sumLeft == sumRight){
            System.out.printf("Yes, sum = %d", sumLeft);
        } else {
            System.out.printf("No, diff = %d", Math.abs(sumLeft - sumRight));
        }
    }
}
