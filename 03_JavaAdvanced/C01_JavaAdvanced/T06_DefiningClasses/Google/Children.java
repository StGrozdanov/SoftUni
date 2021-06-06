package T06_DefiningClasses.Exercise.Google;

public class Children {
    private String name;
    private String birthday;

    public Children(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        try {
            return String.format("%s %s%n", this.name, this.birthday);
        } catch (NullPointerException exception) {
            return "";
        }
    }
}
