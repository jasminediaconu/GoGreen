package client.user;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("profile.fxml"));
        stage.setTitle("GoGreen");
        stage.setScene(new Scene(root, 1024, 768));
        stage.setResizable(false);
        stage.show();
    }

}