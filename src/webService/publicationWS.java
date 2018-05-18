package webService;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import dao.ResponseDAO;
import model.*;
import service.AuthenticationService;
import service.PublicationService;
import service.ResponseService;
import service.UserService;

@ApplicationPath("webSerivce")
@Path("/publicationWS")
@Produces(MediaType.APPLICATION_JSON)
public class publicationWS {
	@Inject
	PublicationService publicationService;
	@Inject
	ResponseService responseService;
	@Inject
	AuthenticationService authenticationService;

	@GET
	public List<Publication> getAllPubs() {
		return publicationService.getAllPub();
	}

	@GET
	public Publication getPub(@QueryParam("pId") int pId) {
		Publication publication = publicationService.getById(pId);
		return publication;
	}

	@DELETE
	public void deletePub(@QueryParam("pId") int pId) {
		Publication publication = publicationService.getById(pId);
		publicationService.deletePub(publication);
	}

	@POST
	public Publication createPub(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("imagePath") String imagePath, @QueryParam("comment") String comment,
			@QueryParam("date") Date date) {
		User user = authenticationService.get(authCredentials);
		Publication publication = new Publication(imagePath, comment, date, user);
		publicationService.createNewPub(publication);
		return publication;
	}

	@PUT
	public void updatePub(@QueryParam("imagePath") String imagePath, @QueryParam("comment") String comment,
			@QueryParam("date") Date date, @QueryParam("pId") int pId) {
		Publication publication = publicationService.getById(pId);
		List<Response> responses = responseService.getAllResByPid(pId);
		publicationService.updatePub(publication, imagePath, comment, responses);
	}
}
