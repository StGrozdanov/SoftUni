package T03_SetsAndMapsAdvanced.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FixEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, String> mailBook = new LinkedHashMap<>();
        int counter = 0;
        String user = "";

        while (!"stop".equals(input)){
            counter++;
            if (counter % 2 == 0){
                if (!input.endsWith("com") && !input.endsWith("us") && !input.endsWith("uk")){
                    mailBook.putIfAbsent(user, input);
                }
            } else {
                user = input;
            }
            input = scanner.nextLine();
        }
        mailBook.forEach((key, value) -> System.out.printf("%s -> %s%n", key, value));
    }
}
