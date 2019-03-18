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
     * When the syntax of the email/username/password is not correct.
     */
    static final int SYNTAX = 0;
    /**
     * The constant FAIL.
     * When something in the process of singing up went wrong.
     */
    static final int FAIL = 1;
    /**
     * The constant OK.
     * When everything went ok.
     */
    static final int OK = 2;

    private String username;
    private String password;
    private String email;
    private SignUpController signUpController;
    private ClientUser clientUser;
    private int response = -1;

    /**
     * Instantiates a new Sign up request.
     *
     * @param username         the username
     * @param password         the password
     * @param email            the email
     * @param signUpController the signUp controller
     */
    SignUpRequest(String username, String password, String email,
                  SignUpController signUpController) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.signUpController = signUpController;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public Boolean doInBackground(Object[] params) {
        ServerRequests sv = new ServerRequests();
        if (signUp()) {
            clientUser = sv.getClientUserProfile();
            System.out.println("CLIENT: " + clientUser);
            this.response = OK;
            return true;
        }
        this.response = FAIL;
        return false;
    }

    @Override
    public void onPostExecute(Object params) {
        if (response == OK) {
            Main.clientUser = clientUser;
            signUpController.signUpSucces();
        } else {
            signUpController.signUpFail(response);
        }

    }

    //
    private boolean signUp() {
        ServerRequests sv = new ServerRequests();
        String response = sv.signUp(username, email, password);
        if (response == null) {
            //USERNAME, EMAIL, OR PASSWORD MISSING
            return false;
        } else if (response.equals("syntax")) {
            //IMPROPER SYNTAX
            return false;
        } else if (response.equals("username")) {
            //WRONG USERNAME
            return false;
        } else if (response.equals("email")) {
            //WRONG PASSWORD
            return false;
        } else if (response.equals("success")) {
            sv.getItems();
            return true;
        } else {
            //something failed
            return false;
        }
    }

    @Override
    public void progressCallback(Object[] params) {

    }
}
