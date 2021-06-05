package FundamentalsFinalExamPreparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfMassages = Integer.parseInt(scanner.nextLine());
        String criteria = "@(?<planet>[a-zA-Z]+)[^-@!:>]*?:(?<population>[\\d]+)[^-@!:>]*?!(?<atkType>[AD])![^-@!:>]*?->(?<soldiers>[\\d]+)";
        Pattern pattern = Pattern.compile(criteria);

        List<String> attackedPlanets = new ArrayList<>();
        List<String> destroyedPlanets = new ArrayList<>();

        for (int i = 0; i < numberOfMassages; i++) {
            String massage = scanner.nextLine();
            String letters = String.join("", massage.split("[^sStTaArR]+"));
            StringBuilder encryptedMsg = new StringBuilder();
            for (int j = 0; j < massage.length(); j++) {
                char value = (char) (massage.charAt(j) - letters.length());
                encryptedMsg.append(value);
            }

            Matcher matcher = pattern.matcher(encryptedMsg);
            if (matcher.find()) {
                String planet = matcher.group("planet");
                String attackType = matcher.group("atkType");
                if (attackType.equals("A")) {
                    attackedPlanets.add(planet);
                } else if (attackType.equals("D")) {
                    destroyedPlanets.add(planet);
                }
            }
        }
        System.out.printf("Attacked planets: %d%n", attackedPlanets.size());
        attackedPlanets.stream().sorted((n1, n2) -> n1.compareTo(n2))
                .forEach(x -> System.out.printf("-> %s%n", x));
        System.out.printf("Destroyed planets: %d%n", destroyedPlanets.size());
        destroyedPlanets.stream().sorted((n1, n2) -> n1.compareTo(n2))
                .forEach(x -> System.out.printf("-> %s%n", x));
    }
}