package models;
import org.junit.*;
import java.util.*;

import javax.persistence.PersistenceException;

import play.test.*;
import models.*;

public class UserTest extends UnitTest {


	@SuppressWarnings("deprecation")
	@Before
	public void setup(){
		Fixtures.deleteAll();
	}
	
	@Test
	public void insertionMultipleDuMemeUtilisateur(){
		String email = "testConstraint@gmail.com";
		String fullname = "doe";
		String password = "secret";
		boolean isAdmin = true;
		new User(email, password, fullname, isAdmin).save();
		
		assertNotNull(User.find("byEmail",email));
		try{
			new User("testConstraint@gmail.com", password, fullname, isAdmin).save();
			fail("Une violation de contrainte aurait du se produire lors de l'insertion de " + email);			
		}catch(PersistenceException e){
			// OK
		}
		
	}
    
    @Test
    public void createAndRetrieveUser(){
    	// create user
    	String email = "jdoe@gmail.com";
		String password = "secret";
		String fullName = "doe";
		boolean isAdmin = true;
		new User(email, password, fullName, isAdmin).save();
    	// negative test
    	assertNull("test negatif avec un user inexistant", User.find("byEmail", "unknownUser@yahoo.fr").first());
    	// search user
    	User bob = User.find("byEmail", email).first();
    	
    	assertNotNull(email + " aurait du etre trouve", bob);
    	assertEquals(email, bob.email);
    	assertEquals(fullName, bob.fullName);
    	assertEquals(password, bob.password);
    	assertEquals(isAdmin, bob.isAdmin);
    }
    
    @Test
    public void connectUser(){
    	// create user
    	String email = "userConnectOK@gmail.com";
		String password = "secret";
		String fullName = "doe";
		boolean isAdmin = true;
		User userConnectOK = new User(email, password, fullName, isAdmin).save();
		// negative test
		assertNull(email + " aurait du etre connecte", User.connect("badMail", password));
		assertNull(email + " aurait du etre connecte", User.connect(userConnectOK.email, "badPassword"));
		assertNull(email + " aurait du etre connecte", User.connect("badMail", "badPassword"));
		
    	// check user
	 	assertNotNull(email + " aurait du etre trouve", userConnectOK);
	 	
	 	assertNotNull(email + " aurait du etre connecte", User.connect(userConnectOK.email, userConnectOK.password));
    }

}
