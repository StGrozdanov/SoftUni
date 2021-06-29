package T02_Encapsulation.Exercise.P02_AnimalFarm;

public class Validator {

    public static void geometryValidator(double unit, String type) {
        if (unit <= 0) {
            throw new IllegalStateException(String.format("%s cannot be zero or negative.", type));
        }
    }

    public static void ageValidator(int age) {
        if (age < 0 || age > 15) {
            throw new IllegalStateException("Age should be between 0 and 15.");
        }
    }

    public static void nameValidator(String name){
        if (name == null || name.trim().length() == 0){
            throw new IllegalStateException("Name cannot be empty.");
        }
    }

}
