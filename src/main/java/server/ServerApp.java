package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Uses the Spring Boot framework.
 * This class opens the web application, to be able to receive queries.
 * @author wouthaakman
 */
@SpringBootApplication
public class ServerApp {

	/**
	 * Starts the web application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ServerApp.class);
		app.run(args);
	}
	
}
