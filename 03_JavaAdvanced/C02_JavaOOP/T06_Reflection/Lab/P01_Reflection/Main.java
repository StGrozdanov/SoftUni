package T06_Reflection.Lab.P01_Reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        System.out.println("class " + Reflection.class.getTypeName());
        System.out.println("class " + Reflection.class.getSuperclass().getName());
        Class<?>[] interfaces = Reflection.class.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }

        try {
            Reflection reflection = Reflection.class.getDeclaredConstructor().newInstance();
            System.out.println(reflection);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
