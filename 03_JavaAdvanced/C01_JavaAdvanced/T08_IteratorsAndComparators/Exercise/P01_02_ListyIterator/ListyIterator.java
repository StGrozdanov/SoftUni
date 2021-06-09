package T08_IteratorsAndComparators.Exercise.P01_02_ListyIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListyIterator implements Iterable<String> {
    private List<String> collection;
    private int index = 0;

    public ListyIterator(String... collection) {
        this.collection = Arrays.asList(collection);
    }


    @Override
    public String toString() {
        if (collection.isEmpty()) {
            return "Invalid Operation!";
        } else {
            return collection.get(index);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterate();
    }

    public void print() {
        if (collection.isEmpty()) {
            System.out.println("Invalid Operation!");
        } else {
            for (String s : collection) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    private class Iterate implements Iterator<String> {
        @Override
        public boolean hasNext() {
            return index < collection.size() - 1;
        }

        @Override
        public String next() {
            return collection.get(index++);
        }
    }
}
