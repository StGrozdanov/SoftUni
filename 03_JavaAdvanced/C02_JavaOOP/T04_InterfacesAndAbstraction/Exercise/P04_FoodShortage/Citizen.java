package T04_InterfacesAndAbstraction.Exercise.P04_FoodShortage;

public class Citizen implements Identifiable, Birthable, foodBuyers {
    private String name;
    private int age;
    private String id;
    private String birthDate;
    private int food;

    public Citizen(String name, int age, String id, String birth) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate = birth;
        this.food = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void buyFood() {
        this.food += 10;
    }

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
