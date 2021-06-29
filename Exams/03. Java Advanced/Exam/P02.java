import java.util.Scanner;

public class P02 {

    static int pythonLength = 1;
    static int totalFood = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] inputTokens = scanner.nextLine().split(",\\s+");
        char[][] field = initializeTheMatrix(scanner, size);
        int[] snakePosition = new int[2];

        for (int rows = 0; rows < field.length; rows++) {
            for (int cols = 0; cols < field[rows].length; cols++) {
                if (field[rows][cols] == 's') {
                    snakePosition[0] = rows;
                    snakePosition[1] = cols;
                } else if (field[rows][cols] == 'f') {
                    totalFood++;
                }
            }
        }

        boolean gameOver = false;

        for (int i = 0; i < inputTokens.length; i++) {
            switch (inputTokens[i]) {
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
            if (gameOver || totalFood == 0){
                break;
            }
        }
        if (!gameOver && totalFood == 0) {
            System.out.printf("You win! Final python length is %d", pythonLength);
        } else if (!gameOver && totalFood > 0) {
            System.out.printf("You lose! There is still %d food to be eaten.", totalFood);
        } else if (gameOver) {
            System.out.println("You lose! Killed by an enemy!");
        }
    }

    private static boolean moveTheSnake(int[] snakePosition, char[][] field, int rowModification,
                                        int colModification) {
        int snakeRow = snakePosition[0];
        int snakeCol = snakePosition[1];

        int newSnakeRow = snakeRow + rowModification;
        int newSnakeCol = snakeCol + colModification;

        if (isInBounds(newSnakeRow, newSnakeCol, field)) {
            if (field[newSnakeRow][newSnakeCol] == 'f') {
                pythonLength++;
                totalFood--;
                field[snakeRow][snakeCol] = '*';
                field[newSnakeRow][newSnakeCol] = 's';
                snakePosition[0] = newSnakeRow;
                snakePosition[1] = newSnakeCol;
                return false;
            } else if (field[newSnakeRow][newSnakeCol] == '*') {
                field[snakeRow][snakeCol] = '*';
                field[newSnakeRow][newSnakeCol] = 's';
                snakePosition[0] = newSnakeRow;
                snakePosition[1] = newSnakeCol;
                return false;
            } else if (field[newSnakeRow][newSnakeCol] == 'e') {
                field[snakeRow][snakeCol] = '*';
                snakePosition[0] = newSnakeRow;
                snakePosition[1] = newSnakeCol;
                return true;
            }
        } else if (!isInBounds(newSnakeRow, newSnakeCol, field)) {
            if (newSnakeRow < 0) {
                newSnakeRow = field.length - 1;
            } else if (newSnakeRow >= field.length) {
                newSnakeRow = 0;
            }
            if (newSnakeCol < 0) {
                newSnakeCol = field.length - 1;
            } else if (newSnakeCol >= field.length) {
                newSnakeCol = 0;
            }
            if (field[newSnakeRow][newSnakeCol] == 'f') {
                pythonLength++;
                totalFood--;
                field[snakeRow][snakeCol] = '*';
                field[newSnakeRow][newSnakeCol] = 's';
                snakePosition[0] = newSnakeRow;
                snakePosition[1] = newSnakeCol;
                return false;
            } else if (field[newSnakeRow][newSnakeCol] == '*') {
                field[snakeRow][snakeCol] = '*';
                field[newSnakeRow][newSnakeCol] = 's';
                snakePosition[0] = newSnakeRow;
                snakePosition[1] = newSnakeCol;
                return false;
            } else if (field[newSnakeRow][newSnakeCol] == 'e') {
                field[snakeRow][snakeCol] = '*';
                snakePosition[0] = newSnakeRow;
                snakePosition[1] = newSnakeCol;
                return true;
            }
            return false;
        }
        return false;
    }

    private static char[][] initializeTheMatrix(Scanner scanner, int size) {
        char[][] matrix = new char[size][size];
        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine().replaceAll("\\s+", "");
            matrix[i] = line.toCharArray();
        }
        return matrix;
    }

    private static boolean isInBounds(int rows, int cols, char[][] matrix) {
        return rows >= 0 && rows < matrix.length && cols >= 0 && cols < matrix.length;
    }
}