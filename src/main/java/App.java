import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        staticFileLocation("/public");


        get("/heroes/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.clearAllHeroes();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete an individual task
        get("/heroes/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Hero deleteHero = Hero.findById(idOfTaskToDelete); //use it to find task
            deleteHero.deleteHero();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show all tasks
        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new task form
        get("/heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process new task form
        post("/heroes", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int age=Integer.parseInt(req.queryParams("age"));
            String specialPower = req.queryParams("specialPower");
            String weakness=req.queryParams("weakness");
            Hero newHero = new Hero(name,age,specialPower,weakness);
            res.redirect("/index");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual task
        get("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Hero foundHero = Hero.findById(idOfTaskToFind); //use it to find task
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "hero-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a task
        get("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfTaskToEdit);
            model.put("editHero", editHero);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process a form to update a task
        post("/heroes/:id", (req, res) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int newAge=Integer.parseInt(req.queryParams("age"));
            String newSpecialPower = req.queryParams("specialPower");
            String newWeakness=req.queryParams("weakness");
            int idOfTaskToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfTaskToEdit);
            editHero.update(newName,newAge,newSpecialPower,newWeakness);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/squads/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.clearAllSquads();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete an individual task
        get("/squads/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Squad deleteHero = Squad.findById(idOfSquadToDelete); //use it to find task
            deleteHero.deleteSquad();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show all tasks
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "index22.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new task form
        get("/squads/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process new task form
        post("/squads", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int size=Integer.parseInt(req.queryParams("size"));
            String cause = req.queryParams("cause");

            Squad newSquad = new Squad(name,cause,size);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual task
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Squad foundSquad = Squad.findById(idOfSquadToFind); //use it to find task
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a task
        get("/squads/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            model.put("editSquad", editSquad);
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process a form to update a task
        post("/squads/:id", (req, res) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int newSize=Integer.parseInt(req.queryParams("size"));
            String newCause=req.queryParams("cause");
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            editSquad.update(newName,newCause,newSize);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());






    }
}
