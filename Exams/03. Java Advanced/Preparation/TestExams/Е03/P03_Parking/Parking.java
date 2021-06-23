package TestExams.Е03.P03_Parking;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private List<Car> data;
    private String type;
    private int capacity;

    public Parking(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Car car) {
        if (this.capacity > 0) {
            this.data.add(car);
            this.capacity--;
        }
    }

    public boolean remove(String manufacturer, String model) {
        Car target = data.stream().filter(c -> c.getManufacturer().equals(manufacturer) && c.getModel().equals(model))
                .findFirst().orElse(null);
        if (target != null) {
            data.remove(target);
            return true;
        }
        return false;
    }

    public Car getLatestCar() {
        if (data.isEmpty()) {
            return null;
        }
        int newestCar = -1;
        for (Car car : data) {
            if (car.getYear() > newestCar) {
                newestCar = car.getYear();
            }
        }
        for (Car car : data) {
            if (car.getYear() == newestCar) {
                return car;
            }
        }
        return null;
    }

    public Car getCar(String manufacturer, String model) {
        return this.data.stream().filter(c -> c.getManufacturer().equals(manufacturer) && c.getModel().equals(model))
                .findFirst().orElse(null);
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append("The cars are parked in ").append(this.type).append(":").append(System.lineSeparator());
        for (Car car : data) {
            builder.append(car);
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
