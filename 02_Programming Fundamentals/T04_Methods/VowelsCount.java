package MethodsExercise06;

import java.util.Scanner;

public class VowelsCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        findVowels(word);

    }
    public static void findVowels(String word){
        int counter = 0;
        char c;
        for (int i = 0; i < word.length(); i++) {
             c = word.charAt(i);
             if (c == 65 || c == 69 || c == 73 || c == 79 || c == 85 ||
                     c == 97 || c == 101 || c == 105 || c == 111 || c == 117){
                 counter++;
             }
        }
        System.out.println(counter);
    }
}
