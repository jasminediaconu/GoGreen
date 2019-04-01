package client.loginscreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class PasswordRecoveryController implements Initializable {

    private double doublex = 0;
    private double doubley = 0;

    @FXML
    private TextField tfEmail;

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
     * Function to recover the password.
     * A mail is created and a message is sent.
     */
    @FXML
    private void recover(MouseEvent event) {
        String mailto = tfEmail.getText();
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
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function will switch to the login screen.
     *
     * @param event MouseEvent type
     * @throws IOException if there is no input
     */
    @FXML
    private void login(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        fillScene(root, event);
    }

    /**
     * This function will fill the screen with a new event stage evoked by the root.
     *
     * @param root  Parent type
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
     * This function remains unused, but required to stay since this class implements Initializable.
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}