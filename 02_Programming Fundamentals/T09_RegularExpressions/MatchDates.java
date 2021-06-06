package RegularExpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchDates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String expression = "(?<day>\\d{2})([./-])(?<month>[A-Z][a-z]{2})\\2(?<year>\\d{4})";
        String text = scanner.nextLine();

        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.print("Day: " + matcher.group("day") + ", ");
            System.out.print("Month: " + matcher.group("month") + ", ");
            System.out.print("Year: " + matcher.group("year"));
            System.out.println();
        }
    }
}
