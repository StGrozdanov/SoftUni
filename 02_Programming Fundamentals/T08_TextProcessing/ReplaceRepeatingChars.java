package TextProcessing;

import java.util.Scanner;

public class ReplaceRepeatingChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (i == input.length()-1){
                result.append(input.charAt(i));
                break;
            } else if (input.charAt(i) == input.charAt(i+1)) {
                continue;
            }
                result.append(input.charAt(i));
        }
        System.out.println(result);
    }
}
