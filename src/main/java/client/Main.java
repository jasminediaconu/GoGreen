package client;

import org.springframework.web.client.RestTemplate;

public class Main {

	private static String url = "https://group72.herokuapp.com/login";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		login("username", "password");
	}

	public static void login(String username, String password){
		RestTemplate rt = new RestTemplate();
		String[] result = rt.getForObject(url + "?username=" + username + "&password=" + password, String[].class);
		if(result != null)
			System.out.println(result[0] + ", " + result[1]);
	}

}
