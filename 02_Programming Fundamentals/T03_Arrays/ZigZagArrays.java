package ArraysExercise;

import java.util.Scanner;

public class ZigZagArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String[] arr1 = new String[n];
        String[] arr2 = new String[n];

        for (int i = 0; i < arr1.length; i++){
            String[] input = scanner.nextLine().split(" ");
            if (i % 2 == 0){
                arr1[i] = input[0];
                arr2[i] = input[1];
            }else {
                arr2[i] = input[0];
                arr1[i] = input[1];
            }
        }
        System.out.println(String.join(" ", arr1));
        System.out.println(String.join(" ", arr2));
    }
}
