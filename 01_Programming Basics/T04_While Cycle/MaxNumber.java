package ProgrammingBasics.WhileCycle;

import java.util.Scanner;

public class MaxNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String num = scanner.nextLine();
        int max = Integer.MIN_VALUE;

        while(!num.equals("Stop")) {
            int number = Integer.parseInt(num);
            if (max < number){
                max = number;
            }
            num = scanner.nextLine();
        }
        System.out.println(max);
    }
}
