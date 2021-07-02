package T04_InterfacesAndAbstraction.Lab.P05_BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Identifiable> ids = new ArrayList<>();

        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            switch (tokens.length) {
                case 2:
                    Robot robot = new Robot(tokens[0], tokens[1]);
                    ids.add(robot);
                    break;
                case 3:
                    Citizen citizen = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                    ids.add(citizen);
                    break;
            }
            input = scanner.nextLine();
        }
        String targetId = scanner.nextLine();

        ids.stream().filter(p -> p.getId().endsWith(targetId)).forEach(p -> System.out.println(p.getId()));
    }
}
