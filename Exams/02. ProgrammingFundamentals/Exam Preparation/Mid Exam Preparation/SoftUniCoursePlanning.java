package ExamPreparation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SoftUniCoursePlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> initialSchedule = Arrays.stream(scanner.nextLine().split(", "))
                .collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!"course start".equals(input)) {
            String[] tokens = input.split(":");
            String command = tokens[0];
            String lessonTitle = tokens[1];
            String exerciseTitle = lessonTitle+"-Generics.Exercise";

            switch (command) {
                case "Add":
                    if (!initialSchedule.contains(lessonTitle)){
                        initialSchedule.add(lessonTitle);
                    }
                    break;
                case "Insert":
                    int insertIndex = Integer.parseInt(tokens[2]);
                    if (!initialSchedule.contains(lessonTitle)){
                        initialSchedule.add(insertIndex, lessonTitle);
                    }
                    break;
                case "Remove":
                    initialSchedule.remove(lessonTitle);
                    initialSchedule.remove(exerciseTitle);
                    break;
                case "Swap":
                    String lessonTitle2 = tokens[2];
                    String exercise2 = lessonTitle2+"-Generics.Exercise";
                    if (initialSchedule.contains(lessonTitle) && initialSchedule.contains(lessonTitle2)){
                        int lessonTitleIndex = initialSchedule.indexOf(lessonTitle);
                        int lessonTitle2Index = initialSchedule.indexOf(lessonTitle2);
                        initialSchedule.set(lessonTitleIndex, lessonTitle2);
                        initialSchedule.set(lessonTitle2Index, lessonTitle);
                        if (initialSchedule.contains(exercise2)){
                            initialSchedule.remove(exercise2);
                            initialSchedule.add(lessonTitleIndex + 1, exercise2);
                        }
                        if (initialSchedule.contains(exerciseTitle)){
                            initialSchedule.remove(exerciseTitle);
                            initialSchedule.add(lessonTitle2Index + 1, exerciseTitle);
                        }
                    }
                    break;
                case "T06_Generics/Exercise":
                    if (initialSchedule.contains(lessonTitle) && !initialSchedule.contains(exerciseTitle)){
                        initialSchedule.add(initialSchedule.indexOf(lessonTitle)+1, exerciseTitle);
                    } else if (!initialSchedule.contains(lessonTitle)){
                        initialSchedule.add(lessonTitle);
                        initialSchedule.add(exerciseTitle);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        int counter = 1;
        for (String schedule:initialSchedule){
            System.out.println(counter+"."+schedule);
            counter++;
        }
    }
}
