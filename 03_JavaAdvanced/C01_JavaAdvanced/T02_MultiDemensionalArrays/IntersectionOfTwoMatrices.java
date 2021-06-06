package T02_MultiDemensionalArrays.Lab;

import java.util.Scanner;

public class IntersectionOfTwoMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int columns = Integer.parseInt(scanner.nextLine());

        char[][] matrix1 = new char[rows][columns];
        char[][] matrix2 = new char[rows][columns];

        charMatrix(scanner, rows, matrix1);
        charMatrix(scanner, rows, matrix2);

        for (int row = 0; row < matrix1.length; row++) {
            for (int column = 0; column < matrix1[row].length; column++) {
                if (matrix1[row][column] == matrix2[row][column]) {
                    System.out.print(matrix1[row][column] + " ");
                } else {
                    System.out.print("*" + " ");
                }
            }
            System.out.println();
        }
    }

    private static void charMatrix(Scanner scanner, int rows, char[][] A) {
        for (int row = 0; row < rows; row++) {
            A[row] = scanner.nextLine().replaceAll(" ", "").toCharArray();
        }
    }
}
