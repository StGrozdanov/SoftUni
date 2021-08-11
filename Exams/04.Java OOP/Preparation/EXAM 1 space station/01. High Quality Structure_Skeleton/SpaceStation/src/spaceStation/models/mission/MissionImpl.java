package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission {
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        List<Astronaut> astronautsToRemove = new ArrayList<>();
        List<String> itemsToRemove = new ArrayList<>();

        for (Astronaut astronaut : astronauts) {
            if (astronaut.canBreath()) {
                Collection<String> items = planet.getItems();
                while (!items.isEmpty() && astronaut.canBreath()) {
                    for (String item : items) {
                        if (astronaut.canBreath()) {
                            astronaut.breath();
                            astronaut.getBag().getItems().add(item);
                            itemsToRemove.add(item);
                        } else {
                            astronautsToRemove.add(astronaut);
                            break;
                        }
                    }
                    for (String target : itemsToRemove) {
                        items.remove(target);
                    }
                }
            }
        }
        for (Astronaut target : astronautsToRemove) {
            astronauts.remove(target);
        }
    }
}
