package controllers;

import java.util.List;

import models.Post;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
//    	User u = new User("bob@gmail.com","secret","boby",false);
//        renderJSON(u);
    	Post frontPost = Post.find("order by postedAt desc").first();
    	List<Post> olderPosts = Post.find("order by postedAt desc").from(1).fetch(10);
    	render(frontPost,olderPosts);
    	//test
    	}    	
}