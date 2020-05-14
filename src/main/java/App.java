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
            return new ModelAndView(model, "layout.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
