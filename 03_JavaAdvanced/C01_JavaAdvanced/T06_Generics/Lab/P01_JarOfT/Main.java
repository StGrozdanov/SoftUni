package T06_Generics.Lab.P01_JarOfT;

public class Main {
    public static void main(String[] args) {

        Jar<String> jar = new Jar<>();

        jar.add("nice");
        jar.add("to hit");

        String element = jar.remove();
    }
}
