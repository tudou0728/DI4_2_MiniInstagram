package service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dao.ResponseDAO;
import model.Publication;
import model.Response;
import model.User;

public class ResponseService {
	@Inject
	ResponseDAO responseDAO;

	public void createRes(Response response) {
		responseDAO.create(response);
	}

	public void deleteRes(Response response) {
		responseDAO.delete(response);
	}

	public void updateRes(Response response, String comment, Date date, User user, Publication publication) {
		responseDAO.update(response, comment, date, user, publication);
	}

	public List<Response> getAllRes() {
		return responseDAO.getAll();
	}
	
	public  List<Response> getAllResByPid(int pId){
		return responseDAO.getAllByPid(pId);
	}
	public Response getById(int rId) {
		return responseDAO.get(rId);
	}
}
