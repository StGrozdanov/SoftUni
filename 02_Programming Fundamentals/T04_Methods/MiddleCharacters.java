package MethodsExercise06;

import java.util.Scanner;

public class MiddleCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        findTheMiddleCharacter(text);

    }
    public static void findTheMiddleCharacter(String text){
        String[] array = text.split("");

        if (array.length % 2 == 0){
            System.out.println(array[array.length/2-1] + array[array.length/2]);
        } else {
            System.out.println(array[array.length/2]);
        }
        }
    }
