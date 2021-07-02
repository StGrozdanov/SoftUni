package Т05_Polymorphism.Lab.P02_Circles;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    public Double getPerimeter() {
        return perimeter;
    }

    public Double getArea() {
        return area;
    }

    public abstract Double calculatePerimeter();
    public abstract Double calculateArea();
}
