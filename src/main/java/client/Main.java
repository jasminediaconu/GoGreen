package client;


import client.objects.Item;
import client.serializer.LocalDateDeserializer;
import client.serializer.LocalDateSerializer;
import client.user.ClientUser;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Main class of the GoGreen application.
 * This application is made by:
 *
 * @author wouthaakman, ginotramontina, giulianoforghieri,
 *         janwillemeriks, jasminediaconu, mandychang, and svetoslavstanoev.
 */
public class Main {

    public static Gson gson;
    public static String sessionID;
    public static ClientUser clientUser;

    public static List<Item> items = new ArrayList<>();

    /**
     * Main function.
     * @param args type.
     */
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gson = builder.setPrettyPrinting().create();

        Application.launch(client.loginscreen.LoginApp.class, args);
        new ServerRequests().endSession();
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
        String generatedMessage = Hashing.sha256().hashString(message,
                StandardCharsets.UTF_8).toString();
        return generatedMessage;
    }

}
