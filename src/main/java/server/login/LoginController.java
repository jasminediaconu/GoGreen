package server.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class handles the REST controlling for any login request.
 * It will check the username and password and returns a String to handle the webpage
 * @author wouthaakman
 *
 */
@RestController
public class LoginController {

	// TODO Handle HTTPS request, bundle with database and look up whether the user exists
	/**
	 * This function handles the request mapping for a user going to the /login url.
	 * Requires two parameters, namely the username and password.
	 * @param username
	 * @param password
	 * @return a response as a String
	 */
	@RequestMapping("/login")
	public String getResponse(@RequestParam String username, @RequestParam String password) {
		username = username.substring(1, username.length()-1);
		password = password.substring(1, password.length()-1);
		System.out.println("\n" + username + "   " + password);
		System.out.println(username.equals("username"));
		if(username.equals("username") && password.equals("password"))
			return "Welcome back, username";
		return "What?! I don't know you";
	}
	
}
