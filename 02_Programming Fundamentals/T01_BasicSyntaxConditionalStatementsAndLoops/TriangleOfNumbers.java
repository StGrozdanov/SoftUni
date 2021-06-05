package SameOlShit;

import java.util.Scanner;

public class TriangleOfNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int counter = 1;

        for (int i = 1; i <= n; i++){
            for (int j = 0; j < i; j++){
                System.out.print(counter + " ");
            }
            counter++;
            System.out.println();
        }
    }
}
