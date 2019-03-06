package server.login;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.ServerApp;

import java.sql.*;

/**
 * This class handles the REST controlling for any login request.
 * It will check the username and password and returns a String to handle the webpage
 * @author wouthaakman
 *
 */
@RestController
public class LoginController {

    private static PreparedStatement select;

    static {
        try{
            select = ServerApp.dbConnection.prepareStatement("SELECT userid FROM user_login WHERE username = ? AND password = ?;");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This function handles the request mapping for a user going to the /login url.
     * Requires two parameters, namely the username and hashed password.
     * It will make a query that goes through the db to check if the user exists and returns the id if that is the case.
     * @param user String[] type
     * @return a response as a String
     */
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String login(@RequestBody String[] user) {
        try{
            String username = user[0];
            String password = user[1];

            select.setString(1, username);
            select.setString(2, password);

            ResultSet result = select.executeQuery();
            while(result.next()) {
                String sessionID = ServerApp.createNewSessionID();
                int userID = Integer.parseInt(result.getString("userid"));
                ServerApp.addSessionID(sessionID, userID);
                return sessionID + "::" + userID;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
