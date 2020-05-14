import Restaurants.PostMeal;
import Restaurants.PostRecipe;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null)
        {return Integer.parseInt((String)processBuilder.environment().get("PORT")) ;}
        return 4567;
    }

    public static void main(String[] args) {

        Spark.port(getHerokuAssignedPort());

        staticFileLocation("/public");



        get("/", (req,res) -> {
            Map<String,Object> model = new HashMap<String,Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/form",(req,res)->{
            Map<String,Object> model = new HashMap<String,Object>();
            List<PostRecipe>normalAnimals=PostRecipe.all();
            model.put("normalAnimals",normalAnimals);
            List<PostMeal>endangeredAnimals=PostMeal.all();
            model.put("endangeredAnimals",endangeredAnimals);
            return new ModelAndView(model, "sightingform.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/new",(req,res)->{
            Map<String,Object> model = new HashMap<String,Object>();
            int animalId = Integer.parseInt(req.queryParams("animalId"));
            String rangerName = req.queryParams("rangerName");
            String sightLocation = req.queryParams("sightLocation");
            Odder Odderz = new Odder(animalId,sightLocation,rangerName);
            sighting.save();
            return new ModelAndView(model, "sightingz.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings",(req,res)->{
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("sightings", Odder.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());


        get("/endangered",(req,res) ->{
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("endangeredAnimals",PostMeal.all());
            return new ModelAndView(model, "endangeredanimal.hbs");
        }, new HandlebarsTemplateEngine());


        get("/endangered/form",(req,res)->{
            Map<String,Object> model = new HashMap<String,Object>();
            return new ModelAndView(model, "endangeredform.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered/new",(req,res) ->{
            Map<String,Object> model = new HashMap<String,Object>();
            String name = req.queryParams("animalName");
            String health = req.queryParams("description");
            String age = req.queryParams("cook");
            PostMeal animal= new PostMeal(name,description,cook);
            animal.save();
            return new ModelAndView(model, "endangeredz.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal/form",(req,res) -> {
            Map<String,Object> model = new HashMap<String,Object>();
            return new ModelAndView(model, "animalform.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal/new",(req,res) -> {
            Map<String,Object> model = new HashMap<String,Object>();
            String name = req.queryParams("animalName");
            String health = req.queryParams("description");
            String age = req.queryParams("cook");
            PostRecipe animal= new PostRecipe(name,description,cook );
            animal.save();
            return new ModelAndView(model, "animalz.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals",(req,res) ->{
            Map<String,Object> model = new HashMap<String,Object>();
            List<PostRecipe>normalAnimals=PostRecipe.all();
            model.put("normalAnimals",normalAnimals);
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());
    }

    private static void staticFileLocation(String s) {
    }
}
