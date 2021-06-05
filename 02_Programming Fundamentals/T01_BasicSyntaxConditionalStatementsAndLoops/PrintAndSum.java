package SameOlShit;

import java.util.Scanner;

public class PrintAndSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int first = Integer.parseInt(scanner.nextLine());
        int second = Integer.parseInt(scanner.nextLine());

        int sum = 0;

        for (int i = first; i <= second; i++){
            sum += i;
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.printf("Sum: %d", sum);
    }
}
