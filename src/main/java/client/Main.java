package client;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import client.objects.Item;

import client.serializer.LocalDateDeserializer;
import client.serializer.LocalDateSerializer;
import client.user.Achievement;
import client.user.ClientUser;

import com.google.gson.JsonElement;
import javafx.application.Application;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;



/**
 * The Main class of the GoGreen application.
 * This application is made by:
 *
 * @author wouthaakman, ginotramontina, giulianoforghieri,
 *     janwillemeriks, jasminediaconu, mandychang, and svetoslavstanoev.
 */
public class Main {

    public static Gson gson;
    public static String sessionID;
    public static ClientUser clientUser;

    public static List<Item> items = new ArrayList<>();
    public static List<Achievement> achievements = new ArrayList<>();

    /**
     * Main function.
     *
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

    /**
     * This function rounds a double value to N decimal places.
     *
     * @param value  double type
     * @param places int type
     * @return a double rounded down to N decimal places
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
