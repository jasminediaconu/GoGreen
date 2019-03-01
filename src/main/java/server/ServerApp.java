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

    /**
     CREATE TABLE IF NOT EXISTS user_login (
        username text NOT NULL,
        password text NOT NULL,
        user_id SERIAL NOT NULL,
        PRIMARY KEY( username )
     );

     INSERT INTO user_login ("username", "password") VALUES ('Wout Haakman', '9347bfd1967a5839344998f964962a28');
     INSERT INTO user_login ("username", "password") VALUES ('Michael Stonebraker', '7760ff62297f10042c0c1f47cca1a587');
     */

}
