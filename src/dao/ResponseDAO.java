package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Publication;
import model.Response;
import model.User;

public class ResponseDAO {

	public void create(EntityManager em, String comment, Date date, User user, Publication publication) {
		Response response = new Response();
		em.getTransaction().begin();
		response.setComment(comment);
		response.setDate(date);
		response.setUser(user);
		response.setPublication(publication);
		em.persist(user);
		em.persist(publication);
		em.persist(response);
		em.getTransaction().commit();
	}

	public Response get(EntityManager em, int rId) {
		return em.find(Response.class, rId);
	}

	public List<Response> getAll(EntityManager em) {
		String sql = "from Response";
		Query query = em.createQuery(sql);
		List<Response> responses = query.getResultList();
		return responses;
	}

	public boolean delete(EntityManager em, int rId) {
		if (get(em, rId) != null) {
			em.getTransaction().begin();
			em.remove(get(em, rId));
			em.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}

	public void update(EntityManager em, int rId, String comment, Date date, User user, Publication publication) {
		Response response = get(em, rId);
		em.getTransaction().begin();
		response.setComment(comment);
		response.setDate(date);
		response.setPublication(publication);
		response.setUser(user);
		em.persist(user);
		em.persist(publication);
		em.persist(response);
		em.getTransaction().commit();
	}
}
