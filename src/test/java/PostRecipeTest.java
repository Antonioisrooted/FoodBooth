import Restaurants.PostRecipe;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
public class PostRecipeTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void PostRecipe_instanceOfPostRecipe_true(){
        PostRecipe testPostRecipe = createPostRecipe();
        assertEquals(true, testPostRecipe instanceof PostRecipe);
    }
    @Test
    public void getMethods_returnTheRightValues(){
        PostRecipe testPostRecipe = createPostRecipe();
        assertEquals("Pilau", testPostRecipe.getName());
        assertEquals("Normal", testPostRecipe.getType());
        assertEquals("Billy Ayiera", testPostRecipe.getCook());
        assertEquals("Spices, meat and rice. Come enjoy a meal.", testPostRecipe.getDescription());
    }
    @Test
    public void equals_returnsTrueIfNamesAreTheSame_true(){
        PostRecipe testPostRecipe = createPostRecipe();
        assertTrue(testPostRecipe.equals(testPostRecipe));
    }
    @Test
    public void save_savesPostRecipeInstanceToDB_PostRecipe(){
        PostRecipe testPostRecipe = createPostRecipe();
        testPostRecipe.save();
        assertTrue(PostRecipe.all().get(0).equals(testPostRecipe));
    }
    @Test
    public void all_returnsAllInstancesOfPostRecipe_true(){
        PostRecipe testPostRecipe = createPostRecipe();
        testPostRecipe.save();
        PostRecipe testSecondPostRecipe = createPostRecipe();
        testSecondPostRecipe.save();
        assertEquals(true, testPostRecipe.all().get(0).equals(testPostRecipe));
        assertEquals(true, testSecondPostRecipe.all().get(1).equals(testSecondPostRecipe));
    }
    @Test
    public void id_assignIdToSpecificInstance(){
        PostRecipe testPostRecipe = createPostRecipe();
        testPostRecipe.save();
        PostRecipe savedPostRecipe = PostRecipe.all().get(0);
        assertEquals(testPostRecipe.getId(), savedPostRecipe.getId());
    }

    public PostRecipe createPostRecipe() {return new PostRecipe("Pilau", "Billy Ayiera", "Spices, meat and rice. Come enjoy a meal.");}
}