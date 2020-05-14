import Restaurantservices.PostRecipe;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class OdderTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void sighting_instanceOfOdder_true(){
        Odder testOdder = createOdder();
        assertEquals(true, testOdderz instanceof Odder);
    }
    @Test
    public void getAnimalId_getTheAnimalId_int(){
        Odder testOdderz = createOdder();
        assertEquals(1,testOdderz.getAnimalId());
    }
    @Test
    public void getLocation_getOdderzLocation_location()
    {   Odder testOdderz = createOdder();
        assertEquals("Zone A",testOdderz.getSightLocation());
    }
    @Test
    public void getRanger_getTheRangerName_ranger(){
        Odder testOdderz = createOdder();
        assertEquals("John",testOdderz.getRangerName());
    }
    @Test
    public void equal_checkWhetherOdderContentAreTheSame_true(){
        Odder testOdderz = createOdder();
        assertTrue(testOdderz.equals(testOdderz));
    }
    @Test
    public void save_returnsTrueIfTheSavedAreEqual(){
        Odder testOdderz = createOdder();
        testOdderz.save();
        assertTrue(Odder.all().get(0).equals(testOdderz));
    }
    @Test
    public void save_assignsIdToSpecificInstance(){
        Odder testOdderz = createOdderz();
        testOdderz.save();
        Odder savedOdder = Odder.all().get(0);
        assertEquals(savedOdder.getId(),testOdderz.getId());
    }
    @Test
    public void all_returnsAllInstancesOfOdder_true(){
        Odder testOdderz = createOdder();
        testOdderz.save();
        Odder testOdderz1 =new Odder(1,"Zone A","Lenny");
        testOdderz1.save();
        assertEquals(true,Odder.all().get(0).equals(testOdderz));
        assertEquals(true,Odder.all().get(1).equals(testOdderz1));

    }
    @Test
    public void find_returnSpecificOdderz_testOdderz1(){
        Odder testOdderz = createOdder();
        testOdderz.save();
        Odder testOdderz1 =new Odder(1,"Zone A","Lenny");
        testOdderz1.save();
        assertEquals(Odder.find(testOdderz1.getId()),testOdderz1);
    }
    @Test
    public void save_saveSAnimalIDIntoDb_true(){
        PostRecipe testPostRecipe = createPostRecipe();
        testPostRecipe.save();
        Odder testOdderz1 =new Odder(testPostRecipe.getId(),"Zone A","Lenny");
        testOdderz1.save();
        Odder savedOdderz = Odder.find(testOdderz1.getId());
        assertEquals(savedOdderz.getAnimalId(), testPostRecipe.getId());
    }

    public PostRecipe createPostRecipe()
    {
        return new PostRecipe("Lion", "Normal", "Healthy");
    }
    public Odder createOdder()
    {
        return new Odder(1, "Zone A", "John");
    }
}