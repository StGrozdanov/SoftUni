package TextProcessing;

import java.util.Scanner;

public class DigitsLettersCharsInString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        StringBuilder letters = new StringBuilder();
        StringBuilder digits = new StringBuilder();
        StringBuilder symbols = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char find = input.charAt(i);
            if (Character.isLetter(find)){
                letters.append(find);
            } else if (Character.isDigit(find)){
                digits.append(find);
            } else {
                symbols.append(find);
            }
        }
        System.out.println(digits);
        System.out.println(letters);
        System.out.println(symbols);
    }
}
