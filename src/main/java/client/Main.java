package client;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.security.MessageDigest;

/**
 * The Main class of the GoGreen application.
 * This application is made by:
 * @author wouthaakman, ginotramontina, giulianoforghieri,
 * janwillemeriks, jasminediaconu, mandychang, and svetoslavstanoev
 */
public class Main {

    private static String requestUrl = "https://group72.herokuapp.com/login";

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        login("Wout Haakman", "wouthaakman");
    }

    /**
     * This function takes in a username and password, both of type String.
     * It will call the hashString function to hash the password for safe HTTP GET responses.
     * If the server responds with the correct result it will print this to the console, if not valid it will print "Wrong username or password"
     * @param username
     * @param password
     */
    public static void login(String username, String password){
        String hashedPassword = hashString(password);
        if(username == null || hashedPassword == null)
            return;

        Gson gson = new Gson();

        try{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(requestUrl);

            String json = gson.toJson(new String[]{username, hashedPassword});
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println("\n\n\nMessage!!!");
            System.out.println(new BasicResponseHandler().handleResponse(response) + ", it works");
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * This function will take a message of type String and hash it using MD5 digest.
     * @param message
     * @return a hashed password as type String
     */
    public static String hashString(String message){
        if(message == null)
            return null;
        String generatedMessage = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(message.getBytes());

            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedMessage = sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return generatedMessage;
    }

}
