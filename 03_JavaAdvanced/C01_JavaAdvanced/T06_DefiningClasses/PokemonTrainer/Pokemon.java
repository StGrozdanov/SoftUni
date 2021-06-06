package T06_DefiningClasses.Exercise.PokemonTrainer;

public class Pokemon {
    private String name;
    private String element;
    private int hp;

    public Pokemon(String name, String element, int hp) {
        this.name = name;
        this.element = element;
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getElement() {
        return element;
    }
}
