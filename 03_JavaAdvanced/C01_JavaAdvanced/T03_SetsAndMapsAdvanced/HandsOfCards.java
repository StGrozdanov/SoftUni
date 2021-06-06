package T03_SetsAndMapsAdvanced.Exercise;

import java.util.*;

public class HandsOfCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, Integer>> players = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while (!"JOKER".equals(input)) {
            String[] tokens = input.split(":");
            String player = tokens[0];
            players.putIfAbsent(player, new LinkedHashMap<>());
            String[] token = tokens[1].split("\\s+");
            for (int i = 1; i < token.length; i++) {
                String elements = token[i].replace(",", "");
                players.get(player).putIfAbsent(elements, 0);
                String[] element = elements.split("");
                if (element.length == 3) {
                    element[0] = String.valueOf(10);
                    element[1] = element[2];
                }
                int P = findP(element[0]);
                int T = findT(element[1]);
                players.get(player).put(elements, P * T);
            }
            input = scanner.nextLine();
        }
        List<Integer> collect = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> entry : players.entrySet()) {
            for (Map<String, Integer> value : players.values()) {
                int sum = 0;
                for (Integer integer : value.values()) {
                    sum += integer;
                }
                collect.add(sum);
            }
        }
        Map<String, Integer> finalScore = new LinkedHashMap<>();
        for (String s : players.keySet()) {
            finalScore.put(s, collect.get(0));
            collect.remove(0);
        }
        finalScore.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
    }

    private static int findP(String element) {
        int P;
        switch (element) {
            case "J":
                P = 11;
                break;
            case "Q":
                P = 12;
                break;
            case "K":
                P = 13;
                break;
            case "A":
                P = 14;
                break;
            default:
                P = Integer.parseInt(element);
                break;
        }
        return P;
    }

    private static int findT(String element) {
        int T = 0;
        switch (element) {
            case "S":
                T = 4;
                break;
            case "H":
                T = 3;
                break;
            case "D":
                T = 2;
                break;
            case "C":
                T = 1;
                break;
        }
        return T;
    }

}
