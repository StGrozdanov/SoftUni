package T03_Inheritance.Exercise.P06_Animals;

public class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.nameValidator(name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        Validator.ageValidator(age);
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        Validator.genderValidator(gender);
        this.gender = gender;
    }

    public String produceSound(){
        return "I am the animal master class";
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + System.lineSeparator() +
                String.format("%s %d %s", this.name, this.age, this.gender) + System.lineSeparator() +
                produceSound();
    }
}
