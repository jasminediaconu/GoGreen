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

	// TODO Bundle with database and look up whether the user exists
	/**
	 * This function handles the request mapping for a user going to the /login url.
	 * Requires two parameters, namely the username and password.
	 * @param username
	 * @param password
	 * @return a response as a String
	 */
	@RequestMapping("/login")
	public String getResponse(@RequestParam String username, @RequestParam String password) {
		if(username.equals("username") && password.equals("password"))
			return "Welcome back, username";
		return "What?! I don't know you";
	}
	
}
