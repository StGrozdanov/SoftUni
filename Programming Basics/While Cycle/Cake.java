package ProgrammingBasics.WhileCycle.Exercises;

import java.util.Scanner;

public class Cake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = Integer.parseInt(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());
        int cake = width * length;

        while (cake > 0) {
            String taken = scanner.nextLine();
            if (taken.equals("STOP")) {
                break;
            } else {
                int cakeTaken = Integer.parseInt(taken);
                cake = cake - cakeTaken;
                if (cake < 0) {
                    break;
                }
            }
        }
        if (cake > 0) {
            System.out.printf("%d pieces are left.", cake);
        } else {
            System.out.printf("No more cake left! You need %d pieces more.", Math.abs(cake));
        }
    }
}
