package client.loginscreen;

import client.Main;
import client.ServerRequests;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SignupController implements Initializable {

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

    /**
     * This function will update x and y when the mouse is pressed
     *
     * @param event MouseEvent type
     */
    @FXML
    private void pressed(MouseEvent event) {
        xcoord = event.getSceneX();
        ycoord = event.getSceneY();
    }

    /**
     * This function will change the drag of the scene when the mouse is dragged
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
     * @param event MouseEvent type.
     * @throws IOException Exception.
     */
    @FXML
    private void login(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        fillScene(root, event);
    }

    /**
     * This function will handle the input of username, email,
     * and password when the sign up button is pressed.
     * It will also handle the responses returned by the ServerRequests class given it's query.
     * @param event MouseEvent type.
     * @throws Exception Exception.
     */
    @FXML
    private void signup(MouseEvent event) throws Exception {

        String username = tf_username.getText();
        String email = tf_email.getText();
        String password = pf_password.getText();

        System.out.println(username + ", " + email + ", " + password);
        String response = ServerRequests.signUp(username, email, password);
        if (response == null) {
            //USERNAME, EMAIL, OR PASSWORD MISSING
        } else if (response.equals("syntax")) {
            //INCORRECT SYNTAX
        } else if (response.equals("fail")) {
            //SIGN UP WAS UNSUCCESSFUL
        } else if (response.equals("ok")) {
            ServerRequests.getItems();

            Main.clientUser = ServerRequests.getClientUserProfile();

            //GOTO MAIN SCREEN
            String path = "../windows/fxml/mainScreen.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(path));
            fillScene(root, event);
        }
    }

    /**
     * This function will fill the screen with a new event stage evoked by the root.
     * @param root Parent type.
     * @param event MouseEvent event.
     */
    private void fillScene(Parent root, MouseEvent event) {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);

    }

    /**
     * This function takes the termsOfService fxml file as file to load in a new popup window
     * @param event MouseEvent type
     * @throws IOException
     */
    @FXML
    private void termsofservice(MouseEvent event) throws IOException {
        // will open a new window and display the terms of service in that
        String source = "termsOfService.fxml";
        privacyandterms(event, source);
    }

    /**
     * This function takes the privacyPolicy fxml file to load in a new popup window
     * @param event MouseEvent type
     * @throws IOException
     */
    @FXML
    private void privacypolicy (MouseEvent event) throws IOException {
        String source = "privacyPolicy.fxml";
        privacyandterms(event,source);
    }

    /**
     * This function opens a new popup window containing the source
     * @param event MouseEvent type
     * @param source String type
     * @throws IOException
     */
    @FXML
    private void privacyandterms (MouseEvent event, String source) throws IOException {
        // will open a new window and display the terms of service in that
        Parent newroot = FXMLLoader.load(this.getClass().getResource(source));
        Stage newstage = new Stage();
        newstage.setScene(new Scene(newroot));
        newstage.show();
    }

    /**
     * This function remains unused, but required to stay since this class implements Initializable.
     * @param url URL type.
     * @param resourceBundle ResourceBundle type.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
