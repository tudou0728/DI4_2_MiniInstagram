package webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import service.UserService;

@Path("/logIn")
public class AuthenticationWS {
	private UserService userService=new UserService();
	
	@GET
	public void login(@QueryParam("userName") String userName, @QueryParam(value = "password") String password)
	{
		if(userService.logIn(userName, password))
		{
			
		}
	}

}
