package T03_Inheritance.Exercise.P06_Animals;

public class Kitten extends Cat{
    public Kitten(String name, int age, String gender) {
        super(name, age, gender);
    }
    public String produceSound(){
        return "Meow";
    }
}
