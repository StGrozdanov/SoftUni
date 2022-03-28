package ProgrammingBasics.AdvancedCycles.Exercise;

import java.util.Scanner;

public class TrainTheTrainers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int jury = Integer.parseInt(scanner.nextLine());
        String presentation = scanner.nextLine();

        double gradeSum = 0;
        double avgGrade = 0;
        double finalGrade = 0;
        int presentationCounter = 0;

        while (!presentation.equals("Finish")){
            for (int i = 1; i <= jury; i++) {
                double grade = Double.parseDouble(scanner.nextLine());
                gradeSum += grade;
                finalGrade += grade;
                avgGrade = gradeSum / jury;
            }
            System.out.printf("%s - %.2f.%n", presentation, avgGrade);
            avgGrade = 0.0;
            gradeSum = 0.0;
            presentationCounter++;
            presentation = scanner.nextLine();
        }
        finalGrade = finalGrade / (presentationCounter * jury);
        System.out.printf("Student's final assessment is %.2f.", finalGrade);
    }
}
