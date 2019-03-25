package client.loginscreen;

import client.Main;
import client.ServerRequests;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class PasswordRecoveryControler implements Initializable{

    private double x = 0;
    private double y = 0;

    @FXML
    private TextField tf_email;
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
     *
     */
    @FXML
    private void recover (MouseEvent event){
        String mailto = tf_email.getText();
        //setting the properties for the mail
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "localhost");

        Session session = Session.getInstance(prop);

        //creating the mail
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@gogreen.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailto));
            message.setSubject("Password recovery");

            String msg = "test";
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);

            //sending the message
            Transport.send(message);
        } catch(AddressException e){
            e.printStackTrace();
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }

    /**
     * This function will switch to the login screen
     * @param event MouseEvent type
     * @throws IOException
     */
    @FXML
    private void login(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
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
