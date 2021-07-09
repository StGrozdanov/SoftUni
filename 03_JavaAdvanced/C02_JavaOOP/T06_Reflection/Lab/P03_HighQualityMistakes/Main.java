package T06_Reflection.Lab.P03_HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static class CompareMethods implements Comparator<Method> {
        @Override
        public int compare(Method o1, Method o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static void main(String[] args) {

        Method[] methods = Reflection.class.getDeclaredMethods();

        Set<Method> getters = new TreeSet<>(new CompareMethods());
        Set<Method> setters = new TreeSet<>(new CompareMethods());

        for (Method method : methods) {
            if (method.getName().contains("get")) {
                getters.add(method);
            } else if (method.getName().contains("set")) {
                setters.add(method);
            }
        }

        Arrays.stream(Reflection.class.getDeclaredFields())
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.printf("%s must be private!%n", f.getName()));

        for (Method getter : getters) {
            if (!Modifier.isPublic(getter.getModifiers())) {
                System.out.printf("%s have to be public!%n", getter.getName());
            }
        }
        for (Method setter : setters) {
            if (!Modifier.isPrivate(setter.getModifiers())) {
                System.out.printf("%s have to be private!%n", setter.getName());
            }
        }
    }
}
