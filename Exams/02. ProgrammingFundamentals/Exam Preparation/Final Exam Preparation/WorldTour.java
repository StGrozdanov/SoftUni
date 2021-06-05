package FundamentalsFinalExamPreparation;

import java.util.Scanner;

public class WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder destinations = new StringBuilder(scanner.nextLine());
        String input = scanner.nextLine();
        while (!"Travel".equals(input)) {
            String[] tokens = input.split(":");
            String cmd = tokens[0];
            switch (cmd) {
                case "Add Stop":
                    int index = Integer.parseInt(tokens[1]);
                    String newStop = tokens[2];
                    if (index >= 0 && index < destinations.length()) {
                        destinations.insert(index, newStop);
                    }
                    System.out.println(destinations);
                    break;
                case "Remove Stop":
                    int start = Integer.parseInt(tokens[1]);
                    int end = Integer.parseInt(tokens[2]);
                    if (start >= 0 && start < destinations.length() && end >= 0 && end < destinations.length()) {
                        destinations.replace(start, end + 1, "");
                    }
                    System.out.println(destinations);
                    break;
                case "Switch":
                    String old = tokens[1];
                    String newOne = tokens[2];
                    String text = destinations.toString();
                    if (text.contains(old)) {
                        String newText = text.replace(old, newOne);
                        destinations.setLength(0);
                        destinations.append(newText);
                    }
                    System.out.println(destinations);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Ready for world tour! Planned stops: %s", destinations);
    }
}
