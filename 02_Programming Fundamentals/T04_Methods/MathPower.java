package Methods05;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MathPower {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double number = Double.parseDouble(scanner.nextLine());
        int raisedBy = Integer.parseInt(scanner.nextLine());

        double mathPower = mathPow(number, raisedBy);
        System.out.println(new DecimalFormat("0.####").format(mathPower));

    }
    private static double mathPow(double number, int raisedBy){
        return Math.pow(number, raisedBy);
    }
}
