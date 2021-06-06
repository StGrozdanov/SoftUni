package T01_StacksAndQuesues.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class MatchingBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayDeque<Integer> expressions = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            String current = String.valueOf(currentChar);
            if (current.equals("(")) {
                expressions.push(i);
            } else if (current.equals((")"))) {
                int index = expressions.pop();
                String contents = input.substring(index, i + 1);
                System.out.println(contents);
            }
        }
    }
}
