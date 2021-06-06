package TextProcessing;

import java.math.BigDecimal;
import java.util.Scanner;

public class MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine();
        int second = Integer.parseInt(scanner.nextLine());

        BigDecimal firstOne = new BigDecimal(first);
        BigDecimal secondOne = new BigDecimal(second);

        BigDecimal result = firstOne.multiply(secondOne);

        System.out.println(result);

    }
}
