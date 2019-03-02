package client.user;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String LEFT = "LEFT";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("profile.fxml"));


        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setTitle("GoGreen");
        if (root != null) {
            stage.setScene(new Scene(root, 1024, 768));
        }
        stage.setResizable(false);
        stage.show();

    }

}