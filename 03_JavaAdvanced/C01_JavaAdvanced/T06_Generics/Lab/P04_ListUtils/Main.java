package T06_Generics.Lab.P04_ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>();
        Collections.addAll(integerList, 1, 2, 18, 2, -1);

        Integer maxInteger = ListUtils.getMax(integerList);

        List<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, "a", "b", "c");

        String minString = ListUtils.getMin(stringList);

        System.out.println(maxInteger);
        System.out.println(minString);
    }
}
