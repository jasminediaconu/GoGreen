package client.windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainScreen extends Application {

    /**
     * This function will start the Application wrapper for the MainScreen, sets the size and title.
     * @param primaryStage The primary stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/client/windows/fxml/mainScreen.fxml"));
        primaryStage.setTitle("GoGreen");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("/client/windows/images/icon.png"));
    }

}
