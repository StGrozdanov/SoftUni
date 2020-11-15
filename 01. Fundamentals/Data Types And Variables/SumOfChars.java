package DataTypesAndVariables;

import java.util.Scanner;

public class SumOfChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());

        int sum = 0;

        for (int i = 1; i <= num; i++){
            String letter = scanner.nextLine();
            for (int j = 0; j < letter.length(); j++){
                int digit = letter.charAt(j);
                sum += digit;
            }
        }
        System.out.printf("The sum equals: %d", sum);
    }
}
