package MethodsExercise06;

import java.util.Scanner;

public class PalindromeIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!"END".equals(input)){
            String[] array = input.split("");
            if (array.length == 1){
                System.out.println("true");
            } else if (array[0].equals(array[array.length - 1])){
                System.out.println("true");
            } else {
                System.out.println("false");
            }
            input = scanner.nextLine();
        }
    }
}
