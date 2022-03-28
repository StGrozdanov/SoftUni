package ProgrammingBasics;

import java.util.Scanner;

public class Scholarship {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double salary = Double.parseDouble(scanner.nextLine());
        double avgGrade = Double.parseDouble(scanner.nextLine());
        double minSalary = Double.parseDouble(scanner.nextLine());

        double socialScholarship = 0;
        double excellentScholarship = 0;

        if (salary < minSalary && avgGrade > 4.5){
            socialScholarship = Math.floor(0.35 * minSalary);
        }
        if (avgGrade >= 5.5){
            excellentScholarship = Math.floor(avgGrade * 25);
        }
        if (socialScholarship > excellentScholarship){
            System.out.printf("You get a Social scholarship %.0f BGN", socialScholarship);
        } else if (socialScholarship < excellentScholarship){
            System.out.printf("You get a scholarship for excellent results %.0f BGN", excellentScholarship);
        } else {
            System.out.println("You cannot get a scholarship!");
        }
}
}
