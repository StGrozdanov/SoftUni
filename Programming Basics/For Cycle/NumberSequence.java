package ProgrammingBasics.ForCycle;

import java.util.Scanner;

public class NumberSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++){
            int num = Integer.parseInt(scanner.nextLine());
            if (min > num){
                min = num;
            }
            if (max < num){
                max = num;
            }

        }
        System.out.printf("Max number: %d%n",max);
        System.out.printf("Min number: %d",min);

    }
}
