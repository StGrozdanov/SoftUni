package MidExamPreparation;

import java.util.*;

public class P15_DragonArmy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, List<Integer>>> dragonCollection = new LinkedHashMap<>();
        int defaultHp = 250;
        int defaultDmg = 45;
        int defaultArmor = 10;

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String dragonType = tokens[0];
            String dragonName = tokens[1];
            String damage = tokens[2];
            String health = tokens[3];
            String armor = tokens[4];

            if (damage.equals("null")) {
                damage = String.valueOf(defaultDmg);
            }
            if (health.equals("null")) {
                health = String.valueOf(defaultHp);
            }
            if (armor.equals("null")) {
                armor = String.valueOf(defaultArmor);
            }

            dragonCollection.putIfAbsent(dragonType, new TreeMap<>());
            dragonCollection.get(dragonType).put(dragonName, new ArrayList<>(Arrays.asList(0, 0, 0)));
            dragonCollection.get(dragonType).get(dragonName).set(0, Integer.parseInt(damage));
            dragonCollection.get(dragonType).get(dragonName).set(1, Integer.parseInt(health));
            dragonCollection.get(dragonType).get(dragonName).set(2, Integer.parseInt(armor));
        }

        Map<String, List<Double>> avgValuesByType = new LinkedHashMap<>();
        List<String> dragonTypes = new ArrayList<>();

        for (Map.Entry<String, Map<String, List<Integer>>> outerMap : dragonCollection.entrySet()) {
            dragonTypes.add(outerMap.getKey());
        }

        int index = -1;
        for (Map<String, List<Integer>> innerMap : dragonCollection.values()) {
            int totalDamage = 0;
            int totalHealth = 0;
            int totalArmor = 0;
            int counter = 0;
            for (List<Integer> stats : innerMap.values()) {
                totalDamage += stats.get(0);
                totalHealth += stats.get(1);
                totalArmor += stats.get(2);
                counter++;
            }
            index++;
            double avgDamage = 1.0 * totalDamage / counter;
            double avgHealth = 1.0 * totalHealth / counter;
            double avgArmor = 1.0 * totalArmor / counter;
            avgValuesByType.put(dragonTypes.get(index), new ArrayList<>(Arrays.asList(avgDamage, avgHealth, avgArmor)));
        }

        for (Map.Entry<String, Map<String, List<Integer>>> outerMap : dragonCollection.entrySet()) {
            System.out.printf("%s::(%.2f/%.2f/%.2f)%n", outerMap.getKey(),
                    avgValuesByType.get(outerMap.getKey()).get(0), avgValuesByType.get(outerMap.getKey()).get(1),
                    avgValuesByType.get(outerMap.getKey()).get(2));
            outerMap.getValue().forEach((key, value) -> System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n",
                    key, value.get(0), value.get(1), value.get(2)));
        }
    }
}
