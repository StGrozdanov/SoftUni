package Methods05;

import java.util.Scanner;

public class GreaterOfTwoValues {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String valuesType = scanner.nextLine();

        switch (valuesType) {
            case "int":
                int firstInt = Integer.parseInt(scanner.nextLine());
                int secondInt = Integer.parseInt(scanner.nextLine());
                System.out.println(greaterValue(firstInt, secondInt));
                break;
            case "char":
                char firstChar = scanner.next().charAt(0);
                char secondChar = scanner.next().charAt(0);
                System.out.println(greaterValue(firstChar, secondChar));
                break;
            case "string":
                String firstString = scanner.nextLine();
                String secondString = scanner.nextLine();
                System.out.println(greaterValue(firstString, secondString));
                break;
        }

    }
    private static int greaterValue(int first, int second){
        if (first > second){
            return first;
        }
        return second;
    }
    private static char greaterValue(char first, char second){
        if (first > second){
            return first;
        }
        return second;
    }
    private static String greaterValue(String first, String second){
        if (first.compareTo(second) >= 0){
            return first;
        }
        return second;
    }
}
