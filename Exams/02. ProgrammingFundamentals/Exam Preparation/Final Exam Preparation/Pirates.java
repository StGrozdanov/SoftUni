package exam01;

import java.util.*;

public class Pirates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String cities = scanner.nextLine();
        Map<String, List<Integer>> targets = new TreeMap<>();

        while (!"Sail".equals(cities)) {
            String[] tokens = cities.split("\\|\\|");
            String town = tokens[0];
            int population = Integer.parseInt(tokens[1]);
            int gold = Integer.parseInt(tokens[2]);

            if (!targets.containsKey(town)) {
                targets.put(town, new ArrayList<>());
                targets.get(town).add(population);
                targets.get(town).add(gold);
            } else {
                int newPopulation = targets.get(town).get(0) + population;
                int newGold = targets.get(town).get(1) + gold;
                targets.get(town).set(0, newPopulation);
                targets.get(town).set(1, newGold);
            }
            cities = scanner.nextLine();
        }
        String events = scanner.nextLine();
        while (!"End".equals(events)) {
            String[] tokens = events.split("=>");
            String cmd = tokens[0];
            if (cmd.equals("Plunder")) {
                String town = tokens[1];
                int people = Integer.parseInt(tokens[2]);
                int gold = Integer.parseInt(tokens[3]);

                int newPopulation = targets.get(town).get(0) - people;
                int newGold = targets.get(town).get(1) - gold;
                targets.get(town).set(0, newPopulation);
                targets.get(town).set(1, newGold);
                System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", town, gold,
                        people);
                if (newPopulation == 0 || newGold == 0) {
                    targets.remove(town);
                    System.out.printf("%s has been wiped off the map!%n", town);
                }

            } else if (cmd.equals("Prosper")) {
                String town = tokens[1];
                int gold = Integer.parseInt(tokens[2]);
                if (gold < 0) {
                    System.out.println("Gold added cannot be a negative number!");
                } else {
                    int newGold = targets.get(town).get(1) + gold;
                    targets.get(town).set(1, newGold);
                    System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n",
                            gold, town, newGold);
                }
            }
            events = scanner.nextLine();
        }
        if (targets.size() > 0) {
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n",
                    targets.size());
            targets.entrySet().stream()
                    .sorted((x, y) -> y.getValue().get(1).compareTo(x.getValue().get(1))).forEach(x ->
                    System.out.printf("%s -> Population: %d citizens, Gold: %d kg%n", x.getKey(),
                            x.getValue().get(0), x.getValue().get(1))
            );
        }
    }
}
