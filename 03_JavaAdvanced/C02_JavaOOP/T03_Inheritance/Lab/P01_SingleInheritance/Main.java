package T03_Inheritance.Lab.P01_SingleInheritance;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();

        animal.eat();
        dog.eat();
        dog.bark();
    }
}
