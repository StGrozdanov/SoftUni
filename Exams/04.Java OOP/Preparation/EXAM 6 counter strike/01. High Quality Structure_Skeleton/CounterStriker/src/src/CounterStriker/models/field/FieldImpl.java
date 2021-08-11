package CounterStriker.models.field;

import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static CounterStriker.common.OutputMessages.COUNTER_TERRORIST_WINS;
import static CounterStriker.common.OutputMessages.TERRORIST_WINS;

public class FieldImpl implements Field {
    private List<Player> terrorists;
    private List<Player> counterTerrorists;

    public FieldImpl() {
        this.terrorists = new ArrayList<>();
        this.counterTerrorists = new ArrayList<>();
    }

    @Override
    public String start(Collection<Player> players) {
        this.terrorists = players.stream().filter(p -> p.getClass().getSimpleName().equals("Terrorist"))
                .collect(Collectors.toList());

        this.counterTerrorists = players.stream().filter(p -> p.getClass().getSimpleName()
                .equals("CounterTerrorist"))
                .collect(Collectors.toList());

        while (!terrorists.isEmpty() && !counterTerrorists.isEmpty()) {
            for (Player terrorist : terrorists) {
                for (Player counterTerrorist : counterTerrorists) {
                    if (counterTerrorist.isAlive()) {
                        counterTerrorist.takeDamage(terrorist.getGun().fire());
                    }
                }
            }
            counterTerrorists = counterTerrorists.stream().filter(Player::isAlive).collect(Collectors.toList());
            for (Player counterTerrorist : counterTerrorists) {
                for (Player terrorist : terrorists) {
                    if (terrorist.isAlive()) {
                        terrorist.takeDamage(counterTerrorist.getGun().fire());
                    }
                }
            }
            terrorists = terrorists.stream().filter(Player::isAlive).collect(Collectors.toList());
        }
        if (terrorists.isEmpty()){
            return COUNTER_TERRORIST_WINS;
        }
        return TERRORIST_WINS;
    }
}
