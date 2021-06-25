package TestExams.Е06;

import java.util.Scanner;

public class P01_TheGarden {
    public static int harmedVegetables = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] field = initializeTheMatrix(scanner);

        int harvestedLettuce = 0;
        int harvestedPotatoes = 0;
        int harvestedCarrots = 0;

        String input = scanner.nextLine();

        while (!"End of Harvest".equals(input)) {
            String[] tokens = input.split("\\s+");
            String cmd = tokens[0];
            int row = Integer.parseInt(tokens[1]);
            int col = Integer.parseInt(tokens[2]);
            switch (cmd) {
                case "Harvest":
                    if (isInBounds(row, col, field)) {
                        if (field[row][col] == 'L') {
                            harvestedLettuce++;
                            field[row][col] = ' ';
                        } else if (field[row][col] == 'P') {
                            harvestedPotatoes++;
                            field[row][col] = ' ';
                        } else if (field[row][col] == 'C') {
                            harvestedCarrots++;
                            field[row][col] = ' ';
                        }
                    }
                    break;
                case "Mole":
                    if (isInBounds(row, col, field)) {
                        String direction = tokens[3];
                        int[] initialPosition = new int[2];
                        initialPosition[0] = row;
                        initialPosition[1] = col;
                        switch (direction) {
                            case "up":
                                spreadTheMole(field, -2, 0, initialPosition);
                                break;
                            case "down":
                                spreadTheMole(field, 2, 0, initialPosition);
                                break;
                            case "left":
                                spreadTheMole(field, 0, -2, initialPosition);
                                break;
                            case "right":
                                spreadTheMole(field, 0, 2, initialPosition);
                                break;
                        }
                    }
                    break;
            }
            input = scanner.nextLine();
        }

        printTheMatrix(field);
        System.out.printf("Carrots: %d%n", harvestedCarrots);
        System.out.printf("Potatoes: %d%n", harvestedPotatoes);
        System.out.printf("Lettuce: %d%n", harvestedLettuce);
        System.out.printf("Harmed vegetables: %d", harmedVegetables);
    }

    private static void spreadTheMole(char[][] field, int rowModification, int colModification, int[] initialPosition) {
        int moleRow = initialPosition[0];
        int moleCol = initialPosition[1];
        int newMoleRow = moleRow + rowModification;
        int newMoleCol = moleCol + colModification;
        if (field[moleRow][moleCol] != ' '){
            harmedVegetables++;
            field[moleRow][moleCol] = ' ';
        }
        while (isInBounds(newMoleRow, newMoleCol, field)) {
            if (field[newMoleRow][newMoleCol] != ' '){
                harmedVegetables++;
                field[newMoleRow][newMoleCol] = ' ';
            }
            newMoleRow += rowModification;
            newMoleCol += colModification;
        }
    }

    private static char[][] initializeTheMatrix(Scanner scanner) {
        int rows = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[rows][];
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine().replaceAll("\\s+", "");
            matrix[i] = line.toCharArray();
        }
        return matrix;
    }

    private static void printTheMatrix(char[][] matrix) {
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                System.out.print(matrix[rows][cols] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(int rows, int cols, char[][] matrix) {
        return rows >= 0 && rows < matrix.length && cols >= 0 && cols < matrix[rows].length;
    }
}
