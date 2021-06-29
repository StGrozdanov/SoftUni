package T03_Inheritance.Exercise.P04_NeedForSpeed;

public class Main {
    public static void main(String[] args) {
        System.out.println(Car.getDefaultFuelConsumption());
        Car car = new Car(10, 20);
        System.out.println(car.getFuelConsumption());
        System.out.println(Car.getDefaultFuelConsumption());
    }
}
