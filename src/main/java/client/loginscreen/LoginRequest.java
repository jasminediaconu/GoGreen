package client.loginscreen;

import client.Main;
import client.ServerRequests;
import client.user.ClientUser;
import com.victorlaerte.asynctask.AsyncTask;
import javafx.scene.image.Image;

/**
 * The type Login request.
 */
public class LoginRequest extends AsyncTask {

    private LoginController loginController;
    private String username;
    private String password;
    private ClientUser clientUser;
    private boolean success = false;

    /**
     * Instantiates a new Login request.
     *
     * @param username        the username
     * @param password        the password
     * @param loginController the login controller
     */
    LoginRequest(String username, String password, LoginController loginController) {
        this.loginController = loginController;
        this.username = username;
        this.password = password;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public Boolean doInBackground(Object[] params) {
        if (login()) {
            success = getUserProfile();
        } else {
            success = false;
        }

        return true;
    }

    @Override
    public void onPostExecute(Object params) {
        if (success) {
            Main.clientUser = clientUser;
            loginController.loginSuccess();
        } else {
            loginController.loginFail();
        }
    }

    private boolean login() {
        String response = ServerRequests.login(username, password);

        if (response == null) {
            //USERNAME, EMAIL, OR PASSWORD MISSING
            return false;
        } else if (response.equals("syntax")) {
            //IMPROPER SYNTAX
            return false;
        } else if (response.equals("fail")) {
            //SOMETHING WENT WRONG
            return false;
        } else if (response.equals("username")) {
            //WRONG USERNAME
            return false;
        } else if (response.equals("password")) {
            //WRONG PASSWORD
            return false;
        } else if (response.equals("success")) {
            ServerRequests.getItems();
            return true;
        }
        return false;
    }

    private boolean getUserProfile() {
        clientUser = ServerRequests.getClientUserProfile();
        if (clientUser != null) {
            clientUser.setActivityList(ServerRequests.retrieveActivities("w"));
            String url = clientUser.getImageURL();
            if (url != null && url.length() != 0 && !url.equals("default")) {
                Image image = new Image(url);
                clientUser.setProfileImage(image);

            }
            return true;
        }
        return false;
    }


    @Override
    public void progressCallback(Object[] params) {

    }
}
