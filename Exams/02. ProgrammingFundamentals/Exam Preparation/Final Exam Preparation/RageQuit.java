package FundamentalsFinalExamPreparation;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RageQuit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        StringBuilder repeat = new StringBuilder();
        StringBuilder unique = new StringBuilder();
        StringBuilder finalStr = new StringBuilder();
        String criteria = "[0-9]+";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(input);
        int num;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            String currentStr = String.valueOf(current).toUpperCase();
            if (!Character.isDigit(current)){
                repeat.append(currentStr);
                if (!unique.toString().contains(currentStr)){
                    unique.append(currentStr);
                }
            } else if (matcher.find()){
                num = Integer.parseInt(matcher.group());
                for (int j = 0; j < num; j++) {
                    finalStr.append(repeat);
                }
                repeat.setLength(0);
                if (num > 9){
                    i++;
                }
            }
        }
        System.out.printf("Unique symbols used: %d%n", unique.length());
        System.out.println(finalStr);
    }
}
