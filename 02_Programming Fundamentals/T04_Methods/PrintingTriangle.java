package Methods05;

import java.util.Scanner;

public class PrintingTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int method = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= method; i++) {
            trianglePrint(i);
        }
        for (int j = method - 1; j >= 1; j--) {
            trianglePrint(j);
        }
    }

    public static void trianglePrint(int method) {
        for (int i = 1; i <= method; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}