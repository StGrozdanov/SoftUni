package TestExams.Е05;

import java.util.Scanner;

public class P02_BookWorm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder builder = new StringBuilder(scanner.nextLine());

        char[][] field = initializeTheMatrix(scanner);

        int[] playerPosition = new int[2];
        for (int rows = 0; rows < field.length; rows++) {
            for (int cols = 0; cols < field[rows].length; cols++) {
                if (field[rows][cols] == 'P') {
                    playerPosition[0] = rows;
                    playerPosition[1] = cols;
                }
            }
        }

        String input = scanner.nextLine();

        while (!"end".equals(input)) {
            switch (input) {
                case "up":
                    moveThePlayer(playerPosition, field, -1, 0, builder);
                    break;
                case "down":
                    moveThePlayer(playerPosition, field, 1, 0, builder);
                    break;
                case "left":
                    moveThePlayer(playerPosition, field, 0, -1, builder);
                    break;
                case "right":
                    moveThePlayer(playerPosition, field, 0, 1, builder);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println(builder);
        printTheMatrix(field);
    }

    private static void moveThePlayer(int[] playerPosition, char[][] field, int rowModification, int colModification,
                                      StringBuilder builder) {
        int playerRow = playerPosition[0];
        int playerCol = playerPosition[1];
        int newPlayerRow = playerRow + rowModification;
        int newPlayerCol = playerCol + colModification;

        if (isInBounds(newPlayerRow, newPlayerCol, field)) {
            if (field[newPlayerRow][newPlayerCol] == '-') {
                field[playerRow][playerCol] = '-';
                field[newPlayerRow][newPlayerCol] = 'P';
                playerPosition[0] = newPlayerRow;
                playerPosition[1] = newPlayerCol;
            } else if (Character.isLetter(field[newPlayerRow][newPlayerCol])) {
                builder.append(field[newPlayerRow][newPlayerCol]);
                field[playerRow][playerCol] = '-';
                field[newPlayerRow][newPlayerCol] = 'P';
                playerPosition[0] = newPlayerRow;
                playerPosition[1] = newPlayerCol;
            }
        } else {
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }

    private static char[][] initializeTheMatrix(Scanner scanner) {
        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];
        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();
        }
        return matrix;
    }

    private static void printTheMatrix(char[][] matrix) {
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                System.out.print(matrix[rows][cols]);
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(int rows, int cols, char[][] matrix) {
        return rows >= 0 && rows < matrix.length && cols >= 0 && cols < matrix.length;
    }
}
