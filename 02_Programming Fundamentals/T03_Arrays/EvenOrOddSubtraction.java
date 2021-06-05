package Arrays;

import java.util.Scanner;

public class EvenOrOddSubtraction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");

        int[] numbers = new int[input.length];
        int sumEven = 0;
        int sumOdd = 0;

        for (int i = 0; i < numbers.length; i++){
            numbers[i] = Integer.parseInt(input[i]);
            if (numbers[i] % 2 == 0){
                sumEven += numbers[i];
            }else {
                sumOdd += numbers[i];
            }
        }
        System.out.println(sumEven - sumOdd);
    }
}
