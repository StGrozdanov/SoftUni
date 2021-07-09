package T06_Reflection.Exercise.P01_HarvestingFields;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!"HARVEST".equals(input)) {
            switch (input) {
                case "private":
                    Arrays.stream(RichSoilLand.class.getDeclaredFields())
                            .filter(e -> Modifier.isPrivate(e.getModifiers()))
                            .forEach(f -> {
                                System.out.printf("private %s %s%n",
                                        f.getType().getSimpleName(),
                                        f.getName());
                            });
                    break;
                case "protected":
                    Arrays.stream(RichSoilLand.class.getDeclaredFields())
                            .filter(e -> Modifier.isProtected(e.getModifiers()))
                            .forEach(f -> {
                                System.out.printf("protected %s %s%n",
                                        f.getType().getSimpleName(),
                                        f.getName());
                            });
                    break;
                case "public":
                    Arrays.stream(RichSoilLand.class.getDeclaredFields())
                            .filter(e -> Modifier.isPublic(e.getModifiers()))
                            .forEach(f -> {
                                System.out.printf("public %s %s%n",
                                        f.getType().getSimpleName(),
                                        f.getName());
                            });
                    break;
                case "all":
                    Arrays.stream(RichSoilLand.class.getDeclaredFields())
                            .forEach(f -> {
                                System.out.printf("%s %s %s%n",
                                        Modifier.toString(f.getModifiers()),
                                        f.getType().getSimpleName(),
                                        f.getName());
                            });
                    break;
            }
            input = scanner.nextLine();
        }
    }
}
