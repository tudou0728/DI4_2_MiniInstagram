package webService;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import service.AuthenticationService;

@Provider
public class AuthenticationFilters implements ContainerRequestFilter {
 
	public static final String AUTHENTICATION_HEADER_KEY = "Authorization";
	
	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException {
		// TODO Auto-generated method stub
		if(!((containerRequestContext.getUriInfo().getAbsolutePath().getPath().contains("users"))&&
				containerRequestContext.getMethod().equals("POST")))
		{
			String authCredentials = containerRequestContext.getHeaderString(AUTHENTICATION_HEADER_KEY);
			AuthenticationService authenticationService=new AuthenticationService();
			if(!authenticationService.authenticate(authCredentials))
			{
				ResponseBuilder responseBuilder = null;
				responseBuilder = Response.serverError();
				Response response = responseBuilder.status(Status.UNAUTHORIZED).entity("User cannot access the resource, please logIn.").build();
				containerRequestContext.abortWith(response);
//				System.out.println("failed access.");
				return;
			}
			else {
//				System.out.println("pass 2.");
				return;
			}
		}
		else {
//			System.out.println("pass.");
			return;
		}
	}

}
