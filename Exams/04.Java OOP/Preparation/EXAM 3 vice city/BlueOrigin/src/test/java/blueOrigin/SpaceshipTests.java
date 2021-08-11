package blueOrigin;

import org.junit.Assert;
import org.junit.Test;

public class SpaceshipTests {

    @Test (expected = IllegalArgumentException.class)
    public void testIfSpaceshipIsFullShouldNotAdd(){
        Spaceship spaceship = new Spaceship("joro", 0);
        spaceship.add(new Astronaut("Joro", 40));
    }
    @Test (expected = IllegalArgumentException.class)
    public void testIfAstronautAlreadyExistShouldThrowExceptionWhenAdd(){
        Spaceship spaceship = new Spaceship("joro", 10);
        spaceship.add(new Astronaut("Joro", 40));
        spaceship.add(new Astronaut("Joro", 40));
    }

    @Test
    public void testIfAddAdds(){
        Spaceship spaceship = new Spaceship("joro", 1);
        spaceship.add(new Astronaut("Joro", 40));
        Assert.assertEquals(spaceship.getCapacity(), spaceship.getCount());
    }
    @Test
    public void testIfRemoveReturnsTrueIfSuccessfull(){
        Spaceship spaceship = new Spaceship("joro", 2);
        spaceship.add(new Astronaut("Joro", 40));
        Assert.assertTrue(spaceship.remove("Joro"));

    }
    @Test
    public void testIfRemoveReturnsFalseIfNotSuccessfull(){
        Spaceship spaceship = new Spaceship("joro", 2);
        spaceship.add(new Astronaut("Mitko", 40));
        Assert.assertFalse(spaceship.remove("Joro"));

    }
    @Test(expected = IllegalArgumentException.class)
    public void testIfCapacityBellowZeroThrowsException(){
            Spaceship spaceship = new Spaceship("joro", -1);
    }
    @Test(expected = NullPointerException.class)
    public void testIfNameIsNullThrowsException(){
        Spaceship spaceship = new Spaceship(null, 11);
    }
    @Test(expected = NullPointerException.class)
    public void testIfNameIsEmptyThrowsException(){
        Spaceship spaceship = new Spaceship("    ", 11);
    }

    @Test
    public void testIfGetNameReturnsTheDesiredName(){
        Spaceship spaceship = new Spaceship("joro", 2);
        spaceship.add(new Astronaut("Mitko", 40));
        Assert.assertEquals("joro", spaceship.getName());
    }
    @Test
    public void testIfGetOxygenPercentageReturnsTheDesiredValue(){
        Astronaut astronaut = new Astronaut("Mitko", 40);
        Assert.assertEquals(40, astronaut.getOxygenInPercentage(), 5);
    }

}
