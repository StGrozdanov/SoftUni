package ObjectsAndClasses09;

import java.math.BigInteger;
import java.util.Scanner;

public class SumBigNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigInteger biggie = new BigInteger(scanner.nextLine());
        BigInteger biggie2 = new BigInteger(scanner.nextLine());

        BigInteger sum = biggie.add(biggie2);

        System.out.println(sum);
    }
}
