package T03_Inheritance.Exercise.P06_Animals;

public class Validator {

    public static void ageValidator(int age) {
        if (age < 0) {
            throw new IllegalStateException("Invalid input!");
        }
    }

    public static void nameValidator(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Invalid input!");
        }
    }

    public static void genderValidator(String gender) {
        if (gender == null || gender.trim().isEmpty() || !gender.equals("Male") && !gender.equals("Female")) {
            throw new IllegalStateException("Invalid input!");
        }
    }

}
