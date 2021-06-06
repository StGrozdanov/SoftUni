package Methods05;

import java.util.Scanner;

public class RepeatString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        int repetitionsCount = Integer.parseInt(scanner.nextLine());

        String repeatString = repeated(text, repetitionsCount);

        System.out.println(repeatString);

    }

    private static String repeated(String text, int repetitionsCount){
        String[] repetitions = new String[repetitionsCount];

        for (int i = 0; i < repetitionsCount; i++) {
            repetitions[i] = text;
        }
        return String.join("", repetitions);
    }

}
