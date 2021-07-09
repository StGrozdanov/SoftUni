package T06_Reflection.Lab.P02_GettersAndSetters;

import java.lang.reflect.Method;
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

        for (Method getter : getters) {
            System.out.printf("%s will return class %s%n", getter.getName(), getter.getReturnType()
                    .getName());
        }
        for (Method setter : setters) {
            System.out.printf("%s and will set field of class %s%n", setter.getName(),
                    Arrays.stream(setter.getParameterTypes())
                            .findFirst()
                            .orElse(null));
        }
    }
}
