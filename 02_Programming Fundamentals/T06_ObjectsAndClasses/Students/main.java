package ObjectsAndClasses09.Students;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Students> students = new ArrayList<>();

        while (!"end".equals(input)) {

            String[] studentInfo = input.split(" ");
            String firstName = studentInfo[0];
            String lastName = studentInfo[1];
            int age = Integer.parseInt(studentInfo[2]);
            String town = studentInfo[3];

            Students info = new Students(firstName, lastName, age, town);

            students.add(info);

            input = scanner.nextLine();
        }

        String targetTown = scanner.nextLine();

        for (Students info:students){
            if (info.getTown().equals(targetTown)){
                System.out.printf("%s %s is %d years old%n", info.getFirstName(), info.getLastName(), info.getAge());
            }
        }
    }
}
