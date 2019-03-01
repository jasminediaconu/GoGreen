package client.loginScreen;

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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private double x = 0;
    private double y = 0;

    @FXML
    private TextField tf_username;

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
    private void login(MouseEvent event) throws SQLException, IOException {

        String username;
        String password;

        username = tf_username.getText();
        password = pf_password.getText();

        Connection connection = DBConnect.getInstance().getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from users where username"
                + " = '" + username + "' or email = '" + pf_password + "' and password = '" + password + "'");

        if (resultSet.next()) {
            Parent root = FXMLLoader.load(getClass().getResource("mainScreenDummy.fxml"));

            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);

            scene.setFill(Color.TRANSPARENT);

        }
    }

    @FXML
    private void signup(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
