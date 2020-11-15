package DataTypesAndVariables;

import java.util.Scanner;

public class SumDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String num = scanner.nextLine();

        int sum = 0;

        for (int i = 0; i < num.length(); i++){
            int digit = num.charAt(i) - 48;
            sum += digit;
        }
        System.out.println(sum);
    }
}
