package ProgrammingBasics.WhileCycle;

import java.util.Scanner;

public class GraduationPart2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();

        int clazz = 0;
        int badScore = 0;
        double avgGrade = 0;

        while (clazz < 12){
            double grade = Double.parseDouble(scanner.nextLine());
            if (grade >= 4){
                clazz ++;
            } else {
                badScore++;
                if (badScore == 2){
                    break;
                }
            } avgGrade += grade / 12;
        } if (clazz == 12){
            System.out.printf("%s graduated. Average grade: %.2f", name, avgGrade);
        } else {
            System.out.printf("%s has been excluded at %d grade", name, clazz+1);
        }
    }
}
