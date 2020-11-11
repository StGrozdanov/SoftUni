package ProgrammingBasics.WhileCycle;

import java.util.Scanner;

public class MinNumber {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            String num = scanner.nextLine();
            int min = Integer.MAX_VALUE;

            while(!num.equals("Stop")) {
                int number = Integer.parseInt(num);
                if (min > number){
                    min = number;
                }
                num = scanner.nextLine();
            }
            System.out.println(min);
        }
    }
