package FundamentalsFinalExamPreparation;

import java.util.*;
import java.util.stream.Collectors;

public class ForceBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, List<String>> users = new TreeMap<>();

        while (!"Lumpawaroo".equals(input)) {
            if (input.contains("|")) {
                String[] tokens = input.split(" \\| ");
                String forceSide = tokens[0];
                String forceUser = tokens[1];

                boolean check = false;
                for (Map.Entry<String, List<String>> current : users.entrySet()) {
                    if (current.getValue().contains(forceUser)) {
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    users.putIfAbsent(forceSide, new ArrayList<>());
                    users.get(forceSide).add(forceUser);
                }
            } else if (input.contains("->")) {
                String[] tokens = input.split(" -> ");
                String forceSide = tokens[1];
                String forceUser = tokens[0];

                for (Map.Entry<String, List<String>> entry : users.entrySet()) {
                    entry.getValue().remove(forceUser);
                }
                users.putIfAbsent(forceSide, new ArrayList<>());
                users.get(forceSide).add(forceUser);
                System.out.printf("%s joins the %s side!%n", forceUser, forceSide);
            }
            input = scanner.nextLine();
        }
        Collections.reverse(users.entrySet().stream().collect(Collectors.toList()));

        users
                .entrySet()
                .stream()
                .sorted((x, y) -> Integer.compare(y.getValue().size(), x.getValue().size()))
                .filter(n -> n.getValue().size() > 0)
                .forEach(x -> {
                    System.out.printf("Side: %s, Members: %d%n", x.getKey(), x.getValue().size());
                    x.getValue().stream().sorted((y1, y2) -> y1.compareTo(y2)).forEach(y ->
                            System.out.println("! " + y));
                });
    }
}
