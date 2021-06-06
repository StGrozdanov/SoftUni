package RegularExpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftUniBarIncome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = "%(?<name>[A-Z][a-z]+)%([^|$%.]?)+<(?<product>[\\w]+)>([^|$%.]?)+\\|(?<count>[\\d]+)\\|([^|$%.\\d]?)+(?<price>[\\d]+\\.?[\\d]+?)\\$";
        // тук ми е казано да ескейпвам всичко различно от определени символи, обаче аз се изхитрих понеже
        // в определени случаи стакват цифри с цифри, символ със символ и т.н. и аз просто си ги вкарвам
        // вътре в ексепшъна, въпреки че по условие не трябва да е така и в тоя случай правилното е да
        // си сложа lazy ?, която означава хвани ми възможно най-малко инфо и така няма да стаква символ
        // със символ - слагам си след условието ([^|$%.]*?) * и ?

        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile(regex);

        double totalIncome = 0;

        while(!"end of shift".equals(input)){
            Matcher valid = pattern.matcher(input);
            while (valid.find()){
                String name = valid.group("name");
                String product = valid.group("product");
                int quantity = Integer.parseInt(valid.group("count"));
                double price = Double.parseDouble(valid.group("price"));
                double totalPrice = quantity * price;
                totalIncome += totalPrice;
                System.out.printf("%s: %s - %.2f%n", name, product, totalPrice);
            }
            input = scanner.nextLine();
        }
        System.out.printf("Total income: %.2f", totalIncome);
    }
}
