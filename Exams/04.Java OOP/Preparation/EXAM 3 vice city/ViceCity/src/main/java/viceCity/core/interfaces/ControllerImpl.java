package viceCity.core.interfaces;

import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {

    private MainPlayer mainPlayer;
    private Set<Player> civilPlayers;
    private Set<Gun> guns;
    private GangNeighbourhood neighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.civilPlayers = new LinkedHashSet<>();
        this.guns = new LinkedHashSet<>();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        this.civilPlayers.add(new CivilPlayer(name));
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        if (type.equals("Pistol")) {
            this.guns.add(new Pistol(name));
        } else if (type.equals("Rifle")) {
            this.guns.add((new Rifle(name)));
        } else {
            return GUN_TYPE_INVALID;
        }
        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (this.guns.isEmpty()) {
            return GUN_QUEUE_IS_EMPTY;
        }
        Gun nextGun = this.guns.iterator().next();
        if (name.equals("Vercetti")) {
            this.mainPlayer.getGunRepository().add(nextGun);
            this.guns.remove(nextGun);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, nextGun.getName(), this.mainPlayer.getName());
        }
        Player civilPlayer = civilPlayers.stream().filter(p -> p.getName().equals(name)).findFirst()
                .orElse(null);
        if (civilPlayer == null) {
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        }
        civilPlayer.getGunRepository().add(nextGun);
        this.guns.remove(nextGun);
        return String.format(GUN_ADDED_TO_CIVIL_PLAYER, nextGun.getName(), civilPlayer.getName());
    }

    @Override
    public String fight() {
        int initialPlayerHp = this.mainPlayer.getLifePoints();
        int initialCivilPlayerHp = 0;
        int initialCivilPlayersCount = this.civilPlayers.size();

        for (Player player : civilPlayers) {
            initialCivilPlayerHp += player.getLifePoints();
        }

        neighbourhood.action(this.mainPlayer, this.civilPlayers);

        int postActionPlayerHp = this.mainPlayer.getLifePoints();
        int postActionCivilPlayerHp = 0;
        for (Player player : civilPlayers) {
            postActionCivilPlayerHp += player.getLifePoints();
        }

        if (postActionCivilPlayerHp == initialCivilPlayerHp && initialPlayerHp == postActionPlayerHp) {
            return FIGHT_HOT_HAPPENED;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(FIGHT_HAPPENED)
                    .append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, postActionPlayerHp))
                    .append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,
                            initialCivilPlayersCount - this.civilPlayers.size()))
                    .append(System.lineSeparator())
                    .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, this.civilPlayers.size()));

            return sb.toString().trim();
        }
    }
}
