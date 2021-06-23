package TestExams.Е03;

import java.util.*;
import java.util.stream.Collectors;

public class P01_Bombs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> bombEffect = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> bombCasing = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .forEach(bombCasing::push);

        Map<String, Integer> bombPouch = new TreeMap<>();
        bombPouch.put("Datura Bombs", 0);
        bombPouch.put("Cherry Bombs", 0);
        bombPouch.put("Smoke Decoy Bombs", 0);

        int daturaReq = 40;
        int cherryReq = 60;
        int smokeReq = 120;
//        boolean filled = bombPouch.get("Datura Bombs") >= 3 && bombPouch.get("Cherry Bombs") >= 3 &&
//                bombPouch.get("Smoke Decoy Bombs") >= 3;

        while (!bombEffect.isEmpty() && !bombCasing.isEmpty()) {
            int mix = bombEffect.peek() + bombCasing.peek();
            if (mix == daturaReq) {
                bombEffect.pop();
                bombCasing.poll();
                int newValue = bombPouch.get("Datura Bombs") + 1;
                bombPouch.put("Datura Bombs", newValue);
            } else if (mix == cherryReq) {
                bombEffect.pop();
                bombCasing.poll();
                int newValue = bombPouch.get("Cherry Bombs") + 1;
                bombPouch.put("Cherry Bombs", newValue);
            } else if (mix == smokeReq) {
                bombEffect.pop();
                bombCasing.poll();
                int newValue = bombPouch.get("Smoke Decoy Bombs") + 1;
                bombPouch.put("Smoke Decoy Bombs", newValue);
            } else {
                int newValue = bombCasing.poll() - 5;
                bombCasing.push(newValue);
            }
            if (bombPouch.get("Datura Bombs") >= 3 && bombPouch.get("Cherry Bombs") >= 3 &&
                    bombPouch.get("Smoke Decoy Bombs") >= 3) {
                break;
            }
        }
        if (bombPouch.get("Datura Bombs") >= 3 && bombPouch.get("Cherry Bombs") >= 3 &&
                bombPouch.get("Smoke Decoy Bombs") >= 3) {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }
        if (bombEffect.isEmpty()) {
            System.out.println("Bomb Effects: empty");
        } else {
            System.out.print("Bomb Effects: ");
            System.out.println(String.join(", ", bombEffect.toString()
                    .replaceAll("[\\[\\]]", "")));
        }
        if (bombCasing.isEmpty()) {
            System.out.println("Bomb Casings: empty");
        } else {
            System.out.print("Bomb Casings: ");
            System.out.println(String.join(", ", bombCasing.toString()
                    .replaceAll("[\\[\\]]", "")));
        }
        bombPouch.forEach((b, c) -> System.out.printf("%s: %d%n", b, c));
    }
}
