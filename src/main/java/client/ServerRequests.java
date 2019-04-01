package client;

import com.google.gson.reflect.TypeToken;

import client.objects.Activity;
import client.objects.Item;
import client.user.Achievement;
import client.user.ClientUser;
import client.user.User;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ServerRequests {

    public static String requestUrl = "https://group72.herokuapp.com/";

    /**
     * This function takes in a username and password, both of type String.
     * It will call the hashString function to hash the password for safe HTTP POST responses.
     * If the server responds with the correct result it will print this to the console,
     * if not valid it will print "Wrong username or password".
     *
     * @param username type.
     * @param password type.
     */
    public String login(String username, String password, boolean ishashed) {
        String hashedPassword = password;
        if (!ishashed) {
            hashedPassword = Main.hashString(password);
        }
        if (username == null || hashedPassword == null) {
            return null;
        }

        Pattern stdPattern = Pattern.compile("^([A-Za-z0-9_]{4,}$)");

        if (!stdPattern.matcher(username).matches()
                || !stdPattern.matcher(password).matches()) {
            return "syntax";
        }

        String response = sendRequestToServer("login",
                Main.gson.toJson(new String[]{username, hashedPassword}));

        if ("username".equals(response)) {
            System.out.println("[ERROR] User specified an invalid username");
            return response;
        } else if ("password".equals(response)) {
            System.out.println("[ERROR] User specified an invalid password");
            return response;
        } else if (response != null && !"fail".equals(response)) {
            System.out.println("[INFO] Login was successful");
            Main.sessionID = response;
            return "success";
        }
        return "fail";
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
    public String signUp(String username, String email, String password) {
        String hashedPassword = Main.hashString(password);
        if (username == null || email == null || hashedPassword == null) {
            return null;
        }

        Pattern stdPattern = Pattern.compile("^([A-Za-z0-9_]{4,}$)");
        Pattern emailPattern =
                Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");

        if (!stdPattern.matcher(username).matches() || !emailPattern.matcher(email).matches()
                || !stdPattern.matcher(password).matches()) {
            return "syntax";
        }

        String response = sendRequestToServer("signup",
                Main.gson.toJson(new String[]{username, email, hashedPassword}));
        return checkSignupResponse(response);
    }

    private String checkSignupResponse(String response) {
        if ("username".equals(response)) {
            System.out.println("[ERROR] This username has already been taken");
            return response;
        } else if ("email".equals(response)) {
            System.out.println("[ERROR] This email has already been taken");
            return response;
        } else if (response != null && !response.equals("fail")) {
            Main.sessionID = response;
            return "success";
        }
        return "fail";
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
    public String endSession() {
        System.out.println("[INFO] Ending session, "
                + "create a new session to continue or close the application.");
        String response = sendRequestToServer("end", Main.gson.toJson(Main.sessionID));
        if ("ok".equals(response)) {
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
    public void getItems() {
        System.out.println("[INFO] Retrieving items from database.");
        Type listType = new TypeToken<List<Item>>() {
        }.getType();
        String response = sendRequestToServer("getItems", null);
        if (response != null) {
            Main.items = Main.gson.fromJson(response, listType);
        }
    }

    /**
     * This function will add an activity to the database.
     *
     * @param activity Activity type.
     * @return a boolean whether adding went successfully
     */
    public boolean addActivity(Activity activity) {
        if (activity == null) {
            return false;
        }

        System.out.println("[INFO] Adding activity to database. " + activity.getActivityID());
        String json = Main.gson.toJson(activity);
        System.out.println("\n\n " + json + " \n\n");
        String response = sendRequestToServer("addActivity?sessionID=" + Main.sessionID, json);
        int activityID = -1;

        try {
            activityID = Integer.parseInt(response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (activityID == -1) {
            return false;
        } else {
            activity.setActivityID(activityID);
            return true;
        }
    }

    /**
     * This function removes an activity with gien acitivtiID from the database.
     *
     * @param activityID int type
     * @return a boolean whether removing went successfully
     */
    public boolean removeActivity(int activityID) {
        if (activityID < 0) {
            return false;
        }

        System.out.println("[INFO] Removing activity from database. " + activityID);
        String response = sendRequestToServer("removeActivity?sessionID="
                + Main.sessionID, Main.gson.toJson(activityID));
        if ("ok".equals(response)) {
            System.out.println("[INFO] Removing activity from database was successful");
            return true;
        } else {
            System.out.println("[ERROR] Removing activity from database went wrong, "
                    + "please check if it exists and correct.");
            return false;
        }
    }

    /**
     * This function retrieves all activities within a given period for a specific user from
     * the database.
     *
     * @param period String type.
     * @return a list of activities
     */
    public List<Activity> retrieveActivities(String period) {
        Type listType = new TypeToken<List<Activity>>() {
        }.getType();
        String response = sendRequestToServer("retrieveActivities?sessionID="
                + Main.sessionID, Main.gson.toJson(period));
        if (response == null) {
            return new ArrayList<>();
        }
        return Main.gson.fromJson(response, listType);
    }

    /**
     * This function retrieves the clients user profile from the database.
     *
     * @return a ClientUser class
     */
    public ClientUser getClientUserProfile() {
        String response = sendRequestToServer("getUserProfile?sessionID=" + Main.sessionID, null);
        return Main.gson.fromJson(response, ClientUser.class);
    }

    /**
     * This function will update the user profile on the server.
     *
     * @return a boolean on whether updating the users profile succeeded
     */
    public boolean updateClientUserProfile() {
        String response = sendRequestToServer("updateUserProfile?sessionID="
                + Main.sessionID, Main.gson.toJson(Main.clientUser));
        if ("success".equals(response)) {
            System.out.println("[INFO] Updating the client users profile went successfully");
            return true;
        } else {
            System.out.println("[ERROR] Updating the client users profile went wrong");
            return false;
        }
    }

    /**
     * This function retrieves all the users the clientuser is following from the database.
     *
     * @return a list of Users
     */
    public List<User> getFollowingProfile() {
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        String response = sendRequestToServer("getFollowingProfile?sessionID="
                + Main.sessionID, null);
        if (response == null) {
            return new ArrayList<>();
        }
        return Main.gson.fromJson(response, listType);
    }

    /**
     * This function gets the global best users.
     *
     * @return a list of Users
     */
    public List<User> getGlobalBestProfile() {
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        String response = sendRequestToServer("getGlobalBestProfile?sessionID="
                + Main.sessionID, null);
        if (response == null) {
            return new ArrayList<>();
        }
        return Main.gson.fromJson(response, listType);
    }

    /**
     * Get the UserID.
     *
     * @return response int type
     */
    public int getUserID() {
        String response = sendRequestToServer("userID?sessionID=" + Main.sessionID, null);
        if (response == null) {
            return -1;
        }
        return Integer.parseInt(response);
    }

    /**
     * Functiom to follow user using their username.
     *
     * @param username of the user the client wants to follow
     * @return true if not already following, false otherwise
     */
    public boolean followUser(String username) {
        String response = sendRequestToServer(
                "followUser?sessionID=" + Main.sessionID, Main.gson.toJson(username));
        if ("success".equals(response)) {
            System.out.println(
                    "[INFO] You successfully followed the following person: " + username);
            return true;
        } else {
            System.out.println(
                    "[ERROR] A failure occurred trying to follow the following person: "
                            + username);
            return false;
        }
    }

    /**
     * Function to unfollow a user using their username.
     *
     * @param username of the user the client wants to unfollow
     * @return true if client was following, false otherwise
     */
    public boolean unFollowUser(String username) {
        String response = sendRequestToServer(
                "unFollowUser?sessionID=" + Main.sessionID, Main.gson.toJson(username));
        if ("success".equals(response)) {
            System.out.println("[INFO] You successfully unfollowed the following person: "
                    + username);
            return true;
        } else {
            System.out.println(
                    "[ERROR] A failure occurred trying to unfollow the following person: "
                            + username);
            return false;
        }
    }

    /**
     * Gets achievements.
     */
    public void getAchievements() {
        System.out.println("[INFO] Retrieving achievements from database.");
        Type listType = new TypeToken<List<Achievement>>() {
        }.getType();
        String response = sendRequestToServer("getAchievements", null);
        if (response != null) {
            Main.achievements = Main.gson.fromJson(response, listType);
        }
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
    private String sendRequestToServer(String type, String json) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
