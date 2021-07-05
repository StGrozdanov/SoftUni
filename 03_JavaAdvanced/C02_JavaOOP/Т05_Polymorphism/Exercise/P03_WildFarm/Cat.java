package Т05_Polymorphism.Exercise.P03_WildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime{
    private String breed;

    public Cat(String animalType, String animalName, Double animalWeight, String region, String breed) {
        super(animalType, animalName, animalWeight, region);
        this.breed = breed;
    }


    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
    }
    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %s, %d]",
                        getClass().getSimpleName(), super.getAnimalName(),
                        this.breed,
                        new DecimalFormat("#.##").format(this.getAnimalWeight()),
                        super.getLivingRegion(), super.getFoodEaten());
    }
}
