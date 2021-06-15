package MidExamPreparation;

import java.util.*;

public class P13_LogsAggregator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> userDuration = new TreeMap<>();
        Map<String, Set<String>> userIp = new TreeMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String ip = tokens[0];
            String user = tokens[1];
            int duration = Integer.parseInt(tokens[2]);

            userDuration.putIfAbsent(user, 0);
            int newDuration = userDuration.get(user) + duration;
            userDuration.put(user, newDuration);

            userIp.putIfAbsent(user, new TreeSet<>());
            userIp.get(user).add(ip);
        }

        userDuration.forEach((u, d) -> {
            System.out.printf("%s: %d %s%n", u, d, userIp.get(u));
        });
    }
}
