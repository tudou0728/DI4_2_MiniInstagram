package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("HelloWord")
public class HelloWord {
	
	@GET
	@Path("/Info")
	@Produces("text/plain")
	public String Info()
	{
		return "REST TP10 Hello word!";
	}
	
	@GET
	@Path("/{nom}")
	@Produces("text/plain")
	public String welcomeUser (@PathParam("nom")String userNom) {
		return "Bonjour "+userNom;
	}
}
