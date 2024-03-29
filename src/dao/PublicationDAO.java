package dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import model.Publication;
import model.Response;
import model.User;

public class PublicationDAO {

	public Publication create(Publication publication) {
		
		try {
			if(get(publication.getpId())==null)
			{
				Session session = HibernateUtil.currentSession();
				Transaction transaction = (Transaction) session.beginTransaction();
				session.save(publication);
				transaction.commit();
			}
			return publication;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	public Publication get(int pId) {
		Session session = HibernateUtil.currentSession();
		try {
			return session.find(Publication.class, pId);
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	public List<Publication> getAll(User user) {
		Session session = HibernateUtil.currentSession();
		try {
			int user_uId = user.getuId()	;
			String sql = "Select publication from Publication publication where user_uId = ? ";
			Query query = session.createQuery(sql);
			query.setParameter(1, user_uId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void delete(Publication publication) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			session.delete(publication);
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

	public Publication update(Publication publication, String imagePath, String comment, List<Response> responses) {
		Session session=HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			publication.setImagePath(imagePath);
			publication.setComment(comment);
			publication.setResponses(responses);
			session.update(publication);
			transaction.commit();
			return publication;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}
}
