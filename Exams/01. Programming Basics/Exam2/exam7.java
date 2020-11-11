package BasicsExamTest2;

import java.util.Scanner;

public class exam7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int first = Integer.parseInt(scanner.nextLine());
        int second = Integer.parseInt(scanner.nextLine());
        int third = Integer.parseInt(scanner.nextLine());

        for (int i = 2; i <= first; i += 2) {
            for (int j = 2; j <= second; j++) {
                for (int k = 2; k <= third; k += 2) {
                    if (j == 2 || j == 3 || j == 5 || j == 7) {
                        System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
    }
}