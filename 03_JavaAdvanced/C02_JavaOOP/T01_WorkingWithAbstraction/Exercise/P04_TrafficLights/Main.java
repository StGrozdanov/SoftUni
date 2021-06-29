package T01_WorkingWithAbstraction.Exercise.P04_TrafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        int repetitions = Integer.parseInt(scanner.nextLine());
        List<TrafficLight> trafficLightList = new ArrayList<>();

        for (String lights : input) {
            TrafficLight trafficLight = new TrafficLight(TrafficLightState.valueOf(lights));
            trafficLightList.add(trafficLight);
        }
        for (int i = 0; i < repetitions; i++) {
            for (TrafficLight trafficLight : trafficLightList) {
                trafficLight.changeSignal();
                System.out.print(trafficLight + " ");
            }
            System.out.println();
        }
    }
}
