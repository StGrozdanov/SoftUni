package ExamPreparation.P02_MilitaryElite;

import ExamPreparation.P02_MilitaryElite.Models.*;
import ExamPreparation.P02_MilitaryElite.Objects.Mission;
import ExamPreparation.P02_MilitaryElite.Objects.Repair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        List<SoldierImpl> army = new ArrayList<>();

        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String type = tokens[0];
            int id = Integer.parseInt(tokens[1]);
            String firstName = tokens[2];
            String lastName = tokens[3];

            switch (type) {
                case "Private":
                    army.add(new PrivateImpl(id, firstName, lastName, Double.parseDouble(tokens[4])));
                    break;
                case "LieutenantGeneral":
                    LieutenantGeneralImpl lieutenant = new LieutenantGeneralImpl(id, firstName, lastName,
                            Double.parseDouble(tokens[4]));
                    for (int i = 5; i < tokens.length; i++) {
                        int soldierId = Integer.parseInt(tokens[i]);
                        PrivateImpl priv = (PrivateImpl) army.stream().filter(s -> s.getId() == soldierId)
                                .findFirst().orElse(null);
                        lieutenant.addPrivate(priv);
                    }
                    army.add(lieutenant);
                    break;
                case "Engineer":
                    try {
                        EngineerImpl engineer = new EngineerImpl(id, firstName, lastName, Double.parseDouble(tokens[4]),
                                tokens[5]);
                        for (int i = 6; i < tokens.length; i++) {
                            String partName = tokens[i];
                            int hoursWorked = Integer.parseInt(tokens[i + 1]);
                            Repair repair = new Repair(partName, hoursWorked);
                            engineer.addRepair(repair);
                            i++;
                        }
                        army.add(engineer);
                    } catch (IllegalStateException e) {
                        continue;
                    }
                    break;
                case "Commando":
                    try {
                        CommandoImpl commando = new CommandoImpl(id, firstName, lastName, Double.parseDouble(tokens[4]),
                                tokens[5]);
                        for (int i = 6; i < tokens.length; i++) {
                            String missionCodeName = tokens[i];
                            String missionState = tokens[i + 1];
                            try {
                                Mission mission = new Mission(missionCodeName, missionState);
                                commando.addMission(mission);
                            } catch (IllegalArgumentException ignored) {

                            }
                            i++;
                        }
                        army.add(commando);
                    } catch (IllegalStateException e) {
                        continue;
                    }
                    break;
                case "Spy":
                    army.add(new SpyIml(id, firstName, lastName, tokens[4]));
                    break;
            }
            input = scanner.nextLine();
        }
        army.forEach(System.out::println);
    }
}
