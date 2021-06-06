package T05_FunctionalProgramming.Exercise;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> coming = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        List<String> people = new ArrayList<>();

        Predicate<String> startsWith = criteria -> {
            for (String person : coming) {
                if (person.startsWith(criteria)) {
                    people.add(person);
                    return true;
                }
            }
            return false;
        };
        Predicate<String> endsWith = criteria -> {
            for (String person : coming) {
                if (person.endsWith(criteria)) {
                    people.add(person);
                    return true;
                }
            }
            return false;
        };
        Predicate<Integer> size = criteria -> {
            for (String person : coming) {
                if (person.length() == criteria) {
                    people.add(person);
                    return true;
                }
            }
            return false;
        };


        String input = scanner.nextLine();
        List<String> newcomers = new ArrayList<>();

        while (!"Party!".equals(input)) {
            String[] tokens = input.split("\\s+");
            String action = tokens[0];
            String criteria = tokens[1];
            if (action.equals("Double")) {
                switch (criteria) {
                    case "StartsWith":
                        String startTarget = tokens[2];
                        if (startsWith.test(startTarget)) {
                            newcomers.addAll(doubleThemUp(people.get(0), people));
                            while (coming.contains(people.get(0))) {
                                coming.remove(people.get(0));
                            }
                            coming.addAll(newcomers);
                            people.clear();
                        }
                        break;
                    case "EndsWith":
                        String endTarget = tokens[2];
                        if (endsWith.test(endTarget)) {
                            newcomers.addAll(doubleThemUp(people.get(0), people));
                            while (coming.contains(people.get(0))) {
                                coming.remove(people.get(0));
                            }
                            coming.addAll(newcomers);
                            people.clear();
                        }
                        break;
                    case "Length":
                        int length = Integer.parseInt(tokens[2]);
                        if (size.test(length)) {
                            newcomers.addAll(doubleThemUp(people.get(0), people));
                            while (coming.contains(people.get(0))) {
                                coming.remove(people.get(0));
                            }
                            coming.addAll(newcomers);
                            people.clear();
                        }
                        break;
                }
            } else if (action.equals("Remove")) {
                switch (criteria) {
                    case "StartsWith":
                        String startTarget = tokens[2];
                        if (startsWith.test(startTarget)) {
                            while (coming.contains(people.get(0))) {
                                coming.remove(people.get(0));
                            }
                            people.clear();
                        }
                        break;
                    case "EndsWith":
                        String endTarget = tokens[2];
                        if (endsWith.test(endTarget)) {
                            while (coming.contains(people.get(0))) {
                                coming.remove(people.get(0));
                            }
                            people.clear();
                        }
                        break;
                    case "Length":
                        int length = Integer.parseInt(tokens[2]);
                        if (size.test(length)) {
                            while (coming.contains(people.get(0))) {
                                coming.remove(people.get(0));
                            }
                            people.clear();
                        }
                        break;
                }
            }
            input = scanner.nextLine();
        }
        if (coming.isEmpty()) {
            System.out.println("Nobody is going to the party!");
        } else {
            List<String> collect = coming.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
            String result = String.join(", ", collect);
            System.out.println(result + " are going to the party!");
        }
    }

    private static List<String> doubleThemUp(String person, List<String> people) {
        int repeat = people.size() * 2;
        List<String> newPeople = new ArrayList<>();

        for (int i = 0; i < repeat; i++) {
            newPeople.add(person);
        }
        return newPeople;
    }
}
