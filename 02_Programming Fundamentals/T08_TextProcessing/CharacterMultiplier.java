package TextProcessing;

import java.util.Scanner;

public class CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");

        String first = input[0];
        String second = input[1];
        int sum = 0;

        if (first.length() == second.length()) {
            for (int i = 0; i < first.length(); i++) {
                char digit1 = first.charAt(i);
                char digit2 = second.charAt(i);
                sum += digit1 * digit2;
            }
        } else {
            int minLength = first.length();
            int maxLength = second.length();
            if (second.length() < first.length()) {
                minLength = second.length();
                maxLength = first.length();
            }
            for (int i = 0; i < minLength; i++) {
                char digit1 = first.charAt(i);
                char digit2 = second.charAt(i);
                sum += digit1 * digit2;
            }
            if (maxLength == first.length()) {
                for (int i = minLength; i < maxLength; i++) {
                    sum += first.charAt(i);
                }
            } else {
                for (int i = minLength; i < maxLength; i++) {
                    sum += second.charAt(i);
                }
            }
        }
        System.out.println(sum);
    }
}
