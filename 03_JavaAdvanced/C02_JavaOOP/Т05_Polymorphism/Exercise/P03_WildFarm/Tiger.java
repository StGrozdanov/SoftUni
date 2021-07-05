package Т05_Polymorphism.Exercise.P03_WildFarm;

public class Tiger extends Felime{
    private String livingRegion;

    public Tiger(String animalType, String animalName, Double animalWeight, String region) {
        super(animalType, animalName, animalWeight, region);
        this.livingRegion = region;
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Vegetable) {
            System.out.printf("%ss are not eating that type of food!%n",
                    getClass().getSimpleName());
        } else if (food instanceof Meat){
            super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
        }
    }
}
