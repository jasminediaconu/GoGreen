package client.windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * The type Main screen.
 */
public class MainScreen extends Application {

    private static Stage primaryStage;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Gets primary stage.
     *
     * @return the primary stage
     */
    public static synchronized Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * This function will start the Application wrapper for the MainScreen, sets the size and title.
     *
     * @param primaryStage The primary stage
     * @throws Exception Exception.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainScreen.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/mainScreen.fxml"));
        primaryStage.setTitle("Greenly");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("/client/windows/images/icon.png"));
    }
}

