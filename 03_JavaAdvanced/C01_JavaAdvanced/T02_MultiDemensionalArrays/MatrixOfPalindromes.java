package T02_MultiDemensionalArrays.Exercise;

import java.util.Scanner;

public class MatrixOfPalindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(tokens[0]);
        int columns = Integer.parseInt(tokens[1]);

        String startLetter = findTheLettersStartAndEnd(rows);
        String midLetter = findTheMiddleLetter(rows, columns);
        String endLetter = findTheLettersStartAndEnd(rows);

        System.out.println(startLetter);
        System.out.println(midLetter);
        System.out.println(endLetter);

    }

    /**
     * приема две числа, за да намери кореспондиращите й букви.
     * @param rows това са редовете на матрицата, в която попълва намерените букви
     * @return връща String
     */
    private static String findTheLettersStartAndEnd(int rows) {
        char startAndEnd = (char) (rows + 97);
        return String.valueOf(startAndEnd);
    }

    private static String findTheMiddleLetter(int rows, int columns) {
        char mid = (char) (rows + 97 + columns);
        return String.valueOf(mid);
    }
}
