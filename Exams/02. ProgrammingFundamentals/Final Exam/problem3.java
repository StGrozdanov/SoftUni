package FundamentalsFinalExam;

import java.util.*;

public class problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> users = new TreeMap<>();

        String input = scanner.nextLine();
        while (!"Statistics".equals(input)) {
            String[] tokens = input.split("->");
            String cmd = tokens[0];
            String userName = tokens[1];
            switch (cmd) {
                case "Add":
                    if (!users.containsKey(userName)) {
                        users.put(userName, new ArrayList<>());
                    } else {
                        System.out.printf("%s is already registered%n", userName);
                    }
                    break;
                case "Send":
                    String email = tokens[2];
                    if (users.containsKey(userName)) {
                        users.get(userName).add(email);
                    }
                    break;
                case "Delete":
                    if (users.containsKey(userName)) {
                        users.remove(userName);
                    } else {
                        System.out.printf("%s not found!%n", userName);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        int usersCount = users.size();
        System.out.println("Users count: " + usersCount);
        users.entrySet().stream().sorted((u1, u2) -> Integer.compare(u2.getValue().size(), u1.getValue().size()))
                .forEach(x -> {
                    System.out.println(x.getKey());
                    x.getValue().forEach(y -> System.out.printf("- %s%n", y));
                });
    }
}
