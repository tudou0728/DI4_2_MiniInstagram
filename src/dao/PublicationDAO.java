package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Publication;
import model.Response;
import model.User;

public class PublicationDAO {

	public void create(EntityManager em, String imagePath, String comment, Date date, User user,
			List<Response> responses) {
		Publication pub = new Publication();
		em.getTransaction().begin();
		pub.setDate(date);
		pub.setComment(comment);
		pub.setDate(date);
		pub.setImagePath(imagePath);
		pub.setUser(user);
		pub.setResponses(responses);
		em.persist(user);
		for (int i = 0; i < responses.size(); i++) {
			em.persist(responses.get(i));
		}
		em.persist(pub);
		
		em.getTransaction().commit();
	}

	public Publication get(EntityManager em, int pId) {
		return em.find(Publication.class, pId);
	}

	public List<Publication> getAll(EntityManager em) {
		String sql = "from Publication";
		Query query = em.createQuery(sql);
		List<Publication> publications = query.getResultList();
		return publications;
	}

	public boolean delete(EntityManager em, int pId) {
		if (get(em, pId) != null) {
			em.getTransaction().begin();
			em.remove(get(em, pId));
			em.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}

	public void update(EntityManager em, int pId, String imagePath, String comment, Date date, User user,
			List<Response> responses) {
		Publication pub = get(em, pId);
		em.getTransaction().begin();
		pub.setComment(comment);
		pub.setDate(date);
		pub.setImagePath(imagePath);
		pub.setResponses(responses);
		em.persist(pub);
		em.getTransaction().commit();
	}
}
