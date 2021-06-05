package FundamentalsFinalExamPreparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String criteria = "(=|\\/)(?<destination>[A-Z][a-zA-Z]{2,})\\1";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(input);
        List<String> destinations = new ArrayList<>();
        int points = 0;

        while (matcher.find()){
            String location = matcher.group("destination");
            destinations.add(location);
            points += location.length();
        }
        System.out.println("Destinations: " + String.join(", ", destinations));
        System.out.printf("Travel Points: %d", points);
    }
}
