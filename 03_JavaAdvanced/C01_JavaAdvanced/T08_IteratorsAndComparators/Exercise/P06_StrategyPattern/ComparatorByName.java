package T08_IteratorsAndComparators.Exercise.P06_StrategyPattern;

import java.util.Comparator;

public class ComparatorByName implements Comparator<Person> {
    @Override
    public int compare(Person one, Person two) {
        int result = Integer.compare(one.getName().length(), two.getName().length());
        if (result == 0) {
            String firstName = one.getName().toLowerCase();
            String secondName = two.getName().toLowerCase();
            String firstLetter = String.valueOf(firstName.charAt(0));
            String secondLetter = String.valueOf(secondName.charAt(0));

            result = firstLetter.compareTo(secondLetter);
        }
        return result;
    }
}
