package ProgrammingBasics.AdvancedConditionalStatements;

import java.util.Scanner;

public class FruitOrVegetable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String bullshit = scanner.nextLine();

        if (bullshit.equals("banana") || bullshit.equals("apple") || bullshit.equals("kiwi") || bullshit.equals("cherry") || bullshit.equals("lemon") || bullshit.equals("grapes")) {
            System.out.println("fruit");
        } else if (bullshit.equals("tomato") || bullshit.equals("cucumber") || bullshit.equals("pepper") || bullshit.equals("carrot")) {
            System.out.println("vegetable");
        } else {
            System.out.println("unknown");
        }
    }
}
