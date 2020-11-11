package ProgrammingBasics.AdvancedCycles;

import java.util.Scanner;

public class TwoNumSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int begin = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());
        int magicNum = Integer.parseInt(scanner.nextLine());

        boolean flag = false;
        int sum = 0;
        int counter = 0;

        for (int i = begin; i <= end; i++) {
            for (int j = begin; j <= end; j++) {
                counter++;
                if (i + j == magicNum) {
                    System.out.printf("Combination N:%d (%d + %d = %d)", counter, i, j, magicNum);
                    flag = true;
                    break;
                }
                } if (flag){
                break;
            }
        }
        if (!flag){
            System.out.printf("%d combinations - neither equals %d", counter, magicNum);
        }
    }
    }
