package T08_IteratorsAndComparators.Exercise.P06_StrategyPattern;

import java.util.Comparator;

public class ComparatorByAge implements Comparator<Person> {
    @Override
    public int compare(Person one, Person two) {
        return Integer.compare(one.getAge(), two.getAge());
    }
}
