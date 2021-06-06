package TextProcessing;

import java.util.Scanner;

public class ValidUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] username = scanner.nextLine().split(", ");
        StringBuilder validUsernames = new StringBuilder();

        for (String currentUsername : username) {
            if (currentUsername.length() >= 3 && currentUsername.length() <= 16) {
                for (int j = 0; j < currentUsername.length(); j++) {
                    char find = currentUsername.charAt(j);
                    if (Character.isDigit(find) || Character.isLetter(find) || currentUsername.contains("-")
                            || currentUsername.contains("_")) {
                        validUsernames.append(find);
                    }
                }
                if (currentUsername.length() == validUsernames.length()){
                    System.out.println(validUsernames);
                }
            }
            validUsernames.setLength(0);
        }
    }
}
