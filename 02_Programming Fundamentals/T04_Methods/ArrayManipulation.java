package MethodsExercise06;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayManipulation {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        String input = scanner.nextLine();

        while (!"end".equals(input)) {

            String[] inputArray = input.split(" ");

            switch (inputArray[0]) {
                case "exchange":
                    int index = Integer.parseInt(inputArray[1]);
                    if (index < 0 || index > array.length-1){
                        System.out.println("Invalid index");
                    } else {
                        exchange(array, index);
                    }
                    break;
                case "max":
                    if ("even".equals(inputArray[1])){
                        // method
                    } else if ("odd".equals(inputArray[1])){
                        // method
                    }
                    break;
                case "min":
                    if ("even".equals(inputArray[1])){
                        // method
                    } else if ("odd".equals(inputArray[1])){
                        // method
                    }
                    break;
                case "first":
                    int countFirst = Integer.parseInt(inputArray[1]);
                    if (countFirst < 0 || countFirst-1 > array.length){
                        System.out.println("Invalid count");
                    }
                    // method
                    break;
                case "last":
                    int countSecond = Integer.parseInt(inputArray[1]);
                    if (countSecond < 0 || countSecond-1 > array.length){
                        System.out.println("Invalid count");
                    }
                    // method
                    break;
            }

            input = scanner.nextLine();
        }
    }
    private static void exchange(int[] array, int index){
        int[] firstHalf = new int[array.length - index];
        for (int i = 0; i < firstHalf.length; i++) {
            firstHalf[i] = array[i];
        }
        int[] secondHalf = new int[array.length - firstHalf.length];
        for (int i = 0; i < secondHalf.length; i++) {
            secondHalf[i] = array[(array.length-index+i)];
        }
        for (int i = 0; i < secondHalf.length; i++) {
            System.out.print(secondHalf[i] + " ");
        }
        for (int i = 0; i < firstHalf.length; i++) {
            System.out.print(firstHalf[i] + " ");
        }
    }
}
