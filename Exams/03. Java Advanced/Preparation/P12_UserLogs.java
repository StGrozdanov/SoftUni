package MidExamPreparation;

import java.util.*;

public class P12_UserLogs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Map<String, Integer>> userLogs = new TreeMap<>();
        while (!"end".equals(input)) {
            String[] tokens = input.split("\\s+");
            String ip = tokens[0].replaceFirst("IP=", "");
            String user = tokens[2].replaceFirst("user=", "");

            userLogs.putIfAbsent(user, new LinkedHashMap<>());
            userLogs.get(user).putIfAbsent(ip, 0);
            int newValue = userLogs.get(user).get(ip) + 1;
            userLogs.get(user).put(ip, newValue);

            input = scanner.nextLine();
        }


        userLogs.forEach((user, address) -> {
            System.out.printf("%s: %n", user);
            int lastElement = address.size();
            final int[] counter = {0};
            if (address.size() == 1) {
                address.forEach((x, y) -> System.out.printf("%s => %d.%n", x, y));
            } else {
                address.forEach((key, value) -> {
                    counter[0]++;
                    if (counter[0] == lastElement) {
                        System.out.printf("%s => %d.%n", key, value);
                    } else {
                        System.out.printf("%s => %d, ", key, value);
                    }
                });
            }
        });
    }
}
