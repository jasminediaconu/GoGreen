package client.loginscreen;

import client.ServerRequests;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PasswordRecoveryController implements Initializable {

    private double doublex = 0;
    private double doubley = 0;

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCode;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Text txterror1;
    @FXML
    private Text txterror2;

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
     * @param event MouseEvent type
     */
    @FXML
    private void pressed(MouseEvent event) {
        doublex = event.getSceneX();
        doubley = event.getSceneY();
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

        stage.setX(event.getScreenX() - doublex);
        stage.setY(event.getScreenY() - doubley);
    }

    /**
     * This function will send a request to the server
     * to send a code to recover the password of the account linked to the email to the email.
     *
     * @param event Event type.
     */
    @FXML
    private void recoveryCode(Event event) {
        String keycode = "";
        if (event instanceof KeyEvent) {
            KeyEvent keyevent = (KeyEvent) event;
            keycode = keyevent.getCode().toString();
        }
        if (event instanceof MouseEvent || keycode.equals("ENTER")) {
            String mail = tfEmail.getText();
            ServerRequests sv = new ServerRequests();
            String response = sv.recoverPassword(mail);
            txterror1.setVisible(false);
            txterror2.setVisible(false);
            if (response.equals("syntax")) {
                txterror1.setText("Please enter a valid Email address.");
                txterror1.setVisible(true);
            } else if (response.equals("fail")) {
                txterror1.setText("Something went wrong, please try again later.");
                txterror1.setVisible(true);
            }
        }


    }

    /**
     * This function will send a request to the server
     * to change the password of the account linked to the recovery code.
     *
     * @param event Event type.
     */
    @FXML
    private void recoverPassword(Event event) {
        String keycode = "";
        if (event instanceof KeyEvent) {
            KeyEvent keyevent = (KeyEvent) event;
            keycode = keyevent.getCode().toString();
        }
        if (event instanceof MouseEvent || keycode.equals("ENTER")) {
            String code = tfCode.getText();
            String password = pfPassword.getText();
            ServerRequests sv = new ServerRequests();
            String response = sv.changePassword(code, password);
            txterror1.setVisible(false);
            txterror2.setVisible(false);
            if (response.equals("syntax")) {
                txterror2.setText("Please enter a valid password.");
                txterror2.setVisible(true);
            } else if (response.equals("fail")) {
                txterror2.setText("Something went wrong, please try again later.");
                txterror2.setVisible(true);
            } else if (response.equals("success")){
                try{login(event);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * This function will switch to the login screen.
     *
     * @param event MouseEvent type
     * @throws IOException if there is no input
     */
    @FXML
    private void login(Event event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        fillScene(root, event);
    }

    /**
     * This function will fill the screen with a new event stage evoked by the root.
     *
     * @param root  Parent type
     * @param event MouseEvent event
     */
    private void fillScene(Parent root, Event event) {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);
    }

    /**
     * This function remains unused, but required to stay since this class implements Initializable.
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
