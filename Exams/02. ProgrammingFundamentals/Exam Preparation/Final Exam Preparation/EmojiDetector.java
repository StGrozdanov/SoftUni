package exam01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String emoji = "([:]{2}|[*]{2})(?<emoji>[A-Z][a-z]{2,})\\1";
        String threshold = "[0-9]";

        Pattern patternNum = Pattern.compile(threshold);
        Pattern emojiPattern = Pattern.compile(emoji);
        Matcher numberMatch = patternNum.matcher(text);

        long target = 1;
        while (numberMatch.find()) {
            target *= Integer.parseInt(numberMatch.group());
        }

        int counter = 0;
        List<String> coolEmoji = new ArrayList<>();
        Matcher emojiMatch = emojiPattern.matcher(text);
        while (emojiMatch.find()) {
            counter++;
            long coolness = 0;
            for (int i = 0; i < emojiMatch.group().length(); i++) {
                char current = emojiMatch.group().charAt(i);
                if (Character.isLetter(current)) {
                    coolness += current;
                }
            }
            if (coolness > target) {
                coolEmoji.add(emojiMatch.group());
            }
        }
        System.out.printf("Cool threshold: %d%n", target);
        System.out.printf("%d emojis found in the text. The cool ones are:%n", counter);
        if (coolEmoji.size() > 0) {
            for (String s : coolEmoji) {
                System.out.println(s);
            }
        }
    }
}
