package ProgrammingBasics.WhileCycle;

import java.util.Scanner;

public class Graduation2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        int clazz = 1;
        int danger = 0;
        double total = 0;

        while (clazz <= 12){
            double grade = Double.parseDouble(scanner.nextLine());
            if (grade >= 4) {
                clazz++;
            } else {
                danger++;
                if (danger == 2){
                    System.out.printf("%s has been excluded at %d grade", name, clazz);
                }
            } total += grade / 12;
        } System.out.printf("%s graduated. Average grade: %.2f", name, total);
    }
}
