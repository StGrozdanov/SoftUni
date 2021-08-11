package aquarium.entities.fish;

public class FreshwaterFish extends BaseFish{
    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(3);
    }

    @Override
    public void eat() {
        super.setSize(super.getSize() + 3);
    }
}
