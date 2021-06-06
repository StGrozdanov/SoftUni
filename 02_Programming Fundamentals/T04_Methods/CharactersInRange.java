package MethodsExercise06;

import java.util.Scanner;

public class CharactersInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char word1 = scanner.next().charAt(0);
        char word2 = scanner.next().charAt(0);

        findWordsInBetween(word1, word2);

    }
    public static void findWordsInBetween(char word1, char word2) {
        if (word1 < word2) {
            for (char i = word1; i < word2; i++) { // вариант 2 е да не правим безсмислена проверка, а да го преведем, като
                // char i = word1 + 1, но ще ми се кара защото давам Int 1 към чар и затова кастваме: char i = (char) (word1 +1)
                if (i == word1) {
                    continue;
                }
                System.out.print(i + " ");
            }
        } else {
            for (char i = word2; i < word1; i++) {
                if (i == word2) {
                    continue;
                }
                System.out.print(i + " ");
            }
        }
    }
}
