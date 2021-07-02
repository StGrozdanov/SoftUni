package Т05_Polymorphism.Lab.P02_Circles;

import static java.lang.Math.PI;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius){
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    public Double calculatePerimeter() {
        return PI * (2 * radius);
    }

    @Override
    public Double calculateArea() {
        return PI * radius * radius;
    }
}
