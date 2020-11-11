package ProgrammingBasics.AdvancedConditionalStatements;

import java.util.Scanner;

public class PersonalTitles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double age = Double.parseDouble(scanner.nextLine());
        String what = scanner.nextLine();

        if (age >= 16 && what.equals("m")){
            System.out.println("Mr.");
        } else if (age < 16 && what.equals("m")){
            System.out.println("Master");
        } else if (age >= 16 && what.equals("f")){
            System.out.println("Ms.");
        } else {
            System.out.println("Miss");
        }
    }
}
