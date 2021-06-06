package T06_Generics.Exercise.P04_CustomList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomList<T extends Comparable<T>> {
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
        return elements.stream().max((e1, e2) -> e1.compareTo(e2)).get();
    }
    public T getMin(){
        return elements.stream().min((e1, e2) -> e1.compareTo(e2)).get();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T element : elements) {
            builder.append(element).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
