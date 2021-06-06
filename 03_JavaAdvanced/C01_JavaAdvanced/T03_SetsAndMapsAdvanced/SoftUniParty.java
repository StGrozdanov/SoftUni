package T03_SetsAndMapsAdvanced.Lab;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SoftUniParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Set<String> vipGuest = new TreeSet<>();
        Set<String> regGuest = new TreeSet<>();

        while (!"PARTY".equals(input)) {
            char beginsWith = input.charAt(0);
            if (Character.isDigit(beginsWith)) {
                vipGuest.add(input);
            } else {
                regGuest.add(input);
            }
            input = scanner.nextLine();
        }

        input = scanner.nextLine();

        while (!"END".equals(input)) {
            vipGuest.remove(input);
            regGuest.remove(input);
            input = scanner.nextLine();
        }

        int missingGuests = vipGuest.size() + regGuest.size();
        System.out.println(missingGuests);
            printTheSet(vipGuest);
            printTheSet(regGuest);
    }

    private static void printTheSet(Set<String> list) {
        if (!list.isEmpty()) {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}
