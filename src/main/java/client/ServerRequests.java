package client;

import java.util.regex.Pattern;

public class ServerRequests {

    private static String requestUrl = "https://group72.herokuapp.com/";

    /**
     * This function takes in a username and password, both of type String.
     * It will call the hashString function to hash the password for safe HTTP POST responses.
     * If the server responds with the correct result it will print this to the console, if not valid it will print "Wrong username or password"
     *
     * @param username
     * @param password
     */
    public static String login(String username, String password, boolean ishashed) {
        String hashedPassword = null;
        if(ishashed){
            hashedPassword = password;
        } else if(!ishashed){
            hashedPassword = Main.hashString(password);
        }
        if (username == null || hashedPassword == null)
            return null;
        String response = sendRequestToServer("login", new Gson().toJson(new String[]{username, hashedPassword}));
        if(response != null) {
            String[] resArr = response.split("::");
            //System.out.println("[INFO] Login returned the following user_id: " + resArr[1]);
            Main.sessionID = resArr[0];
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
     *
     * @param username
     * @param password
     */
    public static String signUp(String username, String email, String password) {
        String hashedPassword = Main.hashString(password);
        Pattern stdPattern = Pattern.compile("[A-Za-z0-9_]+");
        Pattern emailPattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
        if (username == null || email == null || hashedPassword == null)
            return null;

        if (!stdPattern.matcher(username).matches() || !emailPattern.matcher(email).matches() || !stdPattern.matcher(password).matches())
            return "syntax";

        String response = sendRequestToServer("signup", new Gson().toJson(new String[]{username, email, hashedPassword}));
        if(response != null){
            Main.sessionID = response;
            System.out.println("[INFO] The sign up was successful: " + Main.sessionID);
            return "ok";
        }else {
            System.out.println("[ERROR] The sign up was not successful");
            return "fail";
        }
    }

    public static String endSession(){
        System.out.println("[INFO] Ending session, create a new session to continue or close the application.");
        String response = sendRequestToServer("end", new Gson().toJson(Main.sessionID));
        if(response.equals("ok")) {
            System.out.println("[INFO] The session has been ended successfully.");
        }else {
            System.out.println("[WARNING] Something went wrong ending the session.");
        }
        return response;
    }

    /**
     * This function will prepare the HTTP client to send a request to the server.
     * With type it will know what URL to go to. The response will be a String, which can be formatted afterwards via JSON.
     *
     * @param type
     * @param json
     * @return
     */
    private static String sendRequestToServer(String type, String json) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
