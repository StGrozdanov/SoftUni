package RegularExpressions;

import java.util.*;
import java.util.stream.Collectors;

public class Race {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> participants = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());
        Map<String, Integer> raceTimes = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while (!"end of race".equals(input)) {
            String name = String.join("", input.split("[^A-Za-z]"));
            String time = String.join("", input.split("[^0-9]"));
            int totalTime = 0;
            for (int i = 0; i < time.length(); i++) {
                int numbers = Integer.parseInt(String.valueOf(time.charAt(i)));
                totalTime += numbers;
            }
            if (participants.contains(name)) {
                if (raceTimes.containsKey(name)) {
                    int newTime = raceTimes.get(name) + totalTime;
                    raceTimes.put(name, newTime);
                } else {
                    raceTimes.put(name, totalTime);
                }
            }
            input = scanner.nextLine();
        }
        final int[] counter = {0};
        raceTimes.entrySet().stream().sorted((n1, n2) -> n2.getValue().compareTo(n1.getValue())).limit(3).
                forEach(s -> {
                    counter[0]++;
                    String count = "";
                    if (counter[0] == 1) {
                        count = "st";
                    } else if (counter[0] == 2) {
                        count = "nd";
                    } else if (counter[0] == 3) {
                        count = "rd";
                    }
                    System.out.printf("%d%s place: %s%n", counter[0], count, s.getKey());
                });
        //Вариант 2 без финал инт е да си направим един лист, който ни държи информацията "1st place: ",
        //"2nd place", etc, Който да ни седи на мястото на файнъла и на саут вместо да се разправяме с
        // каунтъри може да си пишем директно list.remove(0) + s.getKey(), това ще работи, понеже първо
        // маха първия елемент от масива, понеже, като го изпринтим не ни трябва повече и второ веднага
        // след като го махне го записва, като информация, така че да не е нужно да го достъпваме пак и
        // list.remove(0) ще ни изведе първия елемент от листа и ще го разкара и на негово място идва 2рия
    }
}
