package ExamPreparation.P01_FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Team> teams = new ArrayList<>();

        while (!"END".equals(input)) {
            String[] tokens = input.split(";");
            String cmd = tokens[0];
            String teamName = tokens[1];
            switch (cmd) {
                case "Team":
                    try {
                        teams.add(new Team(teamName));
                    } catch (IllegalStateException e){
                        System.out.print(e.getMessage());
                    }
                    break;
                case "Add":
                    Team targetTeam = getTeam(teams, teamName);
                    if (targetTeam == null) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        Player player;
                        try {
                            player = new Player(tokens[2], Integer.parseInt(tokens[3]),
                                    Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]),
                                    Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]));
                            targetTeam.addPlayer(player);
                        } catch (IllegalStateException e) {
                            System.out.print(e.getMessage());
                        }
                    }
                    break;
                case "Remove":
                    Team targetTeamToRemoveFrom = getTeam(teams, teamName);
                    if (targetTeamToRemoveFrom == null) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        targetTeamToRemoveFrom.removePlayer(tokens[2]);
                    }
                    break;
                case "Rating":
                    Team targetTeamToRate = getTeam(teams, teamName);
                    if (targetTeamToRate == null) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        System.out.println(targetTeamToRate);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
    }

    private static Team getTeam(List<Team> teams, String teamName) {
        return teams.stream().filter(t -> t.getName().equals(teamName)).findFirst()
                .orElse(null);
    }
}
