package FundamentalsFinalExam;

import java.util.Scanner;

public class problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder text = new StringBuilder(scanner.nextLine());
        String input = scanner.nextLine();
        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String cmd = tokens[0];
            switch (cmd) {
                case "Translate":
                    String character = tokens[1];
                    String replacement = tokens[2];
                    String translateResult = text.toString().replace(character, replacement);
                    text.setLength(0);
                    text.append(translateResult);
                    System.out.println(text);
                    break;
                case "Includes":
                    String substring = tokens[1];
                    if (text.toString().contains(substring)){
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "Start":
                    String start = tokens[1];
                    String[] token = text.toString().split("\\s+");
                    String original = token[0];
                    if (start.equals(original)){
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "Lowercase":
                    String lowerResult = text.toString().toLowerCase();
                    text.setLength(0);
                    text.append(lowerResult);
                    System.out.println(text);
                    break;
                case "FindIndex":
                    String indexOfi = tokens[1];
                    System.out.println(text.lastIndexOf(indexOfi));
                    break;
                case "Remove":
                    int startIndex = Integer.parseInt(tokens[1]);
                    int count = Integer.parseInt(tokens[2]);
                    text.replace(startIndex, startIndex+(count), "");
                    System.out.println(text);
                    break;
            }
            input = scanner.nextLine();
        }

    }
}
