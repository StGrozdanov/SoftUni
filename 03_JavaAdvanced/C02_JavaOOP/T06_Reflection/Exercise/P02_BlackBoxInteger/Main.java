package T06_Reflection.Exercise.P02_BlackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Constructor<BlackBoxInt> declaredConstructor = BlackBoxInt.class.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            BlackBoxInt box = declaredConstructor.newInstance();

            Method[] methods = box.getClass().getDeclaredMethods();
            String input = scanner.nextLine();

            while (!"END".equals(input)){
                String[] tokens = input.split("_");
                String cmd = tokens[0];
                int value = Integer.parseInt(tokens[1]);

                Method method = Arrays.stream(methods).filter(m -> m.getName().equals(cmd)).findFirst()
                        .orElse(null);
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(box, value);
                }

                Field innerValue = box.getClass().getDeclaredField("innerValue");
                innerValue.setAccessible(true);
                System.out.println(innerValue.getInt(box));

                input = scanner.nextLine();
            }

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
