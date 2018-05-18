package webService;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Publication;
import model.Response;
import model.User;
import service.AuthenticationService;
import service.PublicationService;
import service.ResponseService;

@ApplicationPath("webSerivce")
@Path("/responseWS")
@Produces(MediaType.APPLICATION_JSON)
public class responseWS {
	@Inject
	ResponseService responseService;
	@Inject
	PublicationService publicationService;
	@Inject
	AuthenticationService authenticationService;

//	@GET
//	public List<Response> getAllRes() {
//		return responseService.getAllRes();
//	}

	@GET
	public List<Response> getAllResByPid(@HeaderParam("Authorization") String authCredentials,@QueryParam("pId") int pId) {
		User user = authenticationService.get(authCredentials);
		return responseService.getAllResByPid(pId,user);
	}

	@DELETE
	public void deleteRes(@HeaderParam("Authorization") String authCredentials,@QueryParam("rId") int rId) {
		User user = authenticationService.get(authCredentials);
		Response response = responseService.getById(rId);
		responseService.deleteRes(response,user);
	}

	@POST
	public Response createRes(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("comment") String comment, @QueryParam("date") Date date, @QueryParam("pId") int pId) {
		Publication publication = publicationService.getById(pId);
		User user = authenticationService.get(authCredentials);
		Response response = new Response(comment, date, user, publication);
		responseService.createRes(response,user);
		return response;
	}

//	@PUT
//	public void updateRes(@HeaderParam("Authorization") String authCredentials,@QueryParam("pId") int pId,@QueryParam("rId") int rId,@QueryParam("comment") String comment,@QueryParam("date") Date date) {
//		Publication publication = publicationService.getById(pId);
//		User user = authenticationService.get(authCredentials);
//		Response response = responseService.getById(rId);
//		responseService.updateRes(response, comment, date, user, publication);
//		
//	}

}
