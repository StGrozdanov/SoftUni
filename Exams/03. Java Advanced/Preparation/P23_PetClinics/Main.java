package MidExamPreparation.P23_PetClinics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Pet> pets = new ArrayList<>();
        List<Clinic> clinics = new ArrayList<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String cmd = tokens[0];
            switch (cmd) {
                case "Create":
                    String clazz = tokens[1];
                    if (clazz.equals("Pet")) {
                        String name = tokens[2];
                        int age = Integer.parseInt(tokens[3]);
                        String kind = tokens[4];
                        Pet pet = new Pet(name, age, kind);
                        pets.add(pet);
                    } else if (clazz.equals("Clinic")) {
                        String name = tokens[2];
                        int rooms = Integer.parseInt(tokens[3]);
                        if (rooms % 2 == 0) {
                            System.out.println("Invalid Operation!");
                        } else {
                            Clinic clinic = new Clinic(name, rooms);
                            clinics.add(clinic);
                        }
                    }
                    break;
                case "Add":
                    String petName = tokens[1];
                    String clinicName = tokens[2];
                    if (checkIfWeHaveThePet(petName, pets) && checkIfWeHaveTheClinic(clinicName, clinics)) {
                        Pet targetPet = pets.stream().filter(p -> p.getName().equals(petName)).findFirst()
                                .orElse(null);
                        Clinic targetClinic = clinics.stream().filter(c -> c.getName().equals(clinicName)).findFirst()
                                .orElse(null);
                        System.out.println(targetClinic.add(targetPet));
                    } else {
                        System.out.println("Invalid Operation!");
                    }
                    break;
                case "Release":
                    String clinic = tokens[1];
                    if (checkIfWeHaveTheClinic(clinic, clinics)) {
                        Clinic targetClinic = clinics.stream().filter(c -> c.getName().equals(clinic)).findFirst()
                                .orElse(null);
                        System.out.println(targetClinic.release());
                    } else {
                        System.out.println("Invalid Operation!");
                    }
                    break;
                case "HasEmptyRooms":
                    String clinicRoom = tokens[1];
                    if (checkIfWeHaveTheClinic(clinicRoom, clinics)) {
                        Clinic targetClinic = clinics.stream().filter(c -> c.getName().equals(clinicRoom)).findFirst()
                                .orElse(null);
                        System.out.println(targetClinic.isEmpty());
                    } else {
                        System.out.println("Invalid Operation!");
                    }
                    break;
                case "Print":
                    String clinicToPrint = tokens[1];
                    if (tokens.length == 3) {
                        int room = Integer.parseInt(tokens[2]);
                        if (checkIfWeHaveTheClinic(clinicToPrint, clinics)) {
                            Clinic targetClinic = clinics.stream().filter(c -> c.getName().equals(clinicToPrint))
                                    .findFirst().orElse(null);
                            targetClinic.print(room);
                        } else {
                            System.out.println("Invalid Operation!");
                        }
                    } else {
                        if (checkIfWeHaveTheClinic(clinicToPrint, clinics)) {
                            Clinic targetClinic = clinics.stream().filter(c -> c.getName().equals(clinicToPrint))
                                    .findFirst().orElse(null);
                            targetClinic.printEach();
                        } else {
                            System.out.println("Invalid Operation!");
                        }
                    }
                    break;
            }
        }
    }

    private static boolean checkIfWeHaveTheClinic(String clinicName, List<Clinic> clinics) {
        for (Clinic clinic : clinics) {
            if (clinic.getName().equals(clinicName)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkIfWeHaveThePet(String petName, List<Pet> pets) {
        for (Pet pet : pets) {
            if (pet.getName().equals(petName)) {
                return true;
            }
        }
        return false;
    }
}
