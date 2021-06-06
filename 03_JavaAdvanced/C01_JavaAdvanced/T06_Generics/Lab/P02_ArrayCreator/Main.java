package T06_Generics.Lab.P02_ArrayCreator;

public class Main {
    public static void main(String[] args) {

        String[] strings = ArrayCreator.create(10, "none");
        Integer[] ints = ArrayCreator.create(Integer.class, 10, 5);

    }
}
