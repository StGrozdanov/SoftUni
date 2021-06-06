package T06_DefiningClasses.Exercise.PokemonTrainer;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String name;
    private int badges;
    private List<Pokemon> pokemon;

    public Trainer(String name) {
        this.name = name;
        this.badges = 0;
        this.pokemon = new ArrayList<>();
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public int getBadges() {
        return badges;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemon.add(pokemon);
    }

    public boolean checkElement(String element) {
        if (!pokemon.isEmpty()) {
            for (Pokemon pokeElement : pokemon) {
                if (element.equals(pokeElement.getElement())) {
                    return true;
                }
            }
        }
        return false;
    }


    public void setBadges() {
        this.badges += 1;
    }

    public void reduceHp() {
        if (!pokemon.isEmpty()) {
            for (Pokemon currentPokemon : pokemon) {
                int currentHP = currentPokemon.getHp();
                int newHP = currentHP - 10;
                currentPokemon.setHp(newHP);
            }
        }
        clearTheDead();
    }

    private void clearTheDead(){
        pokemon.removeIf(p -> p.getHp() <= 0);
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.name, this.badges, pokemon.size());
    }
}
