package AssociativeArraysFilters11;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class WordSynonyms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, ArrayList<String>> synonyms = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            String synonym = scanner.nextLine();

            synonyms.putIfAbsent(word, new ArrayList<>());
            synonyms.get(word).add(synonym);
        }
        for (Map.Entry<String, ArrayList<String>> entry:synonyms.entrySet())
        System.out.println(entry.getKey() + " - " + entry.getValue().
                toString().replaceAll("[\\]\\[]", ""));
    }
}
