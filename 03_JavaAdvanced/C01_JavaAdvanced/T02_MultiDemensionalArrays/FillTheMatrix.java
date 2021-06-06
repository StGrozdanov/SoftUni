package T02_MultiDemensionalArrays.Exercise;

import java.util.Scanner;

public class FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");

        int size = Integer.parseInt(input[0]);
        String scheme = input[1];
        int[][] matrix = new int[size][size];

        int rows = 0;
        int columns = 0;
        int counter = 0;

        if (scheme.equals("A")) {
            while (counter != size * size) {
                counter++;
                if (rows == size) {
                    rows = 0;
                    columns++;
                }
                matrix[rows][columns] = counter;
                rows++;
            }
        } else if (scheme.equals("B")) {
            while (counter != size * size) {
                counter++;
                if (rows == size) {
                    columns++;
                    while (rows != 0) {
                        rows--;
                        matrix[rows][columns] = counter;
                        counter++;
                    }
                }
                if (rows == 0 && counter != 1 && counter < size * size) {
                    columns++;
                    matrix[rows][columns] = counter;
                    while (rows != size - 1) {
                        counter++;
                        rows++;
                        matrix[rows][columns] = counter;
                    }
                }
                if (counter > size * size) {
                    break;
                }
                matrix[rows][columns] = counter;
                rows++;
            }
        }
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
