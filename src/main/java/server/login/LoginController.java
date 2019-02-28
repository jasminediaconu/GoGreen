package server.login;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class handles the REST controlling for any login request.
 * It will check the username and password and returns a String to handle the webpage
 * @author wouthaakman
 *
 */
@RestController
public class LoginController {

    /**
     * This function handles the request mapping for a user going to the /login url.
     * Requires two parameters, namely the username and hashed password.
     * It will make a query that goes through the db to check if the user exists and returns the id if that is the case.
     * @return a response as a String
     */
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String getResponse(@RequestBody String[] user) {
        String username = user[0];
        String password = user[1];
        try{
            Connection con = DriverManager.getConnection(System.getenv("JDBC_DATABASE_URL"));
            PreparedStatement statement = con.prepareStatement("SELECT user_id FROM user_login WHERE username = ? AND password = ?;");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();
            while(result.next()) {
                return result.getString("user_id");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
