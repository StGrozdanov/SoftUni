package Т05_Polymorphism.Exercise.P03_WildFarm;

public class Zebra extends Mammal{
    public Zebra(String animalType, String animalName, Double animalWeight, String region) {
        super(animalType, animalName, animalWeight, region);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }
}
