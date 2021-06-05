    package FundamentalsFinalExamPreparation;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;

    public class AdAstra {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();
            String criteria = "(#|\\|)(?<itemName>[a-zA-Z\\s]+)\\1(?<expirationDate>[\\d]{2}\\/[\\d]{2}\\/[\\d]{2})\\1(?<calories>[\\d]+)\\1";
            Pattern pattern = Pattern.compile(criteria);
            Matcher matcher = pattern.matcher(input);
            double sum = 0;
            List<String> valid = new ArrayList<>();
            while(matcher.find()){
                String product = matcher.group("itemName");
                String expiration = matcher.group("expirationDate");
                int calories = Integer.parseInt(matcher.group("calories"));
                if (calories > 0 && calories <= 10000){
                    sum += calories;
                    String food = String.format("Item: %s, Best before: %s, Nutrition: %d%n", product, expiration, calories);
                    valid.add(food);
                }
            }
            double days;
            if (sum > 0) {
                days = Math.floor(sum / 2000);
                System.out.printf("You have food to last you for: %.0f days!%n", days);
                for (String s : valid) {
                    System.out.print(s);
                }
            } else {
                System.out.println("You have food to last you for: 0 days!");
            }
        }
    }
