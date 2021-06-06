package T06_Generics.Lab.P03_Scale;

public class Main {
    public static void main(String[] args) {

        Scale<String> stringScale = new Scale<>("a", "c");
        Scale<Integer> integerScale = new Scale<>(25, 5);
        System.out.println(stringScale.getHeavier());
        System.out.println(integerScale.getHeavier());
    }
}
