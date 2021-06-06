package T06_DefiningClasses.Exercise.PokemonTrainer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Trainer> participants = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while (!"Tournament".equals(input)) {
            String[] tokens = input.split("\\s+");
            String trainerName = tokens[0];
            String pokemonName = tokens[1];
            String pokemonElement = tokens[2];
            int pokemonHealth = Integer.parseInt(tokens[3]);
            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
            participants.putIfAbsent(trainerName, new Trainer(trainerName));
            participants.get(trainerName).addPokemon(pokemon);

            input = scanner.nextLine();
        }

        input = scanner.nextLine();

        while (!"End".equals(input)) {
            for (Trainer trainer : participants.values()) {
                if (!trainer.getPokemon().isEmpty()) {
                    if (trainer.checkElement(input)) {
                        trainer.setBadges();
                    } else {
                        trainer.reduceHp();
                    }
                }
            }
            input = scanner.nextLine();
        }
        participants.entrySet().stream().sorted((p1, p2) ->
                Integer.compare(p2.getValue().getBadges(), p1.getValue().getBadges()))
                .forEach(e -> System.out.println(e.getValue()));
    }
}
