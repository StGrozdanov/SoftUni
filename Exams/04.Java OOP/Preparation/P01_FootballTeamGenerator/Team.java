package ExamPreparation.P01_FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        setName(name);
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        Validator.nameValidator(name);
        this.name = name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String name) {
        Player player = players.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
        if (player != null) {
            this.players.remove(player);
        } else {
            System.out.printf("Player %s is not in %s team.%n", name, this.name);
        }
    }

    public double getRating() {
        List<Double> playersSkills = new ArrayList<>();
        for (Player player : players) {
            playersSkills.add(player.overallSkillLevel());
        }
        long round;
        try {
            round = Math.round(playersSkills.stream().mapToDouble(p -> Double.parseDouble(String.valueOf(p)))
                    .average().getAsDouble());
        } catch (NoSuchElementException e) {
            round = 0;
        }
        return round;
    }

    @Override
    public String toString() {
        return String.format("%s - %.0f", this.name, this.getRating());
    }
}
