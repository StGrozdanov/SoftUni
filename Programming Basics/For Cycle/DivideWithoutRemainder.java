package ProgrammingBasics.ForCycle.Exercise;

import java.util.Scanner;

public class DivideWithoutRemainder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

                int num = Integer.parseInt(scanner.nextLine());

                double p1 = 0;
                double p2 = 0;
                double p3 = 0;

                double numCount1 = 0;
                double numCount2 = 0;
                double numCount3 = 0;


                for (int i = 1; i <= num; i++) {
                    int n = Integer.parseInt(scanner.nextLine());
                    if (n % 2 == 0) {
                        numCount1++;
                        p1 = 1.0 * (numCount1 / num) * 100;
                    }
                    if (n % 3 == 0) {
                        numCount2++;
                        p2 = 1.0 * (numCount2 / num) * 100;
                    }
                    if (n % 4 == 0) {
                        numCount3++;
                        p3 = 1.0 * (numCount3 / num) * 100;
                    }
                }
                System.out.printf("%.2f%%%n", p1);
                System.out.printf("%.2f%%%n", p2);
                System.out.printf("%.2f%%%n", p3);
            }
        }
