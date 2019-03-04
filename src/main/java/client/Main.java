package client;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * The Main class of the GoGreen application.
 * This application is made by:
 * @author wouthaakman, ginotramontina, giulianoforghieri,
 * janwillemeriks, jasminediaconu, mandychang, and svetoslavstanoev
 */
public class Main {

    public static String sessionID;
    private static String requestUrl = "https://group72.herokuapp.com/";

    public static void main(String[] args) {
        System.out.println(login("WoutHaakman", "test"));
        //System.out.println(signUp("WoutHaakman", "wouthaakman@hotmail.com", "wouthaakman"));

        endSession();
    }

    /**
     * This function takes in a username and password, both of type String.
     * It will call the hashString function to hash the password for safe HTTP POST responses.
     * If the server responds with the correct result it will print this to the console, if not valid it will print "Wrong username or password"
     * @param username
     * @param password
     */
    public static String login(String username, String password){
        String hashedPassword = hashString(password);
        if(username == null || hashedPassword == null)
            return null;

        String response = sendRequestToServer("login", new Gson().toJson(new String[]{username, hashedPassword}));
        if(response != null) {
            String[] resArr = response.split("::");
            System.out.println("[INFO] Login returned the following user_id: " + resArr[1]);
            sessionID = resArr[0];
            return "success: " + resArr[0];
        }else {
            System.out.println("[ERROR] Wrong username or password");
            return "fail";
        }
    }

    /**
     * This function takes in a username and password, both of type String.
     * It will call the hashString function to hash the password for safe HTTP responses.
     * If the username and/or password don't have the proper syntax, defined by the pattern used in the 2nd line of the function, it will return.
     * If the server responds with the correct result it will print this to the console, if not valid it will print an error notifying the sign up was not successful.
     * @param username
     * @param password
     */
    public static String signUp(String username, String email, String password){
        String hashedPassword = hashString(password);
        Pattern stdPattern = Pattern.compile("[A-Za-z0-9_]+");
        Pattern emailPattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
        if(username == null || email == null || hashedPassword == null)
            return null;

        if(!stdPattern.matcher(username).matches() || !emailPattern.matcher(email).matches() || !stdPattern.matcher(password).matches())
            return "syntax";

        String response = sendRequestToServer("signup", new Gson().toJson(new String[]{username, email, hashedPassword}));
        if(response != null){
            sessionID = response;
            System.out.println("[INFO] The sign up was successful: " + sessionID);
            return "ok";
        }else {
            System.out.println("[ERROR] The sign up was not successful");
            return "fail";
        }
    }

    public static String endSession(){
        String response = sendRequestToServer("end", "");
        if(response.equals("ok")){
            System.out.println("[INFO] The session has been ended successfully");
        }
        return response;
    }

    /**
     * This function will prepare the HTTP client to send a request to the server.
     * With type it will know what URL to go to. The response will be a String, which can be formatted afterwards via JSON.
     * @param type
     * @param json
     * @return
     */
    private static String sendRequestToServer(String type, String json){
       try{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(requestUrl + type);

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            String msg = new BasicResponseHandler().handleResponse(response);
            client.close();
            if(msg.length() < 1)
                msg = null;
            System.out.println("[INFO] The server responded with: " + msg);
            return msg;
       }catch(Exception e){
           e.printStackTrace();
       }
       return null;
    }

    /**
     * This function will take a message of type String and hash it using MD5 digest.
     * @param message
     * @return a hashed password as type String
     */
    public static String hashString(String message){
        if(message == null)
            return null;
        String generatedMessage = Hashing.sha256().hashString(message, StandardCharsets.UTF_8).toString();
        return generatedMessage;
    }

}
