package TextProcessing;

import java.util.Scanner;

public class ExtractFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\\\");
        String[] information = input[input.length-1].split("\\.");
        String fileName = information[0];
        String extension = information[1];

        System.out.printf("File name: %s%n", fileName);
        System.out.printf("File extension: %s", extension);
    }
}
