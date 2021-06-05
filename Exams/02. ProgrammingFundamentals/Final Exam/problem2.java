package FundamentalsFinalExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String criteria = "(\\*|@)(?<Tag>[A-Z][a-z]{2,})\\1: (?<g1>[\\[A-Za-z\\]]+)\\|(?<g2>[\\[A-Za-z\\]]+)\\|(?<g3>[\\[A-Za-z\\]]+)\\|$";
        Pattern pattern = Pattern.compile(criteria);
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String tag = matcher.group("Tag");
                String g1 = matcher.group("g1").replace("[", "").replace("]", "");
                String g2 = matcher.group("g2").replace("[", "").replace("]", "");
                String g3 = matcher.group("g3").replace("[", "").replace("]", "");
                String code1 = findCode(g1);
                String code2 = findCode(g2);
                String code3 = findCode(g3);
                String delimit = " ";
                StringBuilder result = new StringBuilder();
                result.append(code1);
                result.append(delimit);
                result.append(code2);
                result.append(delimit);
                result.append(code3);
                System.out.printf("%s: %s%n", tag, result);
            } else {
                System.out.println("Valid message not found!");
            }

        }

    }

    private static String findCode(String g1) {
        String result = "";
        for (int i = 0; i < g1.length(); i++) {
            char current = g1.charAt(i);
            int num = (int) current;
            String value = "" + num;
            result += value;
        }
        return result;
    }
}
