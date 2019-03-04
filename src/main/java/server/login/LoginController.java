package server.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
     * @param username
     * @param password
     * @return a response as a String
     */
    @RequestMapping("/login")
    public String getResponse(@RequestParam String username, @RequestParam String password) {
        try{
            Connection con = DriverManager.getConnection(System.getenv("JDBC_DATABASE_URL"));
            Statement statement = con.createStatement();

            String query = "SELECT userid FROM user_login WHERE username = '" + username + "' AND password = '" + password + "';";
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                return result.getString("userid");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
