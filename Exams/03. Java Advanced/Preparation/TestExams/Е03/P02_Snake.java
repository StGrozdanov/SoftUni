package TestExams.Е03;

import java.util.Scanner;

public class P02_Snake {

    static int foodCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] field = initializeTheMatrix(scanner);
        int[] snakePosition = new int[2];

        for (int rows = 0; rows < field.length; rows++) {
            for (int cols = 0; cols < field[rows].length; cols++) {
                if (field[rows][cols] == 'S') {
                    snakePosition[0] = rows;
                    snakePosition[1] = cols;
                }
            }
        }
        String input = scanner.nextLine();
        boolean gameOver = false;

        while (!gameOver && foodCount < 10) {
            switch (input) {
                case "up":
                    gameOver = moveTheSnake(snakePosition, field, -1, 0);
                    break;
                case "down":
                    gameOver = moveTheSnake(snakePosition, field, 1, 0);
                    break;
                case "left":
                    gameOver = moveTheSnake(snakePosition, field, 0, -1);
                    break;
                case "right":
                    gameOver = moveTheSnake(snakePosition, field, 0, 1);
                    break;
            }
            if (!gameOver && foodCount < 10) {
                input = scanner.nextLine();
            }
        }
        if (gameOver) {
            System.out.println("Game over!");
        }
        if (foodCount >= 10) {
            System.out.println("You won! You fed the snake.");
        }
        System.out.printf("Food eaten: %d%n", foodCount);
        printTheMatrix(field);
    }

    private static boolean moveTheSnake(int[] snakePosition, char[][] field, int rowModification,
                                        int colModification) {
        int snakeRow = snakePosition[0];
        int snakeCol = snakePosition[1];

        int newSnakeRow = snakeRow + rowModification;
        int newSnakeCol = snakeCol + colModification;

        if (isInBounds(newSnakeRow, newSnakeCol, field)) {
            if (field[newSnakeRow][newSnakeCol] == 'B') {
                field[snakeRow][snakeCol] = '.';
                field[newSnakeRow][newSnakeCol] = '.';
                int[] newCoordinates = searchForBurrow(field);
                snakePosition[0] = newCoordinates[0];
                snakePosition[1] = newCoordinates[1];
                return false;
            } else if (field[newSnakeRow][newSnakeCol] == '*') {
                foodCount++;
                field[snakeRow][snakeCol] = '.';
                field[newSnakeRow][newSnakeCol] = 'S';
                snakePosition[0] = newSnakeRow;
                snakePosition[1] = newSnakeCol;
                return false;
            } else if (field[newSnakeRow][newSnakeCol] == '-') {
                field[snakeRow][snakeCol] = '.';
                field[newSnakeRow][newSnakeCol] = 'S';
                snakePosition[0] = newSnakeRow;
                snakePosition[1] = newSnakeCol;
                return false;
            }
        } else if (!isInBounds(newSnakeRow, newSnakeCol, field)) {
            field[snakeRow][snakeCol] = '.';
            return true;
        }
        return false;
    }

    private static int[] searchForBurrow(char[][] field) {
        int[] coordinates = new int[2];
        for (int rows = 0; rows < field.length; rows++) {
            for (int cols = 0; cols < field[rows].length; cols++) {
                if (field[rows][cols] == 'B') {
                    coordinates[0] = rows;
                    coordinates[1] = cols;
                    field[rows][cols] = 'S';
                    return coordinates;
                }
            }
        }
        return coordinates;
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
