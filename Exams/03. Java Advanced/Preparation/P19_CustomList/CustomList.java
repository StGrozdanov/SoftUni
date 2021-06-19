package MidExamPreparation.P19_CustomList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomList<T extends Comparable<T>> implements Iterable<T> {
    private List<T> elements;

    public CustomList() {
        this.elements = new ArrayList<>();
    }

    public void add(T element){
        this.elements.add(element);
    }

    public void remove(int index){
        this.elements.remove(index);
    }

    public boolean contains(T element){
        return this.elements.contains(element);
    }

    public void swap (int index1, int index2){
        Collections.swap(this.elements, index1, index2);
    }

    public int countTheGreaterElements(T comparable){
        int counter = 0;
        for (T element : elements) {
            if (element.compareTo(comparable) > 0){
                counter++;
            }
        }
        return counter;
    }

    public T getMax(){
        return this.elements.stream().max((e1, e2) -> e1.compareTo(e2)).get();
    }
    public T getMin(){
        return this.elements.stream().min((e1, e2) -> e1.compareTo(e2)).get();
    }
    public void sort(){
        this.elements = elements.stream().sorted((e1, e2) -> e1.compareTo(e2)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T element : elements) {
            builder.append(element).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    @Override
    public Iterator<T> iterator() {
        return new customListIterator();
    }

    private class customListIterator implements Iterator<T> {
        private int counter = 0;

        @Override
        public boolean hasNext() {
            return this.counter < elements.size();
        }

        @Override
        public T next() {
            return elements.get(counter++);
        }
    }
}
