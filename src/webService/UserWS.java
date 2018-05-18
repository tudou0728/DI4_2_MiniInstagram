package webService;

import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import model.User;
import service.AuthenticationService;
import service.UserService;

@Path("/users")
public class UserWS
{
	private UserService userService=new UserService();
	private AuthenticationService authenticationService=new AuthenticationService();
	
	@POST
	public User create(@QueryParam("userName")String userName,
			@QueryParam("password")String password,@QueryParam("privacy")boolean privacy) {
		return userService.create(userName, password, privacy);
	}
	
	@PUT
	@Path("userName")
	public User updateName(@HeaderParam("Authorization")String authCredentials,@QueryParam("newUserName")String newUserName) {
		User user=authenticationService.get(authCredentials);
		if(user!=null)
		{
			return userService.update(user, newUserName, user.getPassword(), user.isPrivacy());
		}
		else {
			return null;
		}
	}
	
	@PUT
	@Path("password")
	public User updatePassword(@HeaderParam("userName")String userName,@HeaderParam("password")String password,@QueryParam("newPassword")String newPassword) {
		User user=userService.get(userName, password);
		if(user!=null)
		{
			return userService.update(user, user.getUserName(), newPassword, user.isPrivacy());
		}
		else {
			return null;
		}
	}
	
	@PUT
	@Path("privacy")
	public User updatePrivacy(@HeaderParam("userName")String userName,@HeaderParam("password")String password,@QueryParam("newPrivacy")boolean newPrivacy) {
		User user=userService.get(userName, password);
		if(user!=null)
		{
			return userService.update(user, user.getUserName(), user.getPassword(), newPrivacy);
		}
		else {
			return null;
		}
	}
	
	@DELETE
	public String delete(@HeaderParam("Authorization")String authCredentials) {
		User user=authenticationService.get(authCredentials);
		if(user!=null)
		{
			if(userService.delete(user))
			{
				return "delete user successfully.";
			}
		}
		return "delete user error.";
	}
	
//	@GET
//	@Path("logIn")
//	public boolean logIn(@QueryParam("userName")String userName,
//			@QueryParam("password")String password) {
//		return userService.login(userName, password);
//	}
	
//	@GET
//	@Path("publications/{id}")
//	@Produces("text/plain")
//    public List<Publication> login(@PathParam("id") int id){
//        return 
//    }
	
	//懒加载有问题 还没解决
	
}
