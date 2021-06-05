package FundamentalsFinalExamPreparation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LettersChangeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> elements = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
        double number;
        double result = 0;
        StringBuilder num = new StringBuilder();
        List<Double> results = new ArrayList<>();

        for (String element : elements) {
            for (int i = 0; i < element.length(); i++) {
                char currentLetter = element.charAt(i);
                if (Character.isDigit(currentLetter)) {
                    num.append(currentLetter);
                }
            }
            number = Double.parseDouble(num.toString());
            for (int i = 0; i < element.length(); i++) {
                char currentLetter = element.charAt(i);
                char current = element.toLowerCase().charAt(i);
                int position = current - 96;
                if (i == 0 && Character.isLetter(current)) {
                    if (Character.isUpperCase(currentLetter)) {
                        result =  number / position;
                    } else {
                        result = number * position;
                    }
                }
                if (i != 0 && Character.isLetter(currentLetter)){
                    if (Character.isUpperCase(currentLetter)) {
                        result -= position;
                    } else {
                        result += position;
                    }
                }
            }
            results.add(result);
            num = new StringBuilder();
            result = 0;
        }
        for (double resulted : results){
            result += resulted;
        }
        System.out.printf("%.2f", result);
    }
}
