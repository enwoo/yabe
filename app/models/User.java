package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.db.jpa.Model;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class User extends Model {
	public String email;
	public String password;
	public String fullName;
	public boolean isAdmin;
	
	public User(String email,String password,String fullName,boolean isAdmin){
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.isAdmin = isAdmin;
	}
	
	public static User connect(String email,String password){
		return User.find("byEmailAndPassword", email,password).first();
	}
	
	@Override
	public String toString() {
		StringBuilder s= new StringBuilder();
		s.append(String.format("email = [%s] \n password = [%s] \n fullName = [%s] \n isAdmin = [%b] \n", email,password,fullName,isAdmin));
		return s.toString();
	}
	
}
