package client.loginscreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginApp extends Application {

    /**
     * This function will start the LoginScreen, and also consecutively the SignupScreen,
     * by setting the windows settings such as title and scene.
     *
     * @param stage The stage
     * @throws Exception When the FXML files couldn't be loaded.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/client/loginScreen/login.fxml"));

        Scene scene = new Scene(root);

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("Greenly");
        stage.setScene(scene);
        stage.show();

        stage.getIcons().add(new Image("/client/windows/images/icon.png"));
    }
}
