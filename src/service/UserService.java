package service;

import dao.UserDAO;

public class UserService {
	
	private UserDAO userDAO=new UserDAO();
	
	public boolean logIn(String userName, String password)
	{
		return userDAO.isValide(userName, password);
	}
}
