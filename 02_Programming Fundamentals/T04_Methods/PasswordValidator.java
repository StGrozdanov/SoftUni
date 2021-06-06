package MethodsExercise06;

import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        if (checkPasswordLength(word) == 1) {
            System.out.println("Password must be between 6 and 10 characters");
        }
        if (checkForChars(word) == 1) {
            System.out.println("Password must consist only of letters and digits");
        }
        if (checkForDigits(word) == 1) {
            System.out.println("Password must have at least 2 digits");
        }
        if (checkForDigits(word) == 0 && checkForChars(word) == 0 && checkPasswordLength(word) == 0) {
            System.out.println("Password is valid");
        }

    }

    private static int checkForChars(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && !Character.isDigit(c)) { // можем да заменим първите
                //с функцията !Character.isLetter(c) !!!
                return 1;
            }
        }
        return 0;
    }

    private static int checkForDigits(String word) {
        int counter = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isDigit(c)) {
                counter++;
            }
        }
        if (counter < 2) {
            return 1;
        }
        return 0;
    }

    private static int checkPasswordLength(String word) {
        if (word.length() < 6 || word.length() > 10) {
            return 1;
        }
        return 0;
    }
}
