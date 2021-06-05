package SameOlShit;

import java.util.Scanner;

public class StrongNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String num = scanner.nextLine();
        int sum = 0;

        for (int i = 0; i < num.length(); i++){
            int digit = num.charAt(i) - 48;
            int factorial = 1;
        for (int j = digit; j >= 1; j--){
            factorial *= j;
        }
        sum += factorial;
        }

        int number = Integer.parseInt(num);

        if (sum == number){
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
