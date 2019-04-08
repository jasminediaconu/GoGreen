package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import server.helper.LocalDateDeserializer;
import server.helper.LocalDateSerializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Uses the Spring Boot framework.
 * This class opens the web application, to be able to receive queries.
 *
 * @author wouthaakman
 */
@SpringBootApplication
public class ServerApp {

    public static Connection dbConnection;
    public static Gson gson;
    private static Map<String, Integer> sessions = new HashMap<String, Integer>();

    /**
     * Starts the web application.
     *
     * @param args type.
     */
    public static void main(String[] args) throws Exception {
        dbConnection = DriverManager.getConnection(System.getenv("JDBC_DATABASE_URL"));

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gson = builder.setPrettyPrinting().create();

        sessions.put("test", 1);

        SpringApplication app = new SpringApplication(ServerApp.class);
        app.run(args);
    }

    /**
     * This function will generate a unique session ID.
     *
     * @return a UUID sessionID
     */
    public static String createNewSessionID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Adds the sessionID to the list, with corresponding username.
     *
     * @param sessionID String type
     */
    public static void addSessionID(String sessionID, int userID) {
        if (!sessions.containsKey(sessionID)) {
            sessions.put(sessionID, userID);
        }
    }

    /**
     * Removes the sessionID from the list, by taking the username.
     *
     * @param sessionID String type.
     */
    public static void removeSessionID(String sessionID) {
        sessions.remove(sessionID);
    }

    /**
     * This function will get the userID from the sessions list.
     *
     * @param sessionID String type.
     * @return an integer corresponding to the userID in the database.
     */
    public static int getUserIDfromSession(String sessionID) {
        if (sessions.get(sessionID) == null) {
            return -1;
        }
        return sessions.get(sessionID);
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

     INSERT INTO user_login ("username", "password")
     VALUES ('Wout Haakman', '9347bfd1967a5839344998f964962a28');
     INSERT INTO user_login ("username", "password")
     VALUES ('Michael Stonebraker', '7760ff62297f10042c0c1f47cca1a587');
     */

}
