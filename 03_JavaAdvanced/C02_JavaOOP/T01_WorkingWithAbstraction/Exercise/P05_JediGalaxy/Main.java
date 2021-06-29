package T01_WorkingWithAbstraction.Exercise.P05_JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] galaxy = initializeTheMatrix(scanner);

        String command = scanner.nextLine();
        long sum = 0;

        while (!command.equals("Let the Force be with you")) {
            int[] playerTokens = Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] enemyTokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();

            int enemyRow = enemyTokens[0];
            int enemyCol = enemyTokens[1];

            int playerRow = playerTokens[0];
            int playerCol = playerTokens[1];

            while (enemyRow >= 0 && enemyCol >= 0) {
                if (isInBounds(enemyRow, enemyCol, galaxy)) {
                    galaxy[enemyRow][enemyCol] = 0;
                }
                enemyRow--;
                enemyCol--;
            }
            while (playerRow >= 0 && playerCol < galaxy[0].length) {
                if (isInBounds(playerRow, playerCol, galaxy)) {
                    sum += galaxy[playerRow][playerCol];
                }
                playerCol++;
                playerRow--;
            }
            command = scanner.nextLine();
        }
        System.out.println(sum);
    }

    private static int[][] initializeTheMatrix(Scanner scanner) {
        int[] galaxy = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = galaxy[0];
        int cols = galaxy[1];

        int[][] matrix = new int[rows][cols];

        int value = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }

    private static boolean isInBounds(int row, int col, int[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
