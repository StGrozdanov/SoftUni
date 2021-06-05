package FundamentalsFinalExamPreparation;

import java.util.*;

public class PlantDiscovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Double>> plants = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("<->");
            String plant = tokens[0];
            double rarity = Double.parseDouble(tokens[1]);
            plants.put(plant, new ArrayList<>());
            plants.get(plant).add(0, rarity);
        }

        String input = scanner.nextLine();

        while (!"Exhibition".equals(input)) {
            String[] tokens = input.split("\\s+");
            String cmd = tokens[0];
            String plant = tokens[1];
            switch (cmd) {
                case "Rate:":
                    double rating = Double.parseDouble(tokens[3]);
                    if (plants.containsKey(plant)) {
                        plants.get(plant).add(rating);
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "Update:":
                    double rarity = Double.parseDouble(tokens[3]);
                    if (plants.containsKey(plant)) {
                        plants.get(plant).set(0, rarity);
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "Reset:":
                    if (plants.containsKey(plant)) {
                        double oldRarity = plants.get(plant).get(0);
                        plants.get(plant).clear();
                        plants.get(plant).add(oldRarity);
                    } else {
                        System.out.println("error");
                    }
                    break;
                default:
                    System.out.println("error");
                    break;
            }
            input = scanner.nextLine();
        }
        for (Map.Entry<String, List<Double>> entry : plants.entrySet()) {
            if (entry.getValue().size() > 2) {
                double rarity = entry.getValue().get(0);
                double scoreSum = 0.0;
                int counter = 0;
                for (int i = 1; i < entry.getValue().size(); i++) {
                    counter++;
                    scoreSum += entry.getValue().get(i);
                }
                double avgScore = scoreSum / counter;
                entry.getValue().clear();
                entry.getValue().add(rarity);
                entry.getValue().add(avgScore);
            }
            if (entry.getValue().size() == 1) {
                entry.getValue().add(1, 0.00);
            }
        }
        // цялата горна мусака можеше да се съкрати със Stream Api .average() ...

        System.out.println("Plants for the exhibition:");
        plants.entrySet().stream().sorted((e1, e2) -> {
            int result = e2.getValue().get(0).compareTo(e1.getValue().get(0));
            if (result == 0) {
                result = e2.getValue().get(1).compareTo(e1.getValue().get(1));
            }
            return result;
        }).forEach(x -> System.out.printf("- %s; Rarity: %.0f; Rating: %.2f%n",
                x.getKey(), x.getValue().get(0), x.getValue().get(1)));
    }
}
