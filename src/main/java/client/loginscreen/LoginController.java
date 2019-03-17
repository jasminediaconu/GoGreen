package client.loginscreen;

import client.windows.Controller;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Login controller.
 */
public class LoginController extends Controller implements Initializable {

    private double xcoord = 0;
    private double ycoord = 0;

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private CheckBox rememberBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;


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
        tf_username.setDisable(disableScreen);
        pf_password.setDisable(disableScreen);
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
     */

    @FXML
    private void login() {

        String username = tf_username.getText();
        String password = pf_password.getText();

        LoginRequest loginRequest = new LoginRequest(username, password, this);
        loginRequest.setDaemon(false);
        loginRequest.execute();
        setDisableScreen(true);
    }

    /**
     * This function is called when the login was succesfull.
     */
    public void loginSuccess() {
        try {
            String path = "../windows/fxml/mainScreen.fxml";
            //GOTO MAIN SCREEN
            Parent root = FXMLLoader.load(getClass().getResource(path));
            fillScene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is called when the login failed.
     */
    public void loginFail() {
        setDisableScreen(false);
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
     * This function remains unused, but required to stay since this class implements Initializable.
     *
     * @param url            URL type.
     * @param resourceBundle resourceBundle type.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void update() {

    }
}