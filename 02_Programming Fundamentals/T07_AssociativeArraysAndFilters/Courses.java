package AssociativeArraysFiltersExercise12;

import java.util.*;

public class Courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> students = new LinkedHashMap<>();

        String input = scanner.nextLine();

        while(!"end".equals(input)){
            String[] tokens = input.split(" : ");
            String course = tokens[0];
            String studentName = tokens[1];

            students.putIfAbsent(course, new ArrayList<>());
            students.get(course).add(studentName);

            input = scanner.nextLine();
        }

        students.entrySet().stream().sorted((s1, s2) -> Integer.compare(s2.getValue().size(), s1.getValue().size())
        ).forEach(entry -> {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue().size());
            entry.getValue().stream().sorted((s1, s2) -> s1.compareTo(s2)).forEach(student ->
                    System.out.println("-- " + student));
        });
    }
}
