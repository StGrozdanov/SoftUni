package aquarium;

import org.junit.Assert;
import org.junit.Test;

public class AquariumTests {
    @Test(expected = IllegalArgumentException.class)
    public void testIfCapacityIsBellowZeroShouldThrowException() {
        Aquarium aquarium = new Aquarium("Fish Land", -84);
    }

    @Test(expected = NullPointerException.class)
    public void testIfNameIsNullShouldThrowException() {
        Aquarium aquarium = new Aquarium(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testIfNameIsEmptyShouldThrowException() {
        Aquarium aquarium = new Aquarium("   ", 10);
    }

    @Test
    public void testIfGetNameReturnsDesiredName() {
        Aquarium aquarium = new Aquarium("Fish Land", 20);
        Assert.assertEquals("Fish Land", aquarium.getName());
    }

    @Test
    public void testIfGetCapacityReturnsDesiredCapacity() {
        Aquarium aquarium = new Aquarium("Fish Land", 20);
        Assert.assertEquals(20, aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAddingToFullAquariumShouldThrowException() {
        Aquarium aquarium = new Aquarium("Fish Land", 1);
        aquarium.add(new Fish("Andreana"));
        aquarium.add(new Fish("Stoyan"));
    }

    @Test
    public void testIfGetCountReturnsDesiredCount() {
        Aquarium aquarium = new Aquarium("Fish Land", 20);
        aquarium.add(new Fish("Andreana"));
        aquarium.add(new Fish("Stoyan"));
        Assert.assertEquals(2, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfRemovingNonExistentFishShouldThrowException() {
        Aquarium aquarium = new Aquarium("Fish Land", 1);
        aquarium.remove("Andreana");
    }

    @Test
    public void testIfRemoveMethodRemoves() {
        Aquarium aquarium = new Aquarium("Fish Land", 20);
        aquarium.add(new Fish("Andreana"));
        aquarium.add(new Fish("Stoyan"));
        aquarium.remove("Stoyan");
        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfSellingNonExistentFishShouldThrowException() {
        Aquarium aquarium = new Aquarium("Fish Land", 1);
        aquarium.sellFish("Andreana");
    }

    @Test
    public void testIfSellMethodSellsTheDesiredFish() {
        Aquarium aquarium = new Aquarium("Fish Land", 20);
        Fish fish = new Fish("Stoyan");
        aquarium.add(new Fish("Andreana"));
        aquarium.add(fish);
        Assert.assertEquals(fish, aquarium.sellFish("Stoyan"));
    }

    @Test
    public void testIfSellMethodSetsTheSelledFishAsUnavailable() {
        Aquarium aquarium = new Aquarium("Fish Land", 20);
        Fish fish = new Fish("Stoyan");
        aquarium.add(new Fish("Andreana"));
        aquarium.add(fish);
        aquarium.sellFish("Stoyan");
        Assert.assertFalse(fish.isAvailable());
    }

    @Test
    public void testIfReportReportsCorrectly() {
        Aquarium aquarium = new Aquarium("Fish Land", 20);
        aquarium.add(new Fish("Andreana"));
        aquarium.add(new Fish("Stoyan"));
        Assert.assertEquals("Fish available at Fish Land: Andreana, Stoyan", aquarium.report());
    }


}

