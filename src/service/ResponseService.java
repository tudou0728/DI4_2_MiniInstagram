package service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dao.FollowsDAO;
import dao.PublicationDAO;
import dao.ResponseDAO;
import model.Follows;
import model.Publication;
import model.Response;
import model.User;

public class ResponseService {
	@Inject
	ResponseDAO responseDAO;
	@Inject
	PublicationDAO publicationDAO;
	@Inject
	FollowsDAO followsDAO;

	public void createRes(Response response, User user) {
		User uCreater = response.getPublication().getUser();
		if (uCreater.getuId() == user.getuId()) {
			responseDAO.create(response);
		} else if (!uCreater.isPrivacy()) {
			responseDAO.create(response);
		} else {
			List<Follows> getFollow = followsDAO.getFollow(user.getuId(), uCreater.getuId());
			if (getFollow != null || getFollow.size() > 0) {
				responseDAO.create(response);
			}
		}
	}

	public void deleteRes(Response response, User user) {
		if (response.getUser().getuId() == user.getuId()) {
			responseDAO.delete(response);
		}
	}

	public void updateRes(Response response, String comment, Date date, User user, Publication publication) {
		responseDAO.update(response, comment, date, user, publication);
	}

	public List<Response> getAllRes() {
		return responseDAO.getAll();
	}

	public List<Response> getAllResByPid(int pId, User user) {
		Publication publication = publicationDAO.get(pId);
		int uFollowId = publication.getUser().getuId();
		if (uFollowId == user.getuId()) {
			return responseDAO.getAllByPid(pId);
		} else if (!user.isPrivacy()) {
			return responseDAO.getAllByPid(pId);
		} else {
			List<Follows> getFollow = followsDAO.getFollow(uFollowId, user.getuId());
			if (getFollow != null || getFollow.size() > 0) {
				return responseDAO.getAllByPid(pId);
			} else {
				return null;
			}
		}
	}

	public Response getById(int rId) {
		return responseDAO.get(rId);
	}
}
