package Т05_Polymorphism.Exercise.P03_WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();
        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String[] animalTokens = input.split("\\s+");
            String animalType = animalTokens[0];
            String animalName = animalTokens[1];
            Double animalWeight = Double.parseDouble(animalTokens[2]);
            String animalRegion = animalTokens[3];

            String[] foodTokens = scanner.nextLine().split("\\s+");
            String foodType = foodTokens[0];
            Integer quantity = Integer.parseInt(foodTokens[1]);

            List<Food> food = new ArrayList<>();

            if (foodType.equals("Т05_Polymorphism.Exercise.P03_WildFarm.Vegetable")){
                Vegetable vegetable = new Vegetable(quantity);
                food.add(vegetable);
            } else if (foodType.equals("Т05_Polymorphism.Exercise.P03_WildFarm.Meat")){
                Meat meat = new Meat(quantity);
                food.add(meat);
            }

            switch (animalType) {
                case "Т05_Polymorphism.Exercise.P03_WildFarm.Cat":
                    String breed = animalTokens[4];
                    Cat cat = new Cat(animalType, animalName, animalWeight, animalRegion, breed);
                    cat.makeSound();
                    cat.eat(food.get(0));
                    animals.add(cat);
                    break;
                case "Т05_Polymorphism.Exercise.P03_WildFarm.Tiger":
                    Tiger tiger = new Tiger(animalType, animalName, animalWeight, animalRegion);
                    tiger.makeSound();
                    tiger.eat(food.get(0));
                    animals.add(tiger);
                    break;
                case "Т05_Polymorphism.Exercise.P03_WildFarm.Zebra":
                    Zebra zebra = new Zebra(animalType, animalName, animalWeight, animalRegion);
                    zebra.makeSound();
                    zebra.eat(food.get(0));
                    animals.add(zebra);
                    break;
                case "Т05_Polymorphism.Exercise.P03_WildFarm.Mouse":
                    Mouse mouse = new Mouse(animalType, animalName, animalWeight, animalRegion);
                    mouse.makeSound();
                    mouse.eat(food.get(0));
                    animals.add(mouse);
                    break;
            }
            input = scanner.nextLine();
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
