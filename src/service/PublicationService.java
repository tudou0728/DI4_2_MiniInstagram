package service;

import java.util.List;

import javax.inject.Inject;

import dao.FollowsDAO;
import dao.PublicationDAO;
import dao.UserDAO;
import model.Follows;
import model.Publication;
import model.Response;
import model.User;

public class PublicationService {
	@Inject
	PublicationDAO publicationDAO;
	@Inject
	FollowsDAO followsDAO;
	@Inject
	UserDAO userDAO;

	public void createNewPub(final Publication publication) {
		publicationDAO.create(publication);
	}
	
	public void deletePub(final Publication publication,User user) {
		if (publication.getUser().getuId()==user.getuId()) {
			publicationDAO.delete(publication);
		}		
	}
	
	public void updatePub(Publication publication,User user, String imagePath, String comment, List<Response> responses) {
		if (publication.getUser().getuId()==user.getuId()) {
			publicationDAO.update(publication, imagePath, comment, responses);
		}			
	}
	
	
	public List<Publication> getOtherAllPub(User user,User uFollew) {
		List<Follows> getFollow = followsDAO.getFollow( uFollew.getuId(),user.getuId());
		if (getFollow != null || getFollow.size() > 0) {
			return publicationDAO.getAll(user);
		}else 
			return null;		
	}
	
	public List<Publication> getOwnAllPub(User user){
		return publicationDAO.getAll(user);
	}
	
	public Publication getById(int pId) {
		return publicationDAO.get(pId);
	}
	
	public User getUserById(int uId) {
		return userDAO.getById(uId);
	}
	
}
