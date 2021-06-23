package TestExams.Е04.P03_Guild;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Guild {
    private List<Player> roster;
    private String name;
    private int capacity;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (this.capacity > 0) {
            this.roster.add(player);
            this.capacity--;
        }
    }

    public boolean removePlayer(String name) {
        Player player = this.roster.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
        if (player != null) {
            this.roster.remove(player);
            return true;
        }
        return false;
    }

    public void promotePlayer(String name) {
        Player player = this.roster.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
        if (player != null && !player.getRank().equals("Member")) {
            player.setRank("Member");
        }
    }

    public void demotePlayer(String name) {
        Player player = this.roster.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
        if (player != null && !player.getRank().equals("Trial")) {
            player.setRank("Trial");
        }
    }

    public Player[] kickPlayersByClass(String clazz) {
        List<Player> removedPlayers = this.roster.stream().filter(p -> p.getClazz().equals(clazz))
                .collect(Collectors.toList());

        this.roster = this.roster.stream().filter(p -> !p.getClazz().equals(clazz)).collect(Collectors.toList());

        Player[] players = new Player[removedPlayers.size()];
        for (int i = 0; i < players.length; i++) {
            players[i] = removedPlayers.get(i);
        }
        return players;
    }

    public int count(){
        return this.roster.size();
    }

    public String report(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Players in the guild: %s:", this.name)).append(System.lineSeparator());
        for (Player player : roster) {
            builder.append(player.toString());
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
