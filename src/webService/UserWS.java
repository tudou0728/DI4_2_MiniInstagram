package webService;

import javax.inject.Inject;
import javax.ws.rs.Path;

import service.UserService;

@Path("/service/users")
public class UserWS
{
	@Inject
	UserService userService;
	
}
