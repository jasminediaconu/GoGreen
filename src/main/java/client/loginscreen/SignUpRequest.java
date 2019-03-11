package client.loginscreen;

import client.Main;
import client.ServerRequests;
import client.user.ClientUser;
import com.victorlaerte.asynctask.AsyncTask;

/**
 * The type Sign up request.
 */
public class SignUpRequest extends AsyncTask {

    /**
     * The constant SYNTAX.
     */
    public static final int SYNTAX = 0;
    /**
     * The constant FAIL.
     */
    public static final int FAIL = 1;
    /**
     * The constant OK.
     */
    public static final int OK = 2;

    private String username;
    private String password;
    private String email;
    private SignupController signupController;
    private ClientUser clientUser;
    private int response = -1;

    /**
     * Instantiates a new Sign up request.
     *
     * @param username         the username
     * @param password         the password
     * @param email            the email
     * @param signupController the signup controller
     */
    public SignUpRequest(String username, String password, String email,
                         SignupController signupController) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.signupController = signupController;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public Boolean doInBackground(Object[] params) {

        if (signUp()) {
            clientUser = ServerRequests.getClientUserProfile();
            if (clientUser != null) {
                return true;
            }
        }
        this.response = FAIL;
        return false;
    }

    @Override
    public void onPostExecute(Object params) {
        if (response != OK) {
            Main.clientUser = clientUser;
            signupController.signUpSucces();
        } else {
            signupController.signUpFail(response);
        }

    }

    private boolean signUp() {
        String response = ServerRequests.signUp(username, email, password);
        if (response == null) {
            //USERNAME, EMAIL, OR PASSWORD MISSING
        } else if (response.equals("syntax")) {
            //INCORRECT SYNTAX
            this.response = SYNTAX;
        } else if (response.equals("fail")) {
            //SIGN UP WAS UNSUCCESSFUL
            this.response = FAIL;
        } else if (response.equals("ok")) {
            ServerRequests.getItems();
            this.response = OK;
            return true;
        }
        return false;
    }

    @Override
    public void progressCallback(Object[] params) {

    }
}
