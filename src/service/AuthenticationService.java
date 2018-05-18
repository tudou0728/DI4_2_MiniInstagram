package service;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import model.User;

public class AuthenticationService {
	private UserService UserService=new UserService();
	
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
		{
			return false;
		}
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String userName = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = UserService.login(userName, password);
		return authenticationStatus;
	}
	
	public User get(String authCredentials) {
		if (null == authCredentials)
		{
			return null;
		}
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String userName = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		return UserService.get(userName, password);
	}
}
