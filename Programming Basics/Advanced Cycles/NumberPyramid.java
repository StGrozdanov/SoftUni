package ProgrammingBasics.AdvancedCycles.Exercise;

import java.util.Scanner;

public class NumberPyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int n1 = 1;
        boolean flag = false;

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= i; j++){
                System.out.print(n1 + " ");
                n1++;
                if (n1 > n){
                    flag = true;
                    break;
                }

            }
            System.out.println();
            if (flag){
                break;
            }
        }

    }
}
