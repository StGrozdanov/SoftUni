package TextProcessing;

import java.util.Scanner;

public class CharacterMultiplierLubo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");
        String first = input[0];
        String second = input[1];

        int minLength = Math.min(first.length(), second.length());
        int maxLength = Math.max(first.length(), second.length());
        int sum = 0;

        for (int i = 0; i < maxLength; i++) {
            if (i < minLength){
                sum += first.charAt(i) * second.charAt(i);
            } else if (first.length() == maxLength){
                sum += first.charAt(i);
            } else {
                sum += second.charAt(i);
            }
        }
        System.out.println(sum);
    }
}
