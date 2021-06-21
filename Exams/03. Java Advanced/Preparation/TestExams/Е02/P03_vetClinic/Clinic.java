package TestExams.Е02.P03_vetClinic;

import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private List<Pet> data;
    private int capacity;

    public Clinic(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (capacity > 0) {
            this.data.add(pet);
            this.capacity--;
        }
    }

    public boolean remove(String name) {
        for (Pet pet : data) {
            if (pet.getName().equals(name)) {
                data.remove(pet);
                return true;
            }
        }
        return false;
    }

    public Pet getOldestPet() {
        int oldest = -100;
        for (Pet pet : data) {
            if (pet.getAge() > oldest) {
                oldest = pet.getAge();
            }
        }
        for (Pet pet : data) {
            if (pet.getAge() == oldest) {
                return pet;
            }
        }
        return null;
    }

    public Pet getPet(String name, String owner) {
        return data.stream().filter(e -> e.getName().equals(name) && e.getOwner().equals(owner)).findFirst()
                .orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();

        builder.append("The clinic has the following patients:").append(System.lineSeparator());
        for (Pet pet : data) {
            builder.append(pet.getName());
            builder.append(" ");
            builder.append(pet.getOwner());
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
