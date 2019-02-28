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
 * This class handles the REST controlling for any signup request.
 * It will check the username and password for correct syntax, add them to the database and returns a boolean on completion.
 * @author wouthaakman
 *
 */
@RestController
public class SignUpController {

    /**
     * This function handles the request mapping for a user going to /signup url.
     * Requires two parameters, namely the username and hashed password.
     * It will make a query to insert a new user into the database.
     * @param newUser A String array containing the username and hashed password.
     * @return a boolean value telling the client whether the request was successful.
     */
    @RequestMapping(value="/signup", method= RequestMethod.POST)
    public boolean getResponse(@RequestBody String[] newUser){
        String username = newUser[0];
        String password = newUser[1];

        try{
            Connection con = DriverManager.getConnection(System.getenv("JDBC_DATABASE_URL"));
            PreparedStatement statement = con.prepareStatement("SELECT user_id FROM user_login WHERE username = ? AND password = ?;");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();
            while(result.next()) {
                return false;
            }

            statement = con.prepareStatement("INSERT INTO user_login (\"username\", \"password\") VALUES (?, ?);");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
