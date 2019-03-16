package client;

import client.objects.Activity;
import client.objects.Item;
import client.user.ClientUser;
import client.user.User;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ServerRequests {

    private static String requestUrl = "https://group72.herokuapp.com/";

    /**
     * This function takes in a username and password, both of type String.
     * It will call the hashString function to hash the password for safe HTTP POST responses.
     * If the server responds with the correct result it will print this to the console,
     * if not valid it will print "Wrong username or password".
     *
     * @param username type.
     * @param password type.
     */
    public static String login(String username, String password) {
        String hashedPassword = Main.hashString(password);
        if (username == null || hashedPassword == null) {
            return null;
        }
        String response = sendRequestToServer("login",
                Main.gson.toJson(new String[]{username, hashedPassword}));
        if (response != null && response != "fail") {
            String[] resArr = response.split("::");
            System.out.println("[INFO] Login returned the following user_id: " + resArr[0]);
            Main.sessionID = resArr[0];
            return "success: " + resArr[0];
        } else {
            System.out.println("[ERROR] Wrong username or password");
            return "fail";
        }
    }

    /**
     * This function takes in a username and password, both of type String.
     * It will call the hashString function to hash the password for safe HTTP responses.
     * If the username and/or password don't have the proper syntax,
     * defined by the pattern used in the 2nd line of the function, it will return.
     * If the server responds with the correct result it will print this to the console,
     * if not valid it will print an error notifying the sign up was not successful.
     *
     * @param username type.
     * @param password type.
     */
    public static String signUp(String username, String email, String password) {
        String hashedPassword = Main.hashString(password);
        Pattern stdPattern = Pattern.compile("[A-Za-z0-9_]+");
        Pattern emailPattern =
                Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
        if (username == null || email == null || hashedPassword == null) {
            return null;
        }

        if (!stdPattern.matcher(username).matches() || !emailPattern.matcher(email).matches()
                || !stdPattern.matcher(password).matches()) {
            return "syntax";
        }

        String response = sendRequestToServer("signup",
                Main.gson.toJson(new String[]{username, email, hashedPassword}));
        if (response != null && !response.equals("fail")) {
            Main.sessionID = response;
            System.out.println("[INFO] The sign up was successful: " + Main.sessionID);
            return "ok";
        } else {
            System.out.println("[ERROR] The sign up was not successful.");
            return "fail";
        }
    }

    /**
     * This function will end the current session by taking the sessionID
     * and requesting a session end by the server.
     * The server will respond with ok or fail depending on the response,
     * which means that the server has successfully
     * removed the sessionID pointing to this application instance.
     *
     * @return a String containing the response
     */
    public static String endSession() {
        System.out.println("[INFO] Ending session, "
                + "create a new session to continue or close the application.");
        String response = sendRequestToServer("end", Main.gson.toJson(Main.sessionID));
        if (response.equals("ok")) {
            System.out.println("[INFO] The session has been ended successfully.");
        } else {
            System.out.println("[WARNING] Something went wrong ending the session.");
        }
        return response;
    }

    /**
     * This function will get all the items from the database on the server
     * by sending a getItems request.
     * The returned Items are converted from JSON to the TypeToken listType
     * containing a List of Item's.
     * The list will be assigned to the Main items list.
     */
    public static void getItems() {
        System.out.println("[INFO] Retrieving items from database.");
        Type listType = new TypeToken<List<Item>>() {
        }.getType();
        String response = sendRequestToServer("getItems", null);
        if (response != null)
            Main.items = Main.gson.fromJson(response, listType);
    }

    /**
     * This function will add an activity to the database
     * @param activity
     * @return a boolean whether adding went successfully
     */
    public static boolean addActivity(Activity activity) {
        if (activity == null) {
            return false;
        }

        System.out.println("[INFO] Adding activity to database. " + activity.getActivityID());
        String json = Main.gson.toJson(activity);
        System.out.println("\n\n " + json + " \n\n");
        String response = sendRequestToServer("addActivity?s=" + Main.sessionID, json);
        int activityID = Integer.parseInt(response);
        if (activityID == -1) {
            return false;
        } else {
            activity.setActivityID(activityID);
            return true;
        }
    }

    /**
     * This function removes an activity with gien acitivtiID from the database.
     * @param activityID
     * @return a boolean whether removing went successfully
     */
    public static boolean removeActivity(int activityID) {
        if (activityID < 0) {
            return false;
        }

        System.out.println("[INFO] Removing activity from database. " + activityID);
        String response = sendRequestToServer("removeActivity?s="
                + Main.sessionID, Main.gson.toJson(activityID));
        if (response.equals("ok")) {
            System.out.println("[INFO] Removing activity from database was successful");
            return true;
        } else {
            System.out.println("[ERROR] Removing activity from database went wrong, "
                    + "please check if it exists and correct.");
            return false;
        }
    }

    /**
     * This function retrieves all activities within a given period for a specific user from the database
     * @param period
     * @return a list of activities
     */
    public static List<Activity> retrieveActivities(String period) {
        Type listType = new TypeToken<List<Activity>>() {
        }.getType();
        String response = sendRequestToServer("retrieveActivities?s=" + Main.sessionID, Main.gson.toJson(period));
        if (response == null)
            return new ArrayList<>();
        return Main.gson.fromJson(response, listType);
    }

    /**
     * This funtion retrieves the clients user profile from the database
     * @return a ClientUser class
     */
    public static ClientUser getClientUserProfile() {
        String response = sendRequestToServer("getUserProfile?s=" + Main.sessionID, null);
        return Main.gson.fromJson(response, ClientUser.class);
    }

    /**
     * This function retrieves all the users the clientuser is following from the database
     * @return a list of Users
     */
    public static List<User> getFollowingProfile() {
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        String response = sendRequestToServer("getFollowingProfile?s=" + Main.sessionID, null);
        if (response == null)
            return new ArrayList<User>();
        return Main.gson.fromJson(response, listType);
    }

    /**
     * This funtion gets the global best users.
     * @return a list of Users
     */
    public static List<User> getGlobalBestProfile() {
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        String response = sendRequestToServer("getGlobalBestProfile?s=" + Main.sessionID, null);
        if (response == null)
            return new ArrayList<User>();
        return Main.gson.fromJson(response, listType);
    }

    public static int getUserID() {
        String response = sendRequestToServer("userID?s=" + Main.sessionID, null);
        if (response == null)
            return -1;
        return Integer.parseInt(response);
    }

    /**
     * This function will prepare the HTTP client to send a request to the server.
     * With type it will know what URL to go to. The response will be a String,
     * which can be formatted afterwards via JSON.
     *
     * @param type type.
     * @param json type.
     * @return
     */
    private static String sendRequestToServer(String type, String json) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(requestUrl + type);

            if (json != null) {
                httpPost.setEntity(new StringEntity(json));
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
            }

            CloseableHttpResponse response = client.execute(httpPost);
            String msg = new BasicResponseHandler().handleResponse(response);
            client.close();
            if (msg.length() < 1) {
                msg = null;
            }
            System.out.println("[INFO] The server responded with: " + msg);
            return msg;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
