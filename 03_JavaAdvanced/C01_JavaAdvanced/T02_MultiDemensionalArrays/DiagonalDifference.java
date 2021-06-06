package T02_MultiDemensionalArrays.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int columnsAndRows = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[columnsAndRows][columnsAndRows];
        for (int r = 0; r < matrix.length; r++) {
            matrix[r] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int firstDiagonalSum = 0;
        //first diagonal
        for (int i = 0; i < matrix.length; i++) {
            firstDiagonalSum += matrix[i][i];
        }
        int secondDiagonalSum = 0;
        //second diagonal
        int rows = 0;
        int cols = matrix.length-1;
        while (rows != matrix.length){
            secondDiagonalSum += matrix[rows][cols];
            cols--;
            rows++;
        }
        System.out.println(Math.abs(firstDiagonalSum - secondDiagonalSum));
    }
}
