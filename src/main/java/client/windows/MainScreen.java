package client.windows;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class MainScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This function will start the Application wrapper for the MainScreen, sets the size and title.
     *
     * @param primaryStage The primary stage
     * @throws Exception Exception.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("fxml/mainScreen.fxml"));
        primaryStage.setTitle("GoGreen");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.getScene().setFill(Color.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
