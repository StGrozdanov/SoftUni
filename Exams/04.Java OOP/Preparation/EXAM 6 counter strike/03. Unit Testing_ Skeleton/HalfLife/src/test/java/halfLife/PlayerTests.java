package halfLife;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerTests {
    @Test (expected = NullPointerException.class)
    public void testIfUsernameIsNullShouldThrowException(){
        Player player = new Player(null, 100);
    }
    @Test (expected = NullPointerException.class)
    public void testIfUsernameIsEmptyShouldThrowException(){
        Player player = new Player("", 100);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testIfHealthIsBellowZeroShouldThrowException(){
        Player player = new Player("Vladko", -100);
    }
    @Test
    public void testIfGetUsernameWorks(){
        Player player = new Player("Vladko", 100);
        Assert.assertEquals("Vladko", player.getUsername());
    }
    @Test (expected = IllegalStateException.class)
    public void testIfDeadPlayerTakesDamageShouldThrowException(){
        Player player = new Player("Vladko", 0);
        player.takeDamage(100);
    }
    @Test
    public void testIfDeadPlayerHpIsBellowZeroShouldBeZero(){
        Player player = new Player("Vladko", 1);
        player.takeDamage(2);
        Assert.assertEquals(0, player.getHealth());
    }
    @Test
    public void testIfPlayerTakesDamageWorks(){
        Player player = new Player("Vladko", 100);
        player.takeDamage(80);
        Assert.assertEquals(20, player.getHealth());
    }
    @Test (expected = NullPointerException.class)
    public void testIfNullGunIsAddedShouldThrow(){
        Player player = new Player("Vladko", 100);
        Gun gun = player.getGun("Glock");
        player.addGun(gun);
    }
    @Test
    public void testIfAddGunIsAdded(){
        Player player = new Player("Vladko", 100);
        Gun gun = new Gun("Glock", 10);
        Gun gun1 = new Gun("Block", 11);
        Gun gun2 = new Gun("99mm", 12);
        List<Gun> guns = new ArrayList<>();

        guns.add(gun);
        guns.add(gun1);
        guns.add(gun2);

        player.addGun(gun);
        player.addGun(gun1);
        player.addGun(gun2);

        Assert.assertEquals(guns, player.getGuns());
    }
    @Test
    public void testIfRemovedGunIsRemoved(){
        Player player = new Player("Vladko", 100);
        Gun gun = new Gun("Glock", 10);
        player.addGun(gun);

        Assert.assertTrue(player.removeGun(gun));
    }
    @Test
    public void testIfRemovedGunIsMissing(){
        Player player = new Player("Vladko", 100);
        Gun gun = new Gun("Glock", 10);
        player.addGun(gun);
        player.removeGun(gun);

        Assert.assertNull(player.getGun("Glock"));
    }
    @Test (expected = UnsupportedOperationException.class)
    public void testIfGetGunsReturnsUnmodifiableCollection(){
        Player player = new Player("Vladko", 100);
        Gun gun = new Gun("Glock", 10);
        player.addGun(gun);
        player.getGuns().add(gun);
    }
    @Test
    public void testIfGetGunIsReturned(){
        Player player = new Player("Vladko", 100);
        Gun gun = new Gun("Glock", 10);
        player.addGun(gun);

        Assert.assertEquals(gun, player.getGun("Glock"));
    }

}
