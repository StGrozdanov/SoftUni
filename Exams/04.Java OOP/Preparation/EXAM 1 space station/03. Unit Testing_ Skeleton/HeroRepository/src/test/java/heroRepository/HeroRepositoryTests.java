package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HeroRepositoryTests {
    private HeroRepository repository;

    @Before
    public void setUp(){
        this.repository = new HeroRepository();
    }

    @Test (expected = NullPointerException.class)
    public void testIfHeroToCreateIsNullShouldThrowException(){
        Hero hero = null;
        this.repository.create(hero);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testIfHeroToCreateAlreadyExistShouldThrowException(){
        Hero hero = new Hero("Yoda", 5);
        Hero hero2 = new Hero("Yoda", 5);
        this.repository.create(hero);
        this.repository.create(hero2);
    }
    @Test
    public void testIfCreateHeroCreatesSuccessfully(){
        Hero hero = new Hero("Yoda", 5);
        Assert.assertEquals("Successfully added hero Yoda with level 5", this.repository.create(hero));
    }
    @Test (expected = NullPointerException.class)
    public void testIfRemoveMethodTargetNameIsNullShouldThrowException(){
        this.repository.remove(null);
    }
    @Test (expected = NullPointerException.class)
    public void testIfRemoveMethodTargetNameIsEmptyShouldThrowException(){
        this.repository.remove("   ");
    }
    @Test
    public void testIfRemoveReturnsTrueIfRemovesSuccessfully(){
        Hero hero = new Hero("Yoda", 5);
        this.repository.create(hero);
        Assert.assertTrue(this.repository.remove("Yoda"));
    }
    @Test
    public void testIfReturnHeroWithHighestLevelWorks(){
        Hero hero = new Hero("Yoda", 5);
        Hero hero2 = new Hero("Shmatka", 25);
        this.repository.create(hero);
        this.repository.create(hero2);
        Assert.assertEquals(hero2, this.repository.getHeroWithHighestLevel());
    }
    @Test
    public void testIfReturnHeroByNameWorks(){
        Hero hero = new Hero("Yoda", 5);
        this.repository.create(hero);
        Assert.assertEquals(hero, this.repository.getHero("Yoda"));
    }
    @Test
    public void testIfReturnHeroCollectionWorks(){
        Hero hero = new Hero("Yoda", 5);
        Hero hero2 = new Hero("Shmatka", 25);
        this.repository.create(hero);
        this.repository.create(hero2);
        List<Hero> expected = new ArrayList<>();
        expected.add(hero);
        expected.add(hero2);

        Assert.assertArrayEquals(expected.toArray(), this.repository.getHeroes().toArray());
    }
    @Test
    public void testIfGetCountWorks(){
        Hero hero = new Hero("Yoda", 5);
        this.repository.create(hero);
        Assert.assertEquals(1, this.repository.getCount());
    }

}
