package client;

import client.loginScreen.LoginScreen;
import client.objects.Item;
import client.user.ClientUser;
import com.google.common.hash.Hashing;
import javafx.application.Application;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * The Main class of the GoGreen application.
 * This application is made by:
 *
 * @author wouthaakman, ginotramontina, giulianoforghieri,
 * janwillemeriks, jasminediaconu, mandychang, and svetoslavstanoev.
 */
public class Main {

    public static String sessionID;
    public static ClientUser mainUser;

    public static List<Item> items = new ArrayList<>();

    public static void main(String[] args) {
        ServerRequests.getItems();
        System.out.println(items.get(0).getCo2());
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
