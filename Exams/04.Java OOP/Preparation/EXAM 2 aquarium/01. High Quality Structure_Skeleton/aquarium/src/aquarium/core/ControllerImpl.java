package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        if (aquariumType.equals("FreshwaterAquarium")) {
            this.aquariums.add(new FreshwaterAquarium(aquariumName));
        } else if (aquariumType.equals("SaltwaterAquarium")) {
            this.aquariums.add(new SaltwaterAquarium(aquariumName));
        } else {
            throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        if (type.equals("Ornament")) {
            this.decorations.add(new Ornament());
        } else if (type.equals("Plant")) {
            this.decorations.add(new Plant());
        } else {
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {

        Decoration targetDecoration = this.decorations.findByType(decorationType);

        if (targetDecoration == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }

        Aquarium targetAquarium = getAquarium(aquariumName);

        targetAquarium.addDecoration(targetDecoration);
        this.decorations.remove(targetDecoration);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Aquarium targetAquarium = getAquarium(aquariumName);

        if (!fishType.equals("FreshwaterFish") && !fishType.equals("SaltwaterFish")) {
            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        try {
            if (fishType.equals("FreshwaterFish")) {
                targetAquarium.addFish(new FreshwaterFish(fishName, fishSpecies, price));
            } else {
                targetAquarium.addFish(new SaltwaterFish(fishName, fishSpecies, price));
            }
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium targetAquarium = getAquarium(aquariumName);

        targetAquarium.feed();

        return String.format(FISH_FED, targetAquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium targetAquarium = getAquarium(aquariumName);

        double totalPrice = calculateTotalAquariumPrice(targetAquarium);

        return String.format(VALUE_AQUARIUM, aquariumName, totalPrice);
    }

    @Override
    public String report() {
        return this.aquariums.stream().map(Aquarium::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }

    private Aquarium getAquarium(String aquariumName) {
        return this.aquariums.stream().filter(aquarium -> aquarium.getName().equals(aquariumName))
                .findFirst().orElse(null);
    }

    private double calculateTotalAquariumPrice(Aquarium targetAquarium) {
        return targetAquarium.getFish().stream().mapToDouble(Fish::getPrice).sum() +
                targetAquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
    }

}
