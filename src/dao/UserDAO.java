package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.User;

public class UserDAO 
{	
	public List<User> getAll(EntityManager entityManager)
	{
		String sql="from User user";
		Query query=entityManager.createQuery(sql);
		return query.getResultList();
	}
	
	public User create(EntityManager entityManager,User user)
	{
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return user;
	}
	
	public void delete(EntityManager entityManager,User user)
	{
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}
	
	public User getById(EntityManager entityManager,int uId)
	{
		String sql="from User user where user.uId=:id";
		Query query=entityManager.createQuery(sql);
		query.setParameter("id", uId);
		List<User> users=query.getResultList();
		if(users.size()==1)
		{
			return users.get(0);
		}
		else {
			return null;
		}
	}
	
	public User getByNameAndPassword(EntityManager entityManager,String userName,String password)
	{
		String sql="from User user where user.userName=:userName and user.password=:password";
		Query query=entityManager.createQuery(sql);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		List<User> users=query.getResultList();
		if(users.size()==1)
		{
			return users.get(0);
		}
		else {
			return null;
		}
	}
	
	public boolean isValide(EntityManager entityManager,String userName,String password)
	{
		String sql="from User user where user.userName=:userName and user.password=:password";
		Query query=entityManager.createQuery(sql);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		List<User> users=query.getResultList();
		if(users.size()==1)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public User update(EntityManager entityManager, User user,String userName, String password,boolean privacy)
	{
		entityManager.getTransaction().begin();
		user.setUserName(userName);
		user.setPassword(password);
		user.setPrivacy(privacy);
		entityManager.getTransaction().commit();
		return user;
	}
}
