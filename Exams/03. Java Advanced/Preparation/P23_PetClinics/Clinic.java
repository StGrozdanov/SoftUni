package MidExamPreparation.P23_PetClinics;

public class Clinic {
    private String name;
    private int rooms;
    private Pet[] patients;

    public Clinic(String name, int rooms) {
        this.name = name;
        this.rooms = rooms;
        patients = new Pet[rooms];
        for (int i = 0; i < patients.length; i++) {
            patients[i] = null;
        }
    }

    public String getName() {
        return name;
    }

    public boolean add(Pet petName) {
        if (isEmpty()) {
            for (int i = 0; i <= this.rooms / 2; i++) {
                if ((this.rooms / 2) - i >= 0 && patients[this.rooms / 2 - i] == null) {
                    patients[this.rooms / 2 - i] = petName;
                    return true;
                } else if ((this.rooms / 2) + i < patients.length && patients[this.rooms / 2 + i] == null) {
                    patients[this.rooms / 2 + i] = petName;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean release() {
        for (int i = this.rooms / 2; i < patients.length; i++) {
            if (patients[i] != null) {
                patients[i] = null;
                return true;
            }
        }
        for (int i = 0; i < this.rooms / 2; i++) {
            if (patients[i] != null) {
                patients[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        for (Pet patient : patients) {
            if (patient == null) {
                return true;
            }
        }
        return false;
    }

    public void printEach() {
        for (Pet patient : patients) {
            if (patient == null) {
                System.out.println("Room empty");
            } else {
                System.out.println(patient);
            }
        }
    }

    public void print(int room) {
        if (patients[room - 1] == null) {
            System.out.println("Room empty");
        } else {
            System.out.println(patients[room - 1]);
        }
    }
}
