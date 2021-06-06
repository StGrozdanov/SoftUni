package Methods05;

import java.util.Scanner;

public class MultiplyEvensByOdds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("");

        int[] array = new int[input.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(input[i]);
        }

        System.out.println(MultiplyTheEvensByTheOdds(array));

    }
    private static int MultiplyTheEvensByTheOdds(int[] array){

        int evenSum = 0;
        int oddSum = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0){
                evenSum += array[i];
            } else {
                oddSum += array[i];
            }
        }
        
        return evenSum * oddSum;
    }
}
