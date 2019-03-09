package client.loginscreen;

import client.ServerRequests;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private double x = 0;
    private double y = 0;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    /**
     * This function handles the closing of the window, with the cross button.
     * @param event MouseEvent type
     */
    @FXML
    public void close(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
    }

    /**
     * This function will update x and y when the mouse is pressed
     * @param event MouseEvent type
     */
    @FXML
    private void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /**
     * This function will change the drag of the scene when the mouse is dragged
     * @param event MouseEvent type
     */
    @FXML
    private void dragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    /**
     * This function will handle the input of username and password when the login button is pressed
     * It will also handle the responses returned by the ServerRequests class given it's query
     * @param event MouseEvent type
     * @throws Exception
     */

    @FXML
    private void login(MouseEvent event) throws Exception {

        String username = tf_username.getText();
        String password = pf_password.getText();

        String response = ServerRequests.login(username, password);

        if (response == null) {
            //USERNAME OR PASSWORD MISSING
        } else if (response.equals("fail")) {
            //WRONG USERNAME OR PASSWORD
        } else if (response.startsWith("success:")) {
            ServerRequests.getItems();

            //GOTO MAIN SCREEN
            Parent root = FXMLLoader.load(getClass().getResource("../windows/fxml/mainScreen.fxml"));
            fillScene(root, event);
        }
    }

    /**
     * This function will switch to the signup screen
     * @param event MouseEvent type
     * @throws IOException
     */
    @FXML
    private void signup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        fillScene(root, event);
    }

    /**
     * This function will fill the screen with a new event stage evoked by the root
     * @param root Parent type
     * @param event MouseEvent event
     */
    private void fillScene(Parent root, MouseEvent event) {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);
    }

    /**
     * This function remains unused, but required to stay since this class implements Initializable
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
