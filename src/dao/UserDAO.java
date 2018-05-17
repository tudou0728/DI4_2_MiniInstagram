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

	public void delete(User user) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			session.delete(user);
			transaction.commit();
			HibernateUtil.closeSession();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
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
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			user.setUserName(userName);
			user.setPassword(password);
			user.setPrivacy(privacy);
			session.update(user);
			transaction.commit();
			return user;
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
