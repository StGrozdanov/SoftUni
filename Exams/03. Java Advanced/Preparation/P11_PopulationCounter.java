package MidExamPreparation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class P11_PopulationCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, Long>> populationData = new LinkedHashMap<>();
        Map<String, Long> countryPopulation = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while (!"report".equals(input)) {
            String[] tokens = input.split("\\|");
            String city = tokens[0];
            String country = tokens[1];
            long cityPopulation = Integer.parseInt(tokens[2]);

            populationData.putIfAbsent(country, new LinkedHashMap<>());
            populationData.get(country).putIfAbsent(city, cityPopulation);

            countryPopulation.putIfAbsent(country, 0L);
            long newPopulation = countryPopulation.get(country) + cityPopulation;
            countryPopulation.put(country, newPopulation);

            input = scanner.nextLine();
        }

        countryPopulation.entrySet().stream().sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue()))
                .forEach(c -> {
                    System.out.printf("%s (total population: %d)%n", c.getKey(), c.getValue());
                    populationData.get(c.getKey()).entrySet().stream()
                            .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue()))
                            .forEach(x -> {
                                System.out.printf("=>%s: %d%n", x.getKey(), x.getValue());
                            });
                });
    }
}
