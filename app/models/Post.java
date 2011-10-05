package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class Post extends Model {
	public String title;
	public Date postedAt;
	
	@Lob
	public String content;
	
	@ManyToOne
	public User author;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	public List<Comment> comments;
	
	public Post(User author,String title,String content){
		this.author = author;
		this.title = title;
		this.content = content;
		this.postedAt = new Date();
		this.comments = new ArrayList<Comment>();
	}
	
	public Post addComment(final String author,final String content){
		Comment newComment = new Comment(author, content, this);
		this.comments.add(newComment);
		return this;
	}
}
