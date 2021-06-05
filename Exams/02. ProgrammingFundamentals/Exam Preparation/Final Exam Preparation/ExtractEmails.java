package FundamentalsFinalExamPreparation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");
        String criteria = "^[a-zA-Z0-9]+[\\.\\-_]?[a-zA-Z0-9]+[@][a-zA-Z]{2,}([-][a-zA-Z]{2,})?[.][a-zA-Z]{2,}([-][a-zA-Z])?([.][a-zA-Z]{2,})?";
        Pattern pattern = Pattern.compile(criteria);
        for (String s : input) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()){
                String email = matcher.group();
                System.out.println(email);
            }
        }
    }
}
