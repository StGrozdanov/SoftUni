package T03_Inheritance.Exercise.P06_Animals;

public class Tomcat extends Cat{
    public Tomcat(String name, int age, String gender) {
        super(name, age, gender);
    }
    public String produceSound(){
        return "MEOW";
    }
}
