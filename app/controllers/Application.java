package controllers;

import models.User;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
    	User u = new User("bob@gmail.com","secret","boby",false);
        renderJSON(u);
    }

}