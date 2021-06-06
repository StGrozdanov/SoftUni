package T03_SetsAndMapsAdvanced.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, String> phonebook = new LinkedHashMap<>();

        while (!"search".equals(input)) {
            String[] tokens = input.split("-");
            String contact = tokens[0];
            String number = tokens[1];

            phonebook.put(contact, number);

            input = scanner.nextLine();
        }

        input = scanner.nextLine();

        while (!"stop".equals(input)) {
            if (phonebook.containsKey(input)) {
                String number = phonebook.get(input);
                System.out.printf("%s -> %s%n", input, number);
            } else {
                System.out.printf("Contact %s does not exist.%n", input);
            }
            input = scanner.nextLine();
        }
    }
}
