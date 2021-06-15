package MidExamPreparation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P14_SurbianUnleashed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String criteria = "^(?<singer>[A-Za-z]+[ ]{0,1}[A-Za-z]*[ ]{0,1}[A-Za-z]*) @(?<town>[A-Za-z]+[ ]{0,1}[A-Za-z]*[ ]{0,1}[A-Za-z]*[ ]{0,1}) (?<ticketPrice>\\d+) (?<ticketsCount>\\d+)$";
        Pattern pattern = Pattern.compile(criteria);
        Map<String, Map<String, Integer>> concert = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!"End".equals(input)) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String singer = matcher.group("singer");
                String town = matcher.group("town");
                int ticketPrice = Integer.parseInt(matcher.group("ticketPrice"));
                int ticketsCount = Integer.parseInt(matcher.group("ticketsCount"));

                concert.putIfAbsent(town, new LinkedHashMap<>());
                concert.get(town).putIfAbsent(singer, 0);
                int totalPrice = (ticketPrice * ticketsCount) + concert.get(town).get(singer);
                concert.get(town).put(singer, totalPrice);
            }
            input = scanner.nextLine();
        }
        for (Map.Entry<String, Map<String, Integer>> outerMap : concert.entrySet()) {
            System.out.println(outerMap.getKey());
            outerMap.getValue().entrySet().stream().sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue()))
                    .forEach(e -> System.out.printf("#  %s -> %d%n", e.getKey(), e.getValue()));
        }
    }
}
