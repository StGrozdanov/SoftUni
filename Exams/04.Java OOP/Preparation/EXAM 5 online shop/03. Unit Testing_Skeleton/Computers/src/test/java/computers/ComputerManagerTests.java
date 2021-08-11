package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerManagerTests {
    private Computer computer;
    private ComputerManager computerManager;

    @Before
    public void setUp(){
        this.computer = new Computer("Stoyan", "Galaxy", 2500);
        this.computerManager = new ComputerManager();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIfAddingSameComputerTwiceReturnsException(){
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIfAddingNullComputerReturnsException(){
        this.computerManager.addComputer(null);
    }

    @Test
    public void testIfAddingComputerWorks(){
        Computer computer2 = new Computer("Andreana", "Atapova", 500);
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer2);

        List<Computer> addedComputers = new ArrayList<>();
        addedComputers.add(computer);
        addedComputers.add(computer2);

        Assert.assertEquals(2, this.computerManager.getCount());
        Assert.assertEquals(addedComputers, this.computerManager.getComputers());

    }
    @Test
    public void testIfRemovingComputerReturnsComputer(){
        this.computerManager.addComputer(computer);
        Computer target = this.computerManager.removeComputer("Stoyan", "Galaxy");
        Assert.assertEquals(target, this.computer);
    }

   @Test (expected = IllegalArgumentException.class)
    public void testIfCannotFindComputerShouldThrow(){
        this.computerManager.getComputer("Hi", "Kak si");
   }

   @Test (expected = IllegalArgumentException.class)
    public void testIfNullManufactorerShouldThrow(){
       this.computerManager.getComputer(null, "Kak si");
   }

   @Test
    public void testIfFindsComputerByManufactorer(){
        this.computerManager.addComputer(computer);
        List<Computer> computers = new ArrayList<>();
        computers.add(computer);
        Assert.assertEquals(computers, this.computerManager.getComputersByManufacturer("Stoyan"));
   }

}