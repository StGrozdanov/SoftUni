package TextProcessing;
import java.util.Scanner;

public class textFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] bannedWord = scanner.nextLine().split(", ");
        String text = scanner.nextLine();

        for (String banWord : bannedWord) {
            if (text.contains(banWord)) {
                text = text.replace(banWord, repeatString(banWord.length()));
               }
        }
        System.out.println(text);
    }

    private static String repeatString(int length) {
        StringBuilder replace = new StringBuilder();
        for (int i = 0; i < length; i++) {
            replace.append("*");
        }
        return replace.toString();
    }
}
