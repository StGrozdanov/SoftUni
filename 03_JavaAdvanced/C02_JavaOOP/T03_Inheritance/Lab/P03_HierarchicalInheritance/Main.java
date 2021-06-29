package T03_Inheritance.Lab.P03_HierarchicalInheritance;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();
        Puppy puppy = new Puppy();

        puppy.eat();
        puppy.bark();
        puppy.weep();

        Cat cat = new Cat();

        cat.eat();
        cat.meow();
    }
}
