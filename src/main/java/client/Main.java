package client;

import client.loginscreen.LoginApp;
import com.google.common.hash.Hashing;
import javafx.application.Application;

import java.nio.charset.StandardCharsets;

/**
 * The Main class of the GoGreen application.
 * This application is made by:
 *
 * @author wouthaakman, ginotramontina, giulianoforghieri,
 * janwillemeriks, jasminediaconu, mandychang, and svetoslavstanoev.
 */
public class Main {

    public static String sessionID;

    public static void main(String[] args) {
        Application.launch(LoginScreen.class, args);
        ServerRequests.endSession();
    }

    /**
     * This function will take a message of type String and hash it using MD5 digest.
     *
     * @param message the message to hash.
     * @return a hashed password as type String
     */
    public static String hashString(String message) {
        if (message == null) {
            return null;
        }
        String generatedMessage = Hashing.sha256().hashString(message, StandardCharsets.UTF_8).toString();
        return generatedMessage;
    }

}
