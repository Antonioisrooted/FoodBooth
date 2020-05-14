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

        get("/", (req,res) -> {
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

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
