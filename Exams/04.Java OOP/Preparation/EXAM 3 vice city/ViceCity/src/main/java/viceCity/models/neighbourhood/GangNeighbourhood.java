package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        Deque<Gun> playerGuns = new ArrayDeque<>(mainPlayer.getGunRepository().getModels());

        while (!playerGuns.isEmpty() && !civilPlayers.isEmpty()) {
            Gun currentPlayerGun = playerGuns.poll();
            while (currentPlayerGun.canFire()) {
                Player civilPlayerTarget = civilPlayers.iterator().next();
                civilPlayerTarget.takeLifePoints(currentPlayerGun.fire());
                if (!civilPlayerTarget.isAlive()) {
                    civilPlayers.remove(civilPlayerTarget);
                    if (!civilPlayers.isEmpty()) {
                        civilPlayerTarget = civilPlayers.iterator().next();
                    } else {
                        break;
                    }
                }
            }
        }

        for (Player civil : civilPlayers) {
            Deque<Gun> civilPlayerGuns = new ArrayDeque<>(civil.getGunRepository().getModels());
            while (!civilPlayerGuns.isEmpty() && mainPlayer.isAlive()) {
                Gun currentPlayerGun = civilPlayerGuns.poll();
                while (currentPlayerGun.canFire()) {
                    mainPlayer.takeLifePoints(currentPlayerGun.fire());
                    if (!mainPlayer.isAlive()) {
                        return;
                    }
                }
            }
        }
    }

}
