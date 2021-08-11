package bankSafe;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankVaultTest {

    private BankVault bankVault;
    private Item item;

    @Before
    public void setUp() {
        this.bankVault = new BankVault();
        this.item = new Item("Stoyan", "12");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAddItemAddsItemToNonExistentCellThrowsException() throws OperationNotSupportedException {
        this.bankVault.addItem("12", this.item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAddingItemToATakenCellThrowsException() throws OperationNotSupportedException {
        this.bankVault.addItem("C4", this.item);
        this.bankVault.addItem("C4", new Item("Andreana", "15"));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfAddingAlreadyAddedItemThrowsException() throws OperationNotSupportedException {
        this.bankVault.addItem("C4", this.item);
        this.bankVault.addItem("C3", this.item);
    }

    @Test
    public void testIfItemIsAddedSuccess() throws OperationNotSupportedException {
        Assert.assertEquals("Item:12 saved successfully!", this.bankVault.addItem("C4", this.item));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfRemovingItemNonExistentCellThrowsException() {
        this.bankVault.removeItem("12", this.item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfRemovingNonExistentItemThrowsException() {
        this.bankVault.removeItem("C4", this.item);
    }

    @Test
    public void testIfRemovedItemIsRemoved() throws OperationNotSupportedException {
        this.bankVault.addItem("C4", this.item);
        Assert.assertEquals("Remove item:12 successfully!", this.bankVault.removeItem("C4", this.item));
        Assert.assertNull(this.bankVault.getVaultCells().get("C4"));
    }

    @Test
    public void testIfGetItemOwnerWorks() {
        Assert.assertEquals("Stoyan", this.item.getOwner());
    }

    @Test
    public void testIfGetItemIdWorks() {
        Assert.assertEquals("12", this.item.getItemId());
    }
    @Test (expected = UnsupportedOperationException.class)
    public void testIfGetVaultCellsReturnsUnmodifiableCollection(){
        this.bankVault.getVaultCells().put("D3", null);
    }

}