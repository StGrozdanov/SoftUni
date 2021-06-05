package Arrays;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");

        int[] numbers = new int[input.length];

        for (int i = 0; i < numbers.length; i++){
            numbers[i] = Integer.parseInt(input[i]);
        }
        while(numbers.length > 1){
            int[] temp = new int[numbers.length - 1];
            for (int i = 0; i < temp.length; i++){
                temp[i] = numbers[i] + numbers[i+1];
            }
            numbers = temp;
        }
        System.out.println(numbers[0]);
    }
}
