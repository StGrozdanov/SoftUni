package T02_MultiDemensionalArrays.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class PrintDiagonalsOfSquareMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int firstDiagonal = 0;
        int firstHorizontal = 0;

        while (firstDiagonal != size && firstHorizontal != size){
            System.out.print(matrix[firstDiagonal][firstHorizontal] + " ");
            firstDiagonal++;
            firstHorizontal++;
        }

        int secondDiagonal = size - 1;
        int secondHorizontal = 0;

        System.out.println();
        while (secondDiagonal != -1 && secondHorizontal != size){
            System.out.print(matrix[secondDiagonal][secondHorizontal] + " ");
            secondDiagonal--;
            secondHorizontal++;
        }
    }
}
