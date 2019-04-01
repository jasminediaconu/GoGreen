package client.loginscreen;

import client.Main;
import client.windows.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import com.sun.security.ntlm.Server;

/**
 * The type Login controller.
 */
public class LoginController extends Controller implements Initializable {

    private double xcoord = 0;
    private double ycoord = 0;
    private boolean remembered = false;


    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private CheckBox rememberBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Text txtIncorrectPassword;
    @FXML
    private AnchorPane loginScene = new AnchorPane();

    /**
     * This function handles the closing of the window, with the cross button.
     *
     * @param event MouseEvent type
     */
    @FXML
    public void close(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
    }

    /**
     * This function will update x and y when the mouse is pressed.
     *
     * @param event MouseEvent type.
     */
    @FXML
    private void pressed(MouseEvent event) {
        xcoord = event.getSceneX();
        ycoord = event.getSceneY();
    }


    private void setDisableScreen(boolean disableScreen) {
        tfUsername.setDisable(disableScreen);
        pfPassword.setDisable(disableScreen);
        loginButton.setDisable(disableScreen);
        signUpButton.setDisable(disableScreen);
        rememberBox.setDisable(disableScreen);
    }

    /**
     * This function will change the drag of the scene when the mouse is dragged.
     *
     * @param event MouseEvent type.
     */
    @FXML
    private void dragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - xcoord);
        stage.setY(event.getScreenY() - ycoord);
    }

    /**
     * This function will handle the input of username and
     * password when the login button is pressed.
     * It will also handle the responses returned by the ServerRequests class given it's query.
     *
     * @param event Event type.
     */

    @FXML
    private void login(Event event) {

        String username = tfUsername.getText();
        String password = pfPassword.getText();
        boolean ishashed = false;
        String userpass = null;
        String keycode = "";
        int passwordlength = password.length();

        if (event instanceof KeyEvent) {
            KeyEvent keyevent = (KeyEvent) event;
            keycode = keyevent.getCode().toString();
        }
        if (event instanceof MouseEvent || keycode.equals("ENTER")) {
            try {
                if (remembered) {
                    FileReader freader = new FileReader("rememberme.txt");
                    BufferedReader reader = new BufferedReader(freader);
                    userpass = reader.readLine();
                    reader.close();
                    freader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (userpass != null && userpass.length() >= 5 && userpass.contains(";")) {
                password = userpass.split(";")[1];
                ishashed = true;
            }
            rememberme(username, password, ishashed, passwordlength);

            LoginRequest loginRequest = new LoginRequest(username, password, ishashed, this);
            loginRequest.setDaemon(false);
            loginRequest.execute();
            setDisableScreen(true);
        }
    }

    /**
     * This function is called when the login was succesfull.
     */
    public void loginSuccess() {
        if (tfUsername != null) {
            try {
                String path = "../windows/fxml/mainScreen.fxml";
                //GOTO MAIN SCREEN
                Parent root = FXMLLoader.load(getClass().getResource(path));
                fillScene(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This function is called when the login failed and displays that the password was incorrect..
     */
    public void loginFail() {
        try {
            txtIncorrectPassword.setVisible(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (tfUsername != null) {
            setDisableScreen(false);
        }
    }


    /**
     * This function will switch to the  screen.
     *
     * @param event MouseEvent type
     * @throws IOException Exception.
     */
    @FXML
    private void signup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        fillScene(root);
    }

    /**
     * This function will fill the screen with a new event stage evoked by the root.
     *
     * @param root Parent type.
     */
    private void fillScene(Parent root) {

        Stage stage = (Stage) loginScene.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);
    }

    /**
     * This function takes the termsOfService fxml file as file to load in a new popup window.
     *
     * @throws IOException if there is no input
     */
    @FXML
    private void termsofservice() throws IOException {
        // will open a new window and display the terms of service in that
        String source = "termsOfService.fxml";
        privacyandterms(source);
    }

    /**
     * This function takes the privacyPolicy fxml file to load in a new popup window.
     *
     * @throws IOException if there is no input
     */
    @FXML
    private void privacypolicy() throws IOException {
        String source = "privacyPolicy.fxml";
        privacyandterms(source);
    }

    /**
     * This function opens a new popup window containing the source.
     *
     * @param source String type
     * @throws IOException if there is no input
     */
    @FXML
    private void privacyandterms(String source) throws IOException {
        // will open a new window and display the terms of service in that
        Parent root = FXMLLoader.load(getClass().getResource(source));
        fillScene(root);
    }


    /**
     * This function will switch to the password recovery screen.
     *
     * @param event MouseEvent type
     * @throws IOException if there is no input
     */
    @FXML
    private void forgotpassword(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
        fillScene(root);
    }

    /**
     * This function will write the username and hashed password to a file.
     *
     * @param username String type
     * @param password String type
     * @throws IOException if there is no input
     */
    private void rememberme(String username, String password, boolean ishashed,
                            int passwordlength) {
        String hashedpassword = "";
        if (!ishashed) {
            hashedpassword = Main.hashString(password);
        } else {
            hashedpassword = password;
        }
        try {
            FileWriter writer = new FileWriter("rememberme.txt");
            writer.write("");
            if (rememberBox.isSelected()) {
                writer.write(username + ";" + hashedpassword + ";" + passwordlength);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will check whether a username and password are saved.
     * Set variables accordingly.
     * @throws IOException if there is no input
     */
    @FXML
    public void remembermecheck() throws IOException {
        //open the file and read its contents
        if (!remembered) {
            FileReader fread = new FileReader("rememberme.txt");
            BufferedReader reader = new BufferedReader(fread);
            String userpass = null;
            userpass = reader.readLine();
            reader.close();
            fread.close();
            //if there is something saved, set the text in textfields to the right values
            if (userpass != null && userpass.contains(";")) {
                rememberBox.setSelected(true);
                String[] userpassparts = userpass.split(";");
                tfUsername.setText(userpassparts[0]);
                int passlength = Integer.parseInt(userpassparts[2]);
                String passwordfiller = StringUtils.repeat("a", passlength);
                pfPassword.setText(passwordfiller);
                remembered = true;
            } else if (userpass == null) {
                rememberBox.setSelected(false);
            }
        }
    }


    /**
     * This function remains unused, but required to stay since this class implements Initializable.
     *
     * @param url            URL type.
     * @param resourceBundle resourceBundle type.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            File file = new File("rememberme.txt");
            file.createNewFile();
            remembermecheck();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }
}