package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{
    public Biologist(String name) {
        super(name, 70);
    }

    @Override
    public void breath() {
        super.setOxygen(this.getOxygen() - 5);
    }
}
