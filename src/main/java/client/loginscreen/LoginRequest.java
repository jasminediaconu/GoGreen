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


    public ClientUser clientUser;
    private LoginController loginController;
    private String username;
    private String password;
    private boolean ishashed;
    private boolean success = false;

    /**
     * Instantiates a new Login request.
     *
     * @param username        the username
     * @param password        the password
     * @param loginController the login controller
     */
    LoginRequest(String username, String password, boolean ishashed, LoginController loginController) {
        this.loginController = loginController;
        this.username = username;
        this.password = password;
        this.ishashed = ishashed;

    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public Boolean doInBackground(Object[] params) {
        if (login()) {
            getUserProfile();
            success = loadImage();
        } else {
            success = false;
        }

        return success;
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

    /**
     * Login boolean.
     *
     * @return the boolean
     */
    public boolean login() {
        ServerRequests sv = new ServerRequests();
        String response = sv.login(username, password, ishashed);

        if (response == null) {
            //USERNAME, EMAIL, OR PASSWORD MISSING
            return false;
        } else if (response.equals("syntax")) {
            //IMPROPER SYNTAX
            return false;
        } else if (response.equals("username")) {
            //WRONG USERNAME
            return false;
        } else if (response.equals("password")) {
            //WRONG PASSWORD
            return false;
        } else if (response.equals("success")) {
            sv.getItems();
            sv.getAchievements();
            return true;
        } else {
            //something went wrong
            return false;
        }
    }

    /**
     * Gets user profile.
     *
     * @return the user profile
     */
    public void getUserProfile() {
        ServerRequests sv = new ServerRequests();
        clientUser = sv.getClientUserProfile();
        clientUser.setActivityList(sv.retrieveActivities("w"));
    }

    /**
     * Load image boolean.
     *
     * @return the boolean
     */
    public boolean loadImage() {
        String url = clientUser.getImageURL();
        if (url != null) {

            if (url.equals("default")) {
                return true;
            }

            Image image = new Image(url);
            clientUser.setProfileImage(image);
            return true;
        }
        return false;
    }


    @Override
    public void progressCallback(Object[] params) {

    }
}
