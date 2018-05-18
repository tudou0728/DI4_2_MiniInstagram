package service;

import dao.UserDAO;
import model.User;

public class UserService 
{
	private UserDAO userDAO=new UserDAO();
	
	public boolean login(String userName, String password)
	{
		return userDAO.isValide(userName, password);
	}
	
	public User create(String userName, String password,boolean privacy) {
		User user=new User(userName, password, privacy);
		return userDAO.create(user);
	}
	
	public User update(User user,String userName, String password,boolean privacy) {
		return userDAO.update(user, userName, password, privacy);
	}
	
	public User get(String userName, String password) {
		return userDAO.get(userName, password);
	}
	
	public boolean delete(User user) {
		return userDAO.delete(user);
	}
	
//	public List<Publication> getPublications(int uId)
//	{
//		User user=userDAO.getById(uId);
//		if(!user.isPrivacy())
//		{
//			return user.getPublications();
//		}
//		else
//		{
//			
//		}
//	}
	
	
}
