package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import model.Publication;
import model.Response;
import model.User;

public class ResponseDAO {

	public Response create(Response response) {

		try {
			if (get(response.getrId()) == null) {
				Session session = HibernateUtil.currentSession();
				Transaction transaction = (Transaction) session.beginTransaction();
				session.save(response);
				transaction.commit();
			}
			return response;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	public Response get(int rId) {
		Session session = HibernateUtil.currentSession();
		try {
			return session.find(Response.class, rId);
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	public List<Response> getAll() {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "from Response response";
			Query query = session.createQuery(sql);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Response> getAllByPid(int pId) {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "Select response from Response response where publicaiton_pId =?";
			Query query = session.createQuery(sql);
			query.setParameter(1, pId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void delete(Response response) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			session.delete(response);
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

	public Response update(Response response, String comment, Date date, User user, Publication publication) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			response.setComment(comment);
			response.setDate(date);
			response.setUser(user);
			response.setPublication(publication);
			session.update(publication);
			transaction.commit();
			return response;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}
}
