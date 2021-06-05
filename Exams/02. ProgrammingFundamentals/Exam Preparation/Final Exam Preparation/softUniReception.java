package exam01;

import java.util.Scanner;

public class softUniReception {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int employee1Efficiency = Integer.parseInt(scanner.nextLine());
        int employee2Efficiency = Integer.parseInt(scanner.nextLine());
        int employee3Efficiency = Integer.parseInt(scanner.nextLine());
        int studentsCount = Integer.parseInt(scanner.nextLine());

        int answeredStudentsPerHour = employee1Efficiency + employee2Efficiency + employee3Efficiency;

        int counter = 0;

        while (studentsCount > 0) {
            counter++;
            if (counter % 4 == 0 && counter != 0) {
                studentsCount += answeredStudentsPerHour;
            }
            studentsCount -= answeredStudentsPerHour;
        }
        System.out.printf("Time needed: %dh.", counter);
    }
}
