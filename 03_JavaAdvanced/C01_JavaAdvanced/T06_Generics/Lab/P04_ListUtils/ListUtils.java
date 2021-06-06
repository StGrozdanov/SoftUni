package T06_Generics.Lab.P04_ListUtils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils<T> {
    private List<T> elements;

    public ListUtils(List<T> elements) {
        this.elements = new ArrayList<>();
    }

    public static <T extends Comparable<T>> T getMin(List<T> elements){
        if (elements.size() == 0){
            throw new IllegalArgumentException();
        }
        return elements.stream().min((e1, e2) -> e1.compareTo(e2)).get();
    }

    public static <T extends Comparable<T>> T getMax(List<T> elements){
        if (elements.size() == 0){
            throw new IllegalArgumentException();
        }
        return elements.stream().max((e1, e2) -> e1.compareTo(e2)).get();
    }

}
