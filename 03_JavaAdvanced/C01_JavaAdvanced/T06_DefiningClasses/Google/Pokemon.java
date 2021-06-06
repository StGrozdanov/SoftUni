package T06_DefiningClasses.Exercise.Google;

public class Pokemon {
    private String name;
    private String type;

    public Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        try {
            return String.format("%s %s%n", this.name, this.type);
        } catch (NullPointerException exception) {
            return "";
        }
    }
}
