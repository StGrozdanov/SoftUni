package ObjectsAndClasses09.Students2;

import ObjectsAndClasses09.Students.Students;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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

            if (studentAlreadyExist(students, firstName, lastName)) {
                Students student = getStudents(students, firstName, lastName);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setAge(age);
                student.setTown(town);
            } else {
                Students student = new Students(firstName, lastName, age, town);
                students.add(student);
            }

            input = scanner.nextLine();
        }

        String targetTown = scanner.nextLine();

        for (Students info : students) {
            if (info.getTown().equals(targetTown)) {
                System.out.printf("%s %s is %d years old%n", info.getFirstName(), info.getLastName(), info.getAge());
            }
        }
    }

    private static Students getStudents(List <Students> students, String firstName, String lastName) {
        Students existingStudent = null;
        for (Students student:students){
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                existingStudent = student;
            }
        }
        return existingStudent;
    }

    private static boolean studentAlreadyExist(List<Students> students, String firstName, String lastName) {
        for (Students hi : students) {
            if (hi.getFirstName().equals(firstName) && hi.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }
}
