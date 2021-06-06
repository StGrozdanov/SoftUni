package T06_Generics.Exercise.P02_GenericSwapMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericSwapMethod<T> {
    private List<T> list;

    public GenericSwapMethod() {
        this.list = new ArrayList<>();
    }

    public void addElement(T element){
        this.list.add(element);
    }

    public void swap (int index1, int index2){
        Collections.swap(this.list, index1, index2);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T element : list) {
            builder.append(element.getClass().getName()).append(": ").append(element)
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
