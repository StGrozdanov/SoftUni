package TextProcessing;

import java.util.Scanner;

public class StringExplosion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        StringBuilder whatIsLeft = new StringBuilder();
        int power = 0;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current == '>') {
                whatIsLeft.append(current);
                power += Integer.parseInt(String.valueOf(input.charAt(i + 1)));
            } else if (power == 0) {
                whatIsLeft.append(current);
            } else {
                power--;
            }
        }
        System.out.println(whatIsLeft);
    }
}
