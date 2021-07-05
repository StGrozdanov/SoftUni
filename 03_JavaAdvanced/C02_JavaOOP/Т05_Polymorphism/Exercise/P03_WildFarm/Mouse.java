package Т05_Polymorphism.Exercise.P03_WildFarm;

public class Mouse extends Mammal{
    public Mouse(String animalType, String animalName, Double animalWeight, String region) {
        super(animalType, animalName, animalWeight, region);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Meat) {
            System.out.println("Mice are not eating that type of food!");
        } else if (food instanceof Vegetable){
            super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
        }
    }
}
