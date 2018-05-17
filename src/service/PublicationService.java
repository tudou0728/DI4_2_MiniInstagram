package service;

import java.util.List;

import javax.inject.Inject;

import dao.PublicationDAO;
import model.Publication;
import model.Response;

public class PublicationService {
	@Inject
	PublicationDAO publicationDAO;

	public void createNewPub(final Publication publication) {
		publicationDAO.create(publication);
	}
	
	public void deletePub(final Publication publication) {
		publicationDAO.delete(publication);
	}
	
	public void updatePub(Publication publication, String imagePath, String comment, List<Response> responses) {
		publicationDAO.update(publication, imagePath, comment, responses);
	}
	
	public List<Publication> getAllPub() {
		return publicationDAO.getAll();
	}
}
