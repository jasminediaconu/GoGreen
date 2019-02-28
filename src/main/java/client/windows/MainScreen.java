package client.windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/mainScreen.fxml"));
        primaryStage.setTitle("GoGreen");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("images/icon.png"));
    }

}
