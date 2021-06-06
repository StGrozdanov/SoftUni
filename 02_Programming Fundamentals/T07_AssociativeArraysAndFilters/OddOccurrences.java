package AssociativeArraysFilters11;

import java.util.*;
import java.util.stream.Collectors;

public class OddOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> initialWords = Arrays.stream(scanner.nextLine().split("\\s+")).
                collect(Collectors.toList());

        Map<String, Integer> odd = new LinkedHashMap<>();

        for (String initialWord : initialWords) {
            String wordsToLowerCase = initialWord.toLowerCase();
            odd.putIfAbsent(wordsToLowerCase, 0);
            odd.put(wordsToLowerCase, odd.get(wordsToLowerCase) + 1);
        }
        List<String> oddWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry:odd.entrySet()){
            if (entry.getValue() % 2 != 0){
                oddWords.add(entry.getKey());
            }
        }
        System.out.print(String.join(", ", oddWords));
    }
}
