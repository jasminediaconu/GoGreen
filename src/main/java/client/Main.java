package client;

import org.springframework.web.client.RestTemplate;

public class Main {

	private static int port = 8443;
	private static String url = "https://localhost";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		login("username", "password");
	}

	public static void login(String username, String password){
		RestTemplate rt = new RestTemplate();
		String[] result = rt.getForObject(url + ":" + port + "/login?username=" + username + "&password=" + password, String[].class);
		if(result != null)
			System.out.println(result[0] + ", " + result[1]);
	}

}
