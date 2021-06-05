package FundamentalsFinalExamPreparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MirrorWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String criteria = "(@|#)(?<text1>[a-zA-Z]{3,})\\1\\1(?<text2>[a-zA-Z]{3,})\\1";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(input);

        List<String> mirrorWords = new ArrayList<>();
        int counter = 0;
        while (matcher.find()) {
            counter++;
            String delimiter = " <=> ";
            String firstWord = matcher.group("text1");
            String secondWord = matcher.group("text2");
            StringBuilder first = new StringBuilder(firstWord);
            StringBuilder second = new StringBuilder(secondWord);
            second.reverse();
            if (second.toString().equals(first.toString())) {
                second.reverse();
                first.append(delimiter);
                first.append(second);
                mirrorWords.add(String.valueOf(first));
            }
        }
        if (counter != 0) {
            System.out.printf("%d word pairs found!%n", counter);
        } else {
            System.out.println("No word pairs found!");
        }
        if (mirrorWords.size() != 0) {
            System.out.println("The mirror words are:");
            System.out.print(String.join(", ", mirrorWords));
        } else {
            System.out.println("No mirror words!");
        }
    }
}
