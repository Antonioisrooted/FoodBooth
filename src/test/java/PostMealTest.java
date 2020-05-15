import Restaurants.PostMeal;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostMealTest {
    @Rule
    public   DatabaseRule database = new DatabaseRule();


    @Test
    public void Postmeal_instanceOfPostmeal_true(){
        PostMeal testPostMeal = createPostmeal();
        assertEquals(true, testPostMeal instanceof PostMeal);
    }
    @Test
    public void getName_returnNameOfMeal_cat(){
        PostMeal testPostMeal = createPostmeal();
        assertEquals("Pilau", testPostMeal.getName());
    }
    @Test
    public void getDescription_returnDescriptionLevelOfTheMeal_okay(){
        PostMeal testPostMeal = createPostmeal();
        assertEquals("Spices, meat and rice. Come enjoy a meal.", testPostMeal.getDescription());
    }
    @Test
    public void getCook_returnCookLevelOfTheMeal_okay(){
        PostMeal testPostMeal = createPostmeal();
        assertEquals("Billy Ayiera", testPostMeal.getCook());
    }
    @Test
    public void equals_returnsTrueIfNamesAreTheSame_true(){
        PostMeal testPostMeal = createPostmeal();
        assertTrue(testPostMeal.equals(testPostMeal));
    }
    @Test
    public void save_savesPostmealInstanceToDB_Postmeal(){
        PostMeal testPostMeal = createPostmeal();
        testPostMeal.save();
        assertTrue(PostMeal.all().get(0).equals(testPostMeal));
    }
    @Test
    public void id_assignIdToSpecificInstance(){
        PostMeal testPostMeal = createPostmeal();
        testPostMeal.save();
        PostMeal savedPostMeal = (PostMeal) PostMeal.all().get(0);
        assertEquals(testPostMeal.getId(), savedPostMeal.getId());
    }
    @Test
    public void all_returnsAllInstancesOfPostmealTest_true(){
        PostMeal testPostMeal = createPostmeal();
        testPostMeal.save();
        PostMeal testSecondMeal = createPostmeal();
        testSecondMeal.save();

        assertEquals(true, testPostMeal.all().get(0).equals(testPostMeal));
        assertEquals(true, testSecondMeal.all().get(1).equals(testSecondMeal));
    }
    @Test
    public void all_returnsMealById() throws Exception{
        PostMeal testPostMeal = createPostmeal();
        testPostMeal.save();
        PostMeal testSecondMeal = createPostmeal();
        testSecondMeal.save();

        assertEquals(true, testSecondMeal.find((int) testSecondMeal.getId()).equals(testSecondMeal));
    }




    public PostMeal createPostmeal(){
        return new PostMeal("Pilau","Spices, meat and rice. Come enjoy a meal.", "Billy Ayiera");}
    }
