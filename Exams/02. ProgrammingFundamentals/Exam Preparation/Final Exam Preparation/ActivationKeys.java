package FundamentalsFinalExamPreparation;

import java.util.Scanner;

public class ActivationKeys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String key = scanner.nextLine();
        String input = scanner.nextLine();
        StringBuilder newKey = new StringBuilder();
        newKey.append(key);

        while (!"Generate".equals(input)) {
            String[] tokens = input.split(">>>");
            String cmd = tokens[0];
            switch (cmd) {
                case "Contains":
                    String substring = tokens[1];
                    if (String.valueOf(newKey).contains(substring)) {
                        System.out.printf("%s contains %s%n", newKey, substring);
                    } else {
                        System.out.println("Substring not found!");
                    }
                    break;
                case "Flip":
                    String pattern = tokens[1];
                    int startIndex = Integer.parseInt(tokens[2]);
                    int endIndex = Integer.parseInt(tokens[3]);
                    if ("Upper".equals(pattern)) {
                        String flip = newKey.substring(startIndex, endIndex).toUpperCase();
                        newKey.replace(startIndex, endIndex, flip);
                    } else if ("Lower".equals(pattern)) {
                        String flip = newKey.substring(startIndex, endIndex).toLowerCase();
                        newKey.replace(startIndex, endIndex, flip);
                    }
                    System.out.println(newKey);
                    break;
                case "Slice":
                    int start = Integer.parseInt(tokens[1]);
                    int end = Integer.parseInt(tokens[2]);
                    newKey.delete(start, end);
                    System.out.println(newKey);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Your activation key is: %s", newKey);
    }
}
