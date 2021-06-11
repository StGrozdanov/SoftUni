package MidExamPreparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P04_StringMatrixRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int degrees = getDegrees(scanner);

        String input = scanner.nextLine();
        List<String> data = new ArrayList<>();

        while (!"END".equals(input)) {
            data.add(input);
            input = scanner.nextLine();
        }

        int rows = data.size();
        int cols = Integer.MIN_VALUE;

        for (String text : data) {
            int curLength = text.length();
            if (curLength > cols) {
                cols = curLength;
            }
        }

        char[][] matrix = initializeTheMatrix(data, rows, cols);

        if (degrees > 360) {
            degrees = degrees % 360;
        }

        switch (degrees) {
            case 90:
                reArrangeTheMatrix(matrix, cols, rows);
                break;
            case 180:
                reverseTheMatrix(matrix);
                break;
            case 270:
                reArrangeAndReverseTheMatrix(matrix, cols, rows);
                break;
            default:
                print(matrix);
        }
    }

    private static void reArrangeAndReverseTheMatrix(char[][] matrix, int cols, int rows) {
        char[][] newMatrix = new char[cols][rows];

        int j = 0;
        for (int row = 0; row < matrix.length; row++) {
            int i = cols - 1;
            for (int col = 0; col < matrix[row].length; col++) {
                newMatrix[i][j] = matrix[row][col];
                i--;
            }
            j++;
        }
        print(newMatrix);
    }

    private static void reArrangeTheMatrix(char[][] matrix, int cols, int rows) {
        char[][] newMatrix = new char[cols][rows];

        int j = 0;
        for (int row = matrix.length - 1; row >= 0; row--) {
            int i = 0;
            for (int col = 0; col < matrix[row].length; col++) {
                newMatrix[i][j] = matrix[row][col];
                i++;
            }
            j++;
        }
        print(newMatrix);
    }

    private static void reverseTheMatrix(char[][] matrix) {
        for (int rows = matrix.length - 1; rows >= 0; rows--) {
            for (int cols = matrix[rows].length - 1; cols >= 0; cols--) {
                System.out.print(matrix[rows][cols]);
            }
            System.out.println();
        }
    }

    private static int getDegrees(Scanner scanner) {

        String criteria = "[A-Z][a-z]+\\((?<Number>\\d+)\\)";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(scanner.nextLine());

        if (matcher.find()) {
            return Integer.parseInt(matcher.group("Number"));
        }
        return 0;
    }

    private static void print(char[][] matrix) {
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                if (matrix[rows][cols] == 0) {
                    matrix[rows][cols] = ' ';
                }
                System.out.print(matrix[rows][cols]);
            }
            System.out.println();
        }
    }

    private static char[][] initializeTheMatrix(List<String> data, int rows, int cols) {
        char[][] matrix = new char[rows][cols];

        for (int r = 0; r < matrix.length; r++) {
            char[] arr = data.get(r).toCharArray();
            matrix[r] = arr;
        }
        return matrix;
    }
}
