package ProgrammingBasics.WhileCycle.Exercises;

import java.util.Scanner;

public class OldBooks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String target = scanner.nextLine();
        String output = "";
        int number = 0;

        while (!target.equals(output)){
            output = scanner.nextLine();
            if (output.equals("No More Books")){
                break;
            } if (target.equals(output)){
                break;
            }
            number++;
        } if (output.equals("No More Books")){
            System.out.println("The book you search is not here!");
            System.out.printf("You checked %d books.", number);
        } else {
            System.out.printf("You checked %d books and found it.", number);
        }
    }
}
