package T02_Encapsulation.Exercise.P04_Pizza;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    private void setFlourType(String flourType) {
        Validator.flourTypeValidator(flourType);
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        Validator.bakingTechniqueValidator(bakingTechnique);
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        Validator.doughWeightValidator(weight);
        this.weight = weight;
    }

    public double calculateCalories() {
        double bakingModifier = 0.0;
        switch (this.bakingTechnique) {
            case "Crispy":
                bakingModifier = 0.9;
                break;
            case "Chewy":
                bakingModifier = 1.1;
                break;
            case "Homemade":
                bakingModifier = 1.0;
                break;
        }
        double flourModifier = 0.0;
        switch (this.flourType) {
            case "White":
                flourModifier = 1.5;
                break;
            case "Wholegrain":
                flourModifier = 1.0;
                break;
        }
        return (this.weight * 2) * bakingModifier * flourModifier;
    }
}
