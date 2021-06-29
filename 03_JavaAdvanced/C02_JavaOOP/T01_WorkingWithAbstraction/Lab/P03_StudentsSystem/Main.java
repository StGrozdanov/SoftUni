package T01_WorkingWithAbstraction.Lab.P03_StudentsSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");
        StudentSystem studentSystem = new StudentSystem();

        while (!"Exit".equals(input[0])) {
            switch (input[0]) {
                case "Create":
                    var name = input[1];
                    var age = Integer.parseInt(input[2]);
                    var grade = Double.parseDouble(input[3]);
                    Student student = new Student(name, age, grade);
                    studentSystem.add(student);
                    break;
                case "Show":
                    var targetName = input[1];
                    studentSystem.showStudent(targetName);
                    break;
            }
            input = scanner.nextLine().split("\\s+");
        }
    }
}
