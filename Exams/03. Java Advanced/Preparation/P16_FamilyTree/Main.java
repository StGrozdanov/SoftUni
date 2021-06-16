package MidExamPreparation.P16_FamilyTree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String targetPersonData = scanner.nextLine();
        String dataType = dataType(targetPersonData);
        Person mainPerson = new Person();
        List<Person> people = new ArrayList<>();
        List<String> familyTies = new ArrayList<>();

        String input = scanner.nextLine();
        while (!"End".equals(input)) {
            if (input.contains("-")) {
                familyTies.add(input);
            } else {
                String[] tokens = input.split("\\s+");
                String firstName = tokens[0];
                String lastName = tokens[1];
                String birthday = tokens[2];
                String fullName = firstName + " " + lastName;
                boolean mainPersonFound = false;
                if (dataType.equals("name")) {
                    if (fullName.equals(targetPersonData)) {
                        mainPerson = new Person(fullName, birthday);
                        mainPersonFound = true;
                    }
                } else if (dataType.equals("birthday")) {
                    if (birthday.equals(targetPersonData)) {
                        mainPerson = new Person(fullName, birthday);
                        mainPersonFound = true;
                    }
                }
                if (!mainPersonFound) {
                    Person person = new Person(fullName, birthday);
                    people.add(person);
                }
            }
            input = scanner.nextLine();
        }

        findChildren(mainPerson, people, familyTies);
        findParents(mainPerson, people, familyTies);

        System.out.println(mainPerson);
        System.out.println("Parents:");
        mainPerson.getParents().forEach(p -> System.out.println(p));
        System.out.println("Children:");
        mainPerson.getChildren().forEach(c -> System.out.println(c));

    }

    private static void findChildren(Person mainPerson, List<Person> people, List<String> familyTies) {
        for (String tie : familyTies) {
            String[] tokens = tie.split("-");
            String parentData = tokens[0].trim();
            String childData = tokens[1].trim();
            if (dataType(parentData).equals("name") && dataType(childData).equals("name")) {
                String[] nameTokens = parentData.split("\\s+");
                String fullName = nameTokens[0] + " " + nameTokens[1];
                if (fullName.equals(mainPerson.getName())) {
                    String[] childTokens = childData.split("\\s+");
                    String childName = childTokens[0] + " " + childTokens[1];
                    Person child = people.stream().filter(p -> p.getName().equals(childName)).findFirst().orElse(null);
                    mainPerson.addChildren(child);
                }

            } else if (dataType(parentData).equals("birthday") && dataType(childData).equals("birthday")) {
                String pBirthday = parentData.trim();
                String cBirthday = childData.trim();
                if (pBirthday.equals(mainPerson.getBirthday())) {
                    Person child = people.stream().filter(p -> p.getBirthday().equals(cBirthday)).findFirst().orElse(null);
                    mainPerson.addChildren(child);
                }
            } else if (dataType(parentData).equals("name") && dataType(childData).equals("birthday")) {
                String[] nameTokens = parentData.split("\\s+");
                String fullName = nameTokens[0] + " " + nameTokens[1];
                if (fullName.equals(mainPerson.getName())) {
                    String cBirthday = childData.trim();
                    Person child = people.stream().filter(p -> p.getBirthday().equals(cBirthday)).findFirst().orElse(null);
                    mainPerson.addChildren(child);
                }
            } else {
                String[] nameTokens = childData.split("\\s+");
                String fullName = nameTokens[0] + " " + nameTokens[1];
                String pBirthday = parentData.trim();
                if (pBirthday.equals(mainPerson.getBirthday())) {
                    Person child = people.stream().filter(p -> p.getName().equals(fullName)).findFirst().orElse(null);
                    mainPerson.addChildren(child);
                }
            }
        }
    }

    private static void findParents(Person mainPerson, List<Person> people, List<String> familyTies) {
        for (String tie : familyTies) {
            String[] tokens = tie.split("-");
            String parentData = tokens[0].trim();
            String childData = tokens[1].trim();
            if (dataType(parentData).equals("name") && dataType(childData).equals("name")) {
                String[] nameTokens = childData.split("\\s+");
                String fullName = nameTokens[0] + " " + nameTokens[1];
                if (fullName.equals(mainPerson.getName())) {
                    String[] parentTokens = parentData.split("\\s+");
                    String parentName = parentTokens[0] + " " + parentTokens[1];
                    Person parent = people.stream().filter(p -> p.getName().equals(parentName)).findFirst().orElse(null);
                    mainPerson.addParents(parent);
                }

            } else if (dataType(parentData).equals("birthday") && dataType(childData).equals("birthday")) {
                String pBirthday = parentData.trim();
                String cBirthday = childData.trim();
                if (cBirthday.equals(mainPerson.getBirthday())) {
                    Person parent = people.stream().filter(p -> p.getBirthday().equals(pBirthday)).findFirst().orElse(null);
                    mainPerson.addParents(parent);
                }
            } else if (dataType(parentData).equals("name") && dataType(childData).equals("birthday")) {
                String[] nameTokens = parentData.split("\\s+");
                String fullName = nameTokens[0] + " " + nameTokens[1];
                String cBirthday = childData.trim();
                if (cBirthday.equals(mainPerson.getBirthday())) {
                    Person parent = people.stream().filter(p -> p.getName().equals(fullName)).findFirst().orElse(null);
                    mainPerson.addParents(parent);
                }
            } else {
                String[] nameTokens = childData.split("\\s+");
                String fullName = nameTokens[0] + " " + nameTokens[1];
                String pBirthday = parentData.trim();
                if (fullName.equals(mainPerson.getName())) {
                    Person parent = people.stream().filter(p -> p.getBirthday().equals(pBirthday)).findFirst().orElse(null);
                    mainPerson.addParents(parent);
                }
            }
        }
    }

    private static String dataType(String targetPersonData) {
        if (targetPersonData.contains("/")) {
            return "birthday";
        }
        return "name";
    }
}
