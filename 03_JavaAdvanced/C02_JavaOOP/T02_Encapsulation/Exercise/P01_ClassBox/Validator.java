package T02_Encapsulation.Exercise.P01_ClassBox;

public class Validator {
    public static void geometryValidator(double unit, String type){
        if (unit <= 0){
            throw new IllegalStateException(String.format("%s cannot be zero or negative.", type));
        }
    }
}
