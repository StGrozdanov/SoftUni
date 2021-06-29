package T01_WorkingWithAbstraction.Lab;

import java.util.Scanner;

public class P01_RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        for (int starCount = 1; starCount <= size; starCount++) {
            printRow(size, starCount);
        }
        for (int starCount = size - 1; starCount >= 1; starCount--) {
            printRow(size, starCount);
        }
    }
    static void printRow(int figureSize, int starCount) {
        for (int i = 0; i < figureSize - starCount; i++)
            System.out.print(" ");
        for (int col = 1; col < starCount; col++) {
            System.out.print("* ");
        }
        System.out.println("*");
    }
}
