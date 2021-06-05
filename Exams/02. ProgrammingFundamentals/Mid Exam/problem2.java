package midExam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> parts = Arrays.stream(scanner.nextLine().split("\\|")).collect(Collectors.toList());

        String input = scanner.nextLine();

        while(!"Done".equals(input)){
            String[] tokens = input.split("\\s+");
            String cmd1 = tokens[0];
            String cmd2 = tokens[1];

            if (cmd1.equals("Move") && cmd2.equals("Left")){
                int leftIndex = Integer.parseInt(tokens[2]);

                if (leftIndex > 0 && leftIndex < parts.size()){
                    String part1 = parts.get(leftIndex - 1);
                    String part2 = parts.get(leftIndex);

                    parts.set(leftIndex-1, part2);
                    parts.set(leftIndex, part1);

                }

            } else if (cmd1.equals("Move") && cmd2.equals("Right")){
                int rightIndex = Integer.parseInt(tokens[2]);

                if (rightIndex >= 0 && (rightIndex+1) < parts.size()){
                    String part1 = parts.get(rightIndex + 1);
                    String part2 = parts.get(rightIndex);

                    parts.set(rightIndex+1, part2);
                    parts.set(rightIndex, part1);

                }
            } else if (cmd1.equals("Check") && cmd2.equals("Even")){
                List<String> even = new ArrayList<>();
                for (int i = 0; i < parts.size(); i++) {
                    if (i % 2 == 0){
                        even.add(parts.get(i));
                    }
                }
                System.out.println(String.join(" ", even));
            } else if (cmd1.equals("Check") && cmd2.equals("Odd")){
                List<String> odd = new ArrayList<>();
                for (int i = 0; i < parts.size(); i++) {
                    if (i % 2 != 0){
                        odd.add(parts.get(i));
                    }
                }
                System.out.println(String.join(" ", odd));
            }
            input = scanner.nextLine();
        }
        System.out.println("You crafted " + parts.toString().replaceAll("[\\[\\], ]", "") + "!");
    }
}
