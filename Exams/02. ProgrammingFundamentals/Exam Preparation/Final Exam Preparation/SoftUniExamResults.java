package FundamentalsFinalExamPreparation;

import java.util.*;

public class SoftUniExamResults {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> scores = new TreeMap<>();
        Map<String, List<Integer>> technologies = new TreeMap<>();

        String input = scanner.nextLine();

        while (!"exam finished".equals(input)) {
            String[] tokens = input.split("-");
            String username = tokens[0];
            String cmd = tokens[1];

            if ("banned".equals(cmd)) {
                scores.remove(username);
            } else {
                Integer score = Integer.parseInt(tokens[2]);
                scores.putIfAbsent(username, 0);
                if (scores.get(username) < score){
                    scores.put(username, score);
                }
                technologies.putIfAbsent(cmd, new ArrayList<>());
                technologies.get(cmd).add(score);
            }
            input = scanner.nextLine();
        }
        System.out.println("Results:");
        scores.entrySet().stream().sorted((g1, g2) -> g2.getValue().compareTo(g1.getValue()))
                .forEach(x -> System.out.printf("%s | %d%n", x.getKey(), x.getValue()));
        System.out.println("Submissions:");
        technologies.entrySet().stream().sorted((s1, s2) ->
                Integer.compare(s2.getValue().size(), s1.getValue().size()))
                .forEach(x -> System.out.printf("%s - %d%n", x.getKey(), x.getValue().size()));
    }
}
