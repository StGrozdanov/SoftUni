package Т05_Polymorphism.Exercise.P03_WildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    private String livingRegion;

    public Mammal(String animalType, String animalName, Double animalWeight, String region) {
        super(animalType, animalName, animalWeight);
        this.livingRegion = region;
    }

    public String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Meat) {
            System.out.printf("%ss are not eating that type of food!%n",
                    getClass().getSimpleName());
        } else if (food instanceof Vegetable){
          super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
        }
    }
    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %d]",
                        getClass().getSimpleName(), super.getAnimalName(),
                        new DecimalFormat("#.##").format(this.getAnimalWeight()),
                        this.livingRegion, super.getFoodEaten());
    }
}
