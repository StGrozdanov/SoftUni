package T06_Generics.Exercise.P03_GenericCountMethodString;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Comparable<T>> {
    private List<T> list;

    public Box() {
        this.list = new ArrayList<>();
    }

    public void addElements(T element){
        this.list.add(element);
    }

    public int countTheGreaterElements(T comparable){
        int counter = 0;
        for (T element : list) {
            if (element.compareTo(comparable) > 0){
                counter++;
            }
        }
        return counter;
    }
}
