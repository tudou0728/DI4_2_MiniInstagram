package controller;

import dao.UserDAO;
import model.User;

public class Main {

	public static void main(String[] args) {
//		ConnBD connBD=new ConnBD();
//		connBD.connect();

		UserDAO userDAO=new UserDAO();
		User user=userDAO.getById(5);
//		User user2=userDAO.getById(6);
		//FollowsDAO followsDAO=new FollowsDAO();
//		User user=new User("user10", "password10", true);
		userDAO.create(user);
		System.out.println("lalala2: "+userDAO.getAll().size());
		
//		User user=userDAO.getById(7);
//		userDAO.delete(user);
//		System.out.println("lalala2: "+userDAO.getAll().size());
//		User user=new User("user8", "password8", true);
	//	userDAO.create(user);
//		System.out.println("lalala2: "+user.getUserName());
//		UserDAO userDAO=new UserDAO();
//		User user1=userDAO.getById(connBD.getEntityManager(), 1);
//		User user2=userDAO.getById(connBD.getEntityManager(), 2);
//		User user3=userDAO.getById(connBD.getEntityManager(), 3);
//		User user6=new User("user6", "password6", false);
//		
//		List<Follows> follows=new ArrayList<>();
//		Follows follows2=new Follows();
//		follows2.setFollow(user6);
//		follows2.setFollower(user1);
//		follows.add(follows2);
		

//		FollowsDAO followsDAO=new FollowsDAO();
//		Follows follows=followsDAO.getFollow(connBD.getEntityManager(), 2, 3).get(0);
//		followsDAO.delete(connBD.getEntityManager(), follows);
//		
//		UserDAO userDAO=new UserDAO();
//		User user2=userDAO.getById(connBD.getEntityManager(), 2);
//		System.out.println("lalala: "+user2.getFollowers().size());
//		System.out.println("lalala2: "+user2.getFollows().size());
		
//		connBD.close();
	}

}
