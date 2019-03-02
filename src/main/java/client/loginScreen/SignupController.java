package client.loginScreen;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SignupController implements Initializable {

    private double x = 0;
    private double y = 0;

    @FXML
    private FontAwesomeIcon close;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField pf_password;

    @FXML
    private void close(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
    }

    @FXML
    private void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    private void dragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void login(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        fillScene(root, event);
    }

    @FXML
    private void signup(MouseEvent event) throws Exception {

        String username = tf_username.getText();
        String email = tf_email.getText();
        String password = pf_password.getText();

        String response = ServerRequests.signUp(username, email, password);
        if(response == null){
            //USERNAME, EMAIL, OR PASSWORD MISSING
        }else if(response.equals("fail")){
            //SIGN UP WAS UNSUCCESSFUL
        }else if(response.equals("ok")){
            //GOTO MAIN SCREEN
            Parent root = FXMLLoader.load(getClass().getResource("../windows/fxml/mainScreen.fxml"));
            fillScene(root, event);
        }
    }

    private void fillScene(Parent root, MouseEvent event){
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
