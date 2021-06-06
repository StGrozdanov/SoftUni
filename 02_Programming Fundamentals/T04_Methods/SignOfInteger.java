package Methods05;

import java.util.Scanner;

public class SignOfInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        integerSign(Integer.parseInt(scanner.nextLine()));
    }
    public static void integerSign(int input) {
        if (input > 0){
            System.out.printf("The number %d is positive.", input);
        } else if (input == 0){
            System.out.println("The number 0 is zero.");
        } else {
            System.out.printf("The number %d is negative.", input);
        }
    }
}
