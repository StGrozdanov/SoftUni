package ProgrammingBasics.WhileCycle.Exercises;

import java.util.Scanner;

public class ExamPreparation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int badGradeCount = Integer.parseInt(scanner.nextLine());
        int badGrades = 0;
        double avgScore = 0;
        int finalScore = 0;
        int problems = 0;
        String lastExercise = "";

        while (badGrades < badGradeCount) {
            String exercise = scanner.nextLine();
            if (exercise.equals("Enough")) {
                break;
            }
            int score = Integer.parseInt(scanner.nextLine());
            if (score <= 4) {
                badGrades++;
            }
            finalScore += score;
            problems++;
            lastExercise = exercise;
        }
        if (badGrades >= badGradeCount) {
            System.out.printf("You need a break, %d poor grades.", badGradeCount);
        } else {
            avgScore = 1.0 * finalScore / problems;
            System.out.printf("Average score: %.2f%n", avgScore);
            System.out.printf("Number of problems: %d%n", problems);
            System.out.printf("Last problem: %s", lastExercise);
        }
    }
}
