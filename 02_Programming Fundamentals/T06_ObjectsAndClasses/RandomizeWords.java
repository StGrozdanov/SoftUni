package ObjectsAndClasses09;

import java.util.Random;
import java.util.Scanner;

public class RandomizeWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split(" ");

        Random random = new Random();

        for (int i = 0; i < words.length; i++) {
            int randomWord = random.nextInt(words.length);
            String temp = words[i];
            words[i] = words[randomWord];
            words[randomWord] = temp;
        }
        for (String hi:words){
            System.out.println(hi);
        }
    }
}
