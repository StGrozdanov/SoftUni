package MidExamPreparation;

import java.util.*;
import java.util.stream.Collectors;

public class P25_ThePartyReservationFilterModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> reservations = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
        Map<String, List<String>> history = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!"Print".equals(input)) {
            String[] tokens = input.split(";");
            String cmd = tokens[0];
            String filterType = tokens[1];
            String parameter = tokens[2];
            switch (cmd) {
                case "Add filter":
                    filter(filterType, parameter, reservations, history);
                    break;
                case "Remove filter":
                   reservations = removeFilter(filterType, parameter, reservations, history);
                    break;
            }
            input = scanner.nextLine();
        }
        for (String reservation : reservations) {
            System.out.print(reservation + " ");
        }
    }

    private static List<String> removeFilter(String filterType, String parameter, List<String> reservations,
                                             Map<String, List<String>> history) {
        String key = filterType + ";" + parameter;
        reservations = history.get(key);
        return reservations;
    }

    private static void filter(String filterType, String parameter, List<String> reservations,
                               Map<String, List<String>> history) {
        String key = filterType + ";" + parameter;
        List<String> oldList = new ArrayList<>();
        for (String reservation : reservations) {
            oldList.add(reservation);
        }
        history.put(key, oldList);
        List<String> filteredList;
        switch (filterType) {
            case "Starts with":
                filteredList = reservations.stream().filter(e -> e.startsWith(parameter)).collect(Collectors.toList());
                for (String reservation : filteredList) {
                    reservations.remove(reservation);
                }
                break;
            case "Ends with":
                filteredList = reservations.stream().filter(e -> e.endsWith(parameter)).collect(Collectors.toList());
                for (String reservation : filteredList) {
                    reservations.remove(reservation);
                }
                break;
            case "Length":
                int parameterAsInt = Integer.parseInt(parameter);
                filteredList = reservations.stream().filter(e -> e.length() == parameterAsInt)
                        .collect(Collectors.toList());
                for (String reservation : filteredList) {
                    reservations.remove(reservation);
                }
                break;
            case "Contains":
                filteredList = reservations.stream().filter(e -> e.contains(parameter)).collect(Collectors.toList());
                for (String reservation : filteredList) {
                    reservations.remove(reservation);
                }
                break;
        }
    }
}
