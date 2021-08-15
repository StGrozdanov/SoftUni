package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShopTest {

    //TODO: Test only the provided Shop class

    private Shop shop;
    private Goods good;
    private Goods good2;


    @Before
    public void setUp() {
        this.shop = new Shop();
        this.good = new Goods("Candy", "1");
        this.good2 = new Goods("Chocolate", "2");
    }

    @Test
    public void testIfGetShelvesReturnsCorrectValues(){
        Map<String, Goods> shelves = this.shop.getShelves();

        Map<String, Goods> expectedShelves = new LinkedHashMap<>();
        expectedShelves.put("Shelves1", null);
        expectedShelves.put("Shelves2", null);
        expectedShelves.put("Shelves3", null);
        expectedShelves.put("Shelves4", null);
        expectedShelves.put("Shelves5", null);
        expectedShelves.put("Shelves6", null);
        expectedShelves.put("Shelves7", null);
        expectedShelves.put("Shelves8", null);
        expectedShelves.put("Shelves9", null);
        expectedShelves.put("Shelves10", null);
        expectedShelves.put("Shelves11", null);
        expectedShelves.put("Shelves12", null);

        Assert.assertEquals(expectedShelves, shelves);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfGettingShelvesReturnsUnmodifiableCollection() {
        this.shop.getShelves().put("Invalid", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNonExistentShelfThrowsException() throws OperationNotSupportedException {
        this.shop.addGoods("Invalid", this.good);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAddingOnAlreadyExistentShelfThrowsException() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.good);
        this.shop.addGoods("Shelves1", this.good2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfAddingAlreadyExistentGoodThrowsException() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.good);
        this.shop.addGoods("Shelves2", this.good);
    }

    @Test
    public void testIfAddingGoodWorks() throws OperationNotSupportedException {
        Assert.assertEquals("Goods: 1 is placed successfully!",
                this.shop.addGoods("Shelves1", this.good));

    }

    @Test
    public void testIfAddingGoodWorksCorrectly() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.good);

        Assert.assertEquals(this.good.getGoodsCode(), this.shop.getShelves().get("Shelves1").getGoodsCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfRemovingNonExistentShelfThrowsException() {
        this.shop.removeGoods("Invalid", this.good);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfRemovingNonExistentGoodInShelfThrowsException() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.good);

        this.shop.removeGoods("Shelves1", this.good2);
    }

    @Test
    public void testIfRemovingGoodWorks() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.good);
        this.shop.addGoods("Shelves2", this.good2);

        this.shop.removeGoods("Shelves1", this.good);
        this.shop.removeGoods("Shelves2", this.good2);

        Assert.assertNull(this.shop.getShelves().get("Shelves1"));
        Assert.assertNull(this.shop.getShelves().get("Shelves2"));
    }
    @Test
    public void testIfRemovingGoodReturnsCorrectMsg() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", this.good);

        Assert.assertEquals("Goods: 1 is removed successfully!",
                this.shop.removeGoods("Shelves1", this.good));
    }
}