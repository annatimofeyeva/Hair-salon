import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.Random;
import org.sql2o.*;
import java.util.UUID;

public class App{

public static void main(String[] args){
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


  get("/contacts", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/contacts.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/stylists.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/stylists", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
        String image = request.queryParams("image");
        Stylist newStylist = new Stylist(name, image);
        newStylist.save();
        model.put("template", "templates/stylist-success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());



    post("/messages", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
        model.put("template", "templates/messages.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
 }
}
