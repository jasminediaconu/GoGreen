package server.login;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.ServerApp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class handles the REST controlling for any signup request.
 * It will check the username and password for correct syntax,
 * add them to the database and returns a String on completion.
 * @author wouthaakman
 *
 */
@RestController
public class SignUpController {

    private static PreparedStatement select;
    private static PreparedStatement insert;

    static {
        try {
            select = ServerApp.dbConnection.prepareStatement("SELECT userid FROM user_login "
                    + "WHERE username = ?;");
            insert = ServerApp.dbConnection.prepareStatement("INSERT INTO user_login "
                    + "(\"username\", \"email\", \"password\") "
                    + "VALUES (?, ?, ?) SELECT SCOPE_IDENTITY();");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function handles the request mapping for a user going to /signup url.
     * Requires two parameters, namely the username, email and hashed password.
     * It will make a query to insert a new user into the database.
     * @param newUser A String array containing the username, email and hashed password.
     * @return a boolean value telling the client whether the request was successful.
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@RequestBody String[] newUser) {
        try {
            String username = newUser[0];


            select.setString(1, username);

            ResultSet result = select.executeQuery();
            while (result.next()) {
                return "fail";
            }

            String email = newUser[1];
            String password = newUser[2];

            insert.setString(1, username);
            insert.setString(2, email);
            insert.setString(3, password);
            int resultID = insert.executeUpdate();

            String sessionID = ServerApp.createNewSessionID();
            ServerApp.addSessionID(sessionID, resultID);
            return sessionID;
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
