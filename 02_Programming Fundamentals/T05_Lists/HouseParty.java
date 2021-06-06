package ListsExercise08;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfCommands = Integer.parseInt(scanner.nextLine());
        List<String> guest = new ArrayList<>();

        for (int i = 0; i < numberOfCommands; i++) {
          String name = scanner.nextLine();
          String[] command = name.split("\\s+");

          if (!guest.contains(command[0]) && !command[2].equals("not")){
              guest.add(command[0]);
          } else if (guest.contains(command[0]) && !command[2].equals("not")){
              System.out.printf("%s is already in the list!%n", command[0]);
          } else if (guest.contains(command[0]) && command[2].equals("not")){
              guest.remove(command[0]);
          } else if (!guest.contains(command[0]) && command[2].equals("not")){
              System.out.printf("%s is not in the list!%n", command[0]);
          }
        }
        for (String s : guest) {
            System.out.println(s);
        }
    }
}
