package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Uses the Spring Boot framework.
 * This class opens the web application, to be able to receive queries.
 * @author wouthaakman
 */
@SpringBootApplication
public class ServerApp {

    private static Map<String, String> sessionIDs = new HashMap<String, String>();

    /**
     * Starts the web application
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServerApp.class);
        app.run(args);
    }

    /**
     * This function will generate a unique session ID.
     * @return a UUID sessionID
     */
    public static String createNewSessionID(){
        return UUID.randomUUID().toString();
    }

    /**
     * Adds the sessionID to the list, with corresponding username.
     * @param username String type
     * @param sessionID String type
     */
    public static void addSessionID(String username, String sessionID){
        if(!sessionIDs.containsKey(username)){
            sessionIDs.put(username, sessionID);
        }
    }

    /**
     * Removes the sessionID from the list, by taking the username.
     * @param username String type
     */
    public static void removeSessionID(String username){
        sessionIDs.remove(username);
    }

    /**
     CREATE TABLE IF NOT EXISTS user_login (
        username text NOT NULL,
        email text NOT NULL,
        password text NOT NULL,
        user_id SERIAL NOT NULL,
        PRIMARY KEY (username)
     );

     CREATE TABLE IF NOT EXISTS user_profile (

     );

     INSERT INTO user_login ("username", "password") VALUES ('Wout Haakman', '9347bfd1967a5839344998f964962a28');
     INSERT INTO user_login ("username", "password") VALUES ('Michael Stonebraker', '7760ff62297f10042c0c1f47cca1a587');
     */

}
