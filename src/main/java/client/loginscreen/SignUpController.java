package client.loginscreen;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * The type Signup controller.
 */
public class SignUpController implements Initializable {

    private double xcoord = 0;
    private double ycoord = 0;

    @FXML
    private FontAwesomeIcon close;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Button signUpButton;
    @FXML
    private Text loginButton;
    @FXML
    private AnchorPane signUpScene;

    /**
     * This function handles the closing of the window, with the cross button.
     *
     * @param event MouseEvent type
     */
    @FXML
    private void close(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
    }

    private void setDisableScreen(boolean disableScreen) {
        if (tf_username != null) {
            tf_username.setDisable(disableScreen);
            pf_password.setDisable(disableScreen);
            tf_email.setDisable(disableScreen);
            signUpButton.setDisable(disableScreen);
            loginButton.setDisable(disableScreen);
        }
    }

    /**
     * This function will update x and y when the mouse is pressed.
     *
     * @param event MouseEvent type
     */
    @FXML
    private void pressed(MouseEvent event) {
        xcoord = event.getSceneX();
        ycoord = event.getSceneY();
    }

    /**
     * This function will change the drag of the scene when the mouse is dragged.
     *
     * @param event MouseEvent type
     */
    @FXML
    private void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - xcoord);
        stage.setY(event.getScreenY() - ycoord);
    }

    /**
     * This function will switch to the login screen.
     *
     * @throws IOException Exception.
     */
    @FXML
    private void login() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        fillScene(root);
    }

    /**
     * This function will handle the input of username, email,
     * and password when the sign up button is pressed.
     * It will also handle the responses returned by the ServerRequests class given it's query.
     *
     * @throws Exception Exception.
     */
    @FXML
    private void signUp() {
        setDisableScreen(true);

        String username = tf_username.getText();
        String email = tf_email.getText();
        String password = pf_password.getText();

        SignUpRequest signUpRequest = new SignUpRequest(username, password, email, this);
        signUpRequest.setDaemon(false);
        signUpRequest.execute();
    }


    /**
     * This function will be called when the signUp was successful.
     */
    public void signUpSucces() {
        if (tf_username != null) {
            //GOTO MAIN SCREEN
            try {
                String path = "../windows/fxml/mainScreen.fxml";
                Parent root = FXMLLoader.load(getClass().getResource(path));
                fillScene(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This function is called when the signUp failed.
     *
     * @param response the response
     */
    public void signUpFail(int response) {
        //sign up failed
        if (tf_username != null) {
            setDisableScreen(false);
            System.out.println("FAIL CODE:  " + response);
        }
    }

    /**
     * This function will fill the screen with a new event stage evoked by the root.
     *
     * @param root Parent type.
     */
    private void fillScene(Parent root) {

        Stage stage = (Stage) signUpScene.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);

    }

    /**
     * This function remains unused, but required to stay since this class implements Initializable.
     *
     * @param url            URL type.
     * @param resourceBundle ResourceBundle type.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
