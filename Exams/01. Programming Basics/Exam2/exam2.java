package BasicsExamTest2;

import java.util.Scanner;

public class exam2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        int food = Integer.parseInt(scanner.nextLine());
        double foodAnimal1 = Double.parseDouble(scanner.nextLine());
        double foodAnimal2 = Double.parseDouble(scanner.nextLine());
        double foodAnimal3 = Double.parseDouble(scanner.nextLine());

        double animal1FoodNeeds = days * foodAnimal1;
        double animal2FoodNeeds = days * foodAnimal2;
        double animal3FoodNeeds = days * foodAnimal3;

        double foodSum = animal1FoodNeeds + animal2FoodNeeds + animal3FoodNeeds;

        if (foodSum <= food){
            double foodLeft = food - foodSum;
            System.out.printf("%.0f kilos of food left.", Math.floor(foodLeft));
        } else {
            double foodNeeded = foodSum - food;
            System.out.printf("%.0f more kilos of food are needed.", Math.ceil(foodNeeded));
        }
    }
}
