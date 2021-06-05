package Arrays;

import java.util.Scanner;

public class SumEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String n = scanner.nextLine();
        String[] nAsString = n.split(" ");

        int[] array = new int[nAsString.length];
        int sum = 0;

        for (int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(nAsString[i]);
            if (array[i] % 2 == 0){
                sum += array[i];
            }
        }
        System.out.println(sum);
    }
}
