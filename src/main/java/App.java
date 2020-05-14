import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
        return processBuilder.environment().get("PORT") != null ? Integer.parseInt((String)processBuilder.environment().get("PORT")) : 4567;
    }

    public static void main(String[] args) {

        Spark.port(getHerokuAssignedPort());

        staticFileLocation("/public");

        Map<String,Object> model = new HashMap<String,Object>();

//        get("/sightings/form",(req,res)->{
//            return new ModelAndView(model, "sightingform.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/sightings/new",(req,res)->{
//            int animalId = Integer.parseInt(req.queryParams("animal"));
//            String rangerName = req.queryParams("rangerName");
//            String sightLocation = req.queryParams("sightLocation");
//            Sightings sighting = new Sightings(animalId,sightLocation,rangerName);
//            sighting.save();
//            return new ModelAndView(model, "sightingSuccess.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/sightings",(req,res)->{
//            model.put("sightings",Sightings.all());
//            return new ModelAndView(model, "sightings.hbs");
//        }, new HandlebarsTemplateEngine());

        get("/", (req,res) -> {
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//
//
//        get("/endangered",(req,res) ->{
//            model.put("endangeredAnimals",EndangeredAnimal.all());
//            return new ModelAndView(model, "endangeredanimal.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        get("/endangered/form",(req,res)->{
//            return new ModelAndView(model, "endangeredform.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/endangered/new",(req,res) ->{
//            String name = req.queryParams("animalName");
//            String health = req.queryParams("health");
//            String age = req.queryParams("age");
//            EndangeredAnimal animal= new EndangeredAnimal(name,health,age);
//            animal.save();
//            return new ModelAndView(model, "endangeredSuccess.hbs");
//        }, new HandlebarsTemplateEngine());

        get("/animal/form",(req,res) -> {
            return new ModelAndView(model, "animalform.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal/new",(req,res) -> {
            String name = req.queryParams("animalName");
            NormalAnimal animal= new NormalAnimal(name);
            animal.save();
            return new ModelAndView(model, "animalSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals",(req,res) ->{
            model.put("animals",NormalAnimal.all());
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal",(req,res) ->{
            model.put("animals",NormalAnimal.all());
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
