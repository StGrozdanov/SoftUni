package TextProcessing;

import java.util.Scanner;

public class CaesarCypher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        StringBuilder strangeOutput = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            char newLetter = (char) (letter + 3);
            strangeOutput.append(newLetter);
        }
        System.out.println(strangeOutput);
    }
}
