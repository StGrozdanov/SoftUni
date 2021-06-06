package ObjectsAndClassesExercise10.OrderByAge;

public class Persons {
    private String name;
    private String iD;
    private int age;

    public Persons(String name, String iD, int age) {
        this.name = name;
        this.iD = iD;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getiD() {
        return iD;
    }

    public int getAge() {
        return age;
    }
}
