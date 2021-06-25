package TestExams.Е05.P03_Rabbits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {
    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Rabbit rabbit) {
        if (this.capacity > 0) {
            this.data.add(rabbit);
            this.capacity--;
        }
    }

    public boolean removeRabbit(String name) {
        Rabbit rabbit = this.data.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
        if (rabbit != null) {
            this.data.remove(rabbit);
            return true;
        }
        return false;
    }

    public void removeSpecies(String species) {
        List<Rabbit> rabbitSpecies = this.data.stream().filter(r -> r.getSpecies().equals(species))
                .collect(Collectors.toList());
        if (rabbitSpecies.size() > 0) {
            this.data.removeAll(rabbitSpecies);
        }
        //might be wrong
    }

    public Rabbit sellRabbit(String name) {
        Rabbit rabbit = this.data.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
        if (rabbit != null) {
            rabbit.setAvailable(false);
        }
        return rabbit;
        //might return null ????
    }

    public List<Rabbit> sellRabbitBySpecies(String species) {
        List<Rabbit> rabbitSpecies = this.data.stream().filter(r -> r.getSpecies().equals(species))
                .collect(Collectors.toList());
        if (rabbitSpecies.size() > 0) {
            rabbitSpecies.forEach(r -> r.setAvailable(false));
        }
        return rabbitSpecies;
        //might also return null ....
    }

    public int count() {
        return this.data.size();
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Rabbits available at %s:", this.name)).append(System.lineSeparator());
        List<Rabbit> availableRabbits = this.data.stream().filter(r -> r.isAvailable()).collect(Collectors.toList());
        for (Rabbit availableRabbit : availableRabbits) {
            builder.append(availableRabbit).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

}
