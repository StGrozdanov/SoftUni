package AssociativeArraysFiltersExercise12;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LegendaryFarming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> keyMaterials = new LinkedHashMap<>();
        keyMaterials.put("shards", 0);
        keyMaterials.put("fragments", 0);
        keyMaterials.put("motes", 0);
        Map<String, Integer> junk = new LinkedHashMap<>();
        boolean weHaveLegendary = false;

        while (!weHaveLegendary) {
            String[] input = scanner.nextLine().split("\\s+");
            for (int i = 0; i < input.length; i += 2) {
                int quantity = Integer.parseInt(input[i]);
                String material = input[i + 1].toLowerCase();
                if (keyMaterials.containsKey(material)) {
                    keyMaterials.put(material, keyMaterials.get(material) + quantity);
                    if (keyMaterials.get(material) >= 250) {
                        switch (material) {
                            case "shards":
                                System.out.println("Shadowmourne obtained!");
                                break;
                            case "fragments":
                                System.out.println("Valanyr obtained!");
                                break;
                            case "motes":
                                System.out.println("Dragonwrath obtained!");
                                break;
                        }
                        int newCount = keyMaterials.get(material) - 250;
                        keyMaterials.put(material, newCount);
                        weHaveLegendary = true;
                        break;
                    }
                } else {
                    if (!junk.containsKey(material)) {
                        junk.put(material, 0);
                    }
                    junk.put(material, junk.get(material) + quantity);
                }
            }
        }
        keyMaterials.entrySet().stream().sorted((n1, n2) -> {
            int result = n2.getValue().compareTo(n1.getValue());
            if (result == 0){
               result = n1.getKey().compareTo(n2.getKey());
            }
            return result;
        }).forEach(i -> System.out.printf("%s: %d%n", i.getKey(), i.getValue()));

        junk.entrySet().stream().sorted((n1, n2) -> n1.getKey().compareTo(n2.getKey())).
                forEach(i -> System.out.printf("%s: %d%n", i.getKey(), i.getValue()));
    }
}
