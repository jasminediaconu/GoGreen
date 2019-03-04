package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Uses the Spring Boot framework.
 * This class opens the web application, to be able to receive queries.
 * @author wouthaakman
 */
@SpringBootApplication
public class ServerApp {

    private static Map<String, LocalTime> sessionTimes = new HashMap<String, LocalTime>();
    private static Map<String, String> sessionNames = new HashMap<String, String>();

    private static LocalTime now;

    /**
     * Starts the web application
     * @param args
     */
    public static void main(String[] args) {
        now = LocalTime.now();
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
     * @param sessionID String type
     * @param username String type
     */
    public static void addSessionID(String sessionID, String username) {
        if(!sessionNames.containsKey(username)  && !sessionTimes.containsKey(sessionID)) {
            sessionTimes.put(sessionID, LocalTime.now());
            sessionNames.put(sessionID, username);
        }
    }

    /**
     * Removes the sessionID from the list, by taking the username.
     * @param sessionID String type
     */
    public static void removeSessionID(String sessionID){
        sessionNames.remove(sessionID);
        sessionTimes.remove(sessionID);
    }

    /**
     * Updates the time of the given sessionID.
     * @param sessionID String type
     */
    public static void updateSessionTime(String sessionID){
        sessionTimes.replace(sessionID, LocalTime.now());
    }

    /**
     * Checks for all session IDs whether they are expired.
     */
    public static void checkExpired(){
        LocalTime temp = LocalTime.now();
        if(now.until(temp, ChronoUnit.MINUTES) > 5)
            now = temp;
        Iterator iter = sessionTimes.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            LocalTime time = (LocalTime) entry.getValue();
            if(time.getHour() < now.getHour() || (time.getMinute() + 5 < now.getMinute() && time.getHour() == now.getHour())){
                removeSessionID((String) entry.getKey());
            }
        }
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
