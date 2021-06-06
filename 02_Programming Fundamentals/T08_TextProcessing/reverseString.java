package TextProcessing;

import java.util.Scanner;

public class reverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        StringBuilder sb = new StringBuilder();

        while (!"end".equals(input)) {
            sb.append(input).reverse();
            sb.insert(0, input + " = ");
            System.out.println(sb);
            sb.setLength(0);
            input = scanner.nextLine();
        }
    }
}
