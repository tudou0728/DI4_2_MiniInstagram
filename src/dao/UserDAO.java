package dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import model.User;

public class UserDAO {
	public List<User> getAll() {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "from User user";
			Query query = session.createQuery(sql);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public User create(User user) {
		try {
			if (getById(user.getuId())==null) {
				Session session = HibernateUtil.currentSession();
				Transaction transaction = (Transaction) session.beginTransaction();
				session.save(user);
				transaction.commit();
			}
			return user;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	public boolean delete(User user) {
		Session session = HibernateUtil.currentSession();
		boolean result=false;
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			session.delete(user);
			transaction.commit();
			HibernateUtil.closeSession();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
		return result;
		
	}

	public User getById(int uId) {
		Session session = HibernateUtil.currentSession();
		try {
			return session.find(User.class, uId);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean isValide(String userName, String password) {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "from User user where user.userName=:userName and user.password=:password";
			Query query = session.createQuery(sql);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			List<User> users = query.getResultList();
			System.out.println(users.size());
			if (users.size() == 1) {
				return true;
			} else {
				return false;
			}
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public User update(User user, String userName, String password, boolean privacy) {
		Session session = HibernateUtil.currentSession();
//		System.out.println("lalalala: "+user.getPassword());
		try {	
			Transaction transaction = (Transaction) session.beginTransaction();
			if(userName!=null && !userName.equals(""))
			{
				user.setUserName(userName);
			}
			if(password!=null && !password.equals(""))
			{
				user.setPassword(password);
			}
			user.setPrivacy(privacy);
			session.update(user);
			transaction.commit();
			return user;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public User get(String userName, String password) {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "from User user where user.userName=:userName and user.password=:password";
			Query query = session.createQuery(sql);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			List<User> users = query.getResultList();
			System.out.println(users.size());
			if (users.size() >0) {
				return users.get(0);
			} else {
				return null;
			}
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
