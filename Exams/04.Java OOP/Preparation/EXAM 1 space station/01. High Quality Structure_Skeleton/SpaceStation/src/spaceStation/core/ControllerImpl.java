package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private AstronautRepository astronauts;
    private PlanetRepository planets;
    private MissionImpl mission;
    private int exploredPlanets;

    public ControllerImpl() {
        this.astronauts = new AstronautRepository();
        this.planets = new PlanetRepository();
        this.mission = new MissionImpl();
        this.exploredPlanets = 0;
    }

    private void setExploredPlanets(int exploredPlanets) {
        this.exploredPlanets = exploredPlanets;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        if (type.equals("Biologist")) {
            this.astronauts.add(new Biologist(astronautName));
        } else if (type.equals("Geodesist")) {
            this.astronauts.add(new Geodesist(astronautName));
        } else if (type.equals("Meteorologist")) {
            this.astronauts.add(new Meteorologist(astronautName));
        } else {
            throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        for (String item : items) {
            planet.getItems().add(item);
        }
        this.planets.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        if (this.astronauts.findByName(astronautName) != null) {
            this.astronauts.remove(this.astronauts.findByName(astronautName));
            return String.format(ASTRONAUT_RETIRED, astronautName);
        }
        throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> suitableAstronauts =
                this.astronauts.getModels().stream().filter(a -> a.getOxygen() > 60).collect(Collectors.toList());
        Planet targetPlanet = this.planets.findByName(planetName);

        int initialSuitableAstronauts = suitableAstronauts.size();

        if (initialSuitableAstronauts == 0) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        mission.explore(targetPlanet, suitableAstronauts);

        this.setExploredPlanets(this.exploredPlanets + 1);

        int deadAstronauts = initialSuitableAstronauts - suitableAstronauts.size();

        return String.format(PLANET_EXPLORED, planetName, deadAstronauts);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(REPORT_PLANET_EXPLORED, this.exploredPlanets))
                .append(System.lineSeparator())
                .append(REPORT_ASTRONAUT_INFO)
                .append(System.lineSeparator());

        for (Astronaut astronaut : this.astronauts.getModels()) {
            sb.append(String.format(REPORT_ASTRONAUT_NAME, astronaut.getName()))
                    .append(System.lineSeparator())
                    .append(String.format(REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen()))
                    .append(System.lineSeparator());

            StringBuilder sb2 = new StringBuilder();

            if (astronaut.getBag().getItems().isEmpty()) {
                sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "none"))
                        .append(System.lineSeparator());
            } else {
                astronaut.getBag().getItems().forEach(item -> sb2.append(item)
                        .append(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER));

                String exit = sb2.substring(0, sb2.length() - 2);

                sb
                        .append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, exit))
                        .append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
