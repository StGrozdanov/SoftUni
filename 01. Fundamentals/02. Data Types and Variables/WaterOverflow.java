package DataTypesAndVariables;

import java.util.Scanner;

public class WaterOverflow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int totalWater = 255;
        int pouredOutWater = 0;

        for (int i = 0; i < n; i++){
            int waterInput = Integer.parseInt(scanner.nextLine());

            pouredOutWater += waterInput;

            if (pouredOutWater > 255){
                pouredOutWater -= waterInput;
                System.out.println("Insufficient capacity!");
            }
        }
        System.out.println(pouredOutWater);
    }
}
