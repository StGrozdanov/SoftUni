package T02_Encapsulation.Exercise.P01_ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    private void setLength(double length) {
        Validator.geometryValidator(length, "Length");
        this.length = length;
    }

    private void setWidth(double width) {
        Validator.geometryValidator(width, "Width");
        this.width = width;
    }

    private void setHeight(double height) {
        Validator.geometryValidator(height, "Height");
        this.height = height;
    }

    public double calculateSurfaceArea() {
        return (2 * this.length * this.width) + (2 * this.length * this.height) + (2 * this.width * this.height);
    }

    public double calculateLateralSurfaceArea() {
        return (2 * this.length * this.height) + (2 * this.width * this.height);
    }

    public double calculateVolume() {
        return this.length * this.width * this.height;
    }
}
