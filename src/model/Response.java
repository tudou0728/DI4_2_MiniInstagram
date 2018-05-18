package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Response 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rId;
	
	private String comment;
	private Date date;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Publication publication;

	public Response(String comment, Date date, User user, Publication publication) {
		super();
		this.comment = comment;
		this.date = date;
		this.user = user;
		this.publication = publication;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
	
}
