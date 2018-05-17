package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Follows {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fId;

	@ManyToOne
	private User follow;

	@ManyToOne
	private User follower;// "follower" follows "follow"

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public User getFollow() {
		return follow;
	}

	public void setFollow(User follow) {
		this.follow = follow;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public Follows(User follow, User follower) {
		this.follow = follow;
		this.follower = follower;
	}

	public Follows() {
	}

}
