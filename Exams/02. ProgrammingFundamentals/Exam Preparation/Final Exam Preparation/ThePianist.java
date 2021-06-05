package FundamentalsFinalExamPreparation;

import java.util.*;

public class ThePianist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<String>> music = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\|");
            String piece = tokens[0];
            String composer = tokens[1];
            String key = tokens[2];
            music.put(piece, new ArrayList<>());
            music.get(piece).add(composer);
            music.get(piece).add(key);
        }
        String input = scanner.nextLine();
        while (!"Stop".equals(input)) {
            String[] tokens = input.split("\\|");
            String cmd = tokens[0];
            String piece = tokens[1];
            switch (cmd) {
                case "Add":
                    String composer = tokens[2];
                    String key = tokens[3];
                    if (music.containsKey(piece)) {
                        System.out.printf("%s is already in the collection!%n", piece);
                    } else {
                        System.out.printf("%s by %s in %s added to the collection!%n", piece, composer, key);
                        music.put(piece, new ArrayList<>());
                        music.get(piece).add(composer);
                        music.get(piece).add(key);
                    }
                    break;
                case "Remove":
                    if (music.containsKey(piece)) {
                        System.out.printf("Successfully removed %s!%n", piece);
                        music.remove(piece);
                    } else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    }
                    break;
                case "ChangeKey":
                    String newKey = tokens[2];
                    if (music.containsKey(piece)) {
                        music.get(piece).set(1, newKey);
                        System.out.printf("Changed the key of %s to %s!%n", piece, newKey);
                    } else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        music.entrySet().stream().sorted((p, c) -> {
            int result = p.getKey().compareTo(c.getKey());
            if (result == 0){
                result = p.getValue().get(0).compareTo(c.getValue().get(0));
            }
            return result;
        }).forEach(x -> System.out.printf("%s -> Composer: %s, Key: %s%n", x.getKey(),
                x.getValue().get(0), x.getValue().get(1)));
    }
}
