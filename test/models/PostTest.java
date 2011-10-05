package models;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class PostTest extends UnitTest {

	@SuppressWarnings("deprecation")
	@Before
	public void setup(){
		Fixtures.deleteDatabase();
		Fixtures.loadModels("data.yml");
	}
	
	@Test
	public void doPost() {
		// create a user
		String email = "bob@gmail.com";
		// check user
		assertNotNull(User.find("byEmail", email).first());
		// negative test
		assertTrue(Post.count("author.email", email)==2);
		assertNotNull(Post.find("author.email", email));
		
		// jdoe post 2 comments
		List<Post> posts = Post.find("author.email", email).fetch();
		
		// check
		assertNotNull(posts);
	}

}
