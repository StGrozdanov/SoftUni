package FundamentalsFinalExamPreparation;

import java.util.Scanner;

public class Activation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder massage = new StringBuilder(scanner.nextLine());
        String input = scanner.nextLine();
        while (!"Decode".equals(input)) {
            String[] tokens = input.split("\\|");
            String cmd = tokens[0];
            switch (cmd) {
                case "Move":
                    int firstNLetters = Integer.parseInt(tokens[1]);
                    for (int i = 0; i < firstNLetters; i++) {
                        char currentLetter = massage.charAt(i);
                        massage.append(currentLetter);
                    }
                    massage.delete(0, firstNLetters);
                    break;
                case "Insert":
                    int index = Integer.parseInt(tokens[1]);
                    String value = tokens[2];
                    massage.insert(index, value);
                    break;
                case "ChangeAll":
                    String substring = tokens[1];
                    String replacement = tokens[2];
                    String result = massage.toString().replace(substring, replacement);
                    massage.setLength(0);
                    massage.append(result);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.printf("The decrypted message is: %s", massage);
    }
}
