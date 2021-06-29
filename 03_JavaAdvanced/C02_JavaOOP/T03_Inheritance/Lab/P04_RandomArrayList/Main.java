package T03_Inheritance.Lab.P04_RandomArrayList;

public class Main {
    public static void main(String[] args) {
        RandomArrayList list = new RandomArrayList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(list.getRandomElement());
    }
}
