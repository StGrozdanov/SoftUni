package FundamentalsFinalExamPreparation;

import java.util.Scanner;

public class SecretChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder msg = new StringBuilder(scanner.nextLine());
        String input = scanner.nextLine();

        while (!"Reveal".equals(input)) {
            String[] tokens = input.split(":\\|:");
            String cmd = tokens[0];
            switch (cmd) {
                case "InsertSpace":
                    int index = Integer.parseInt(tokens[1]);
                    msg.insert(index, " ");
                    System.out.println(msg);
                    break;
                case "Reverse":
                    String substring = tokens[1];
                    if (msg.toString().contains(substring)){
                        StringBuilder reversed = new StringBuilder();
                        reversed.append(substring);
                        reversed.reverse();
                        int startIndex = msg.indexOf(substring);
                        int endIndex = startIndex+substring.length();
                        msg.replace(startIndex, endIndex, "");
                        msg.append(reversed);
                        System.out.println(msg);
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "ChangeAll":
                    String sub = tokens[1];
                    String replace = tokens[2];
                    String newMsg = msg.toString().replace(sub, replace);
                    msg.setLength(0);
                    msg.append(newMsg);
                    System.out.println(msg);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.printf("You have a new text message: %s", msg);
    }
}
