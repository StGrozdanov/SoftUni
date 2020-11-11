package ProgrammingBasics.AdvancedConditionalStatements;

import java.util.Scanner;

public class InvalidNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());

        if (num > 200 || num < 100 && num != 0){
            System.out.println("invalid");
        }
    }
}
