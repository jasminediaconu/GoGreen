package client.windows;

import client.Main;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Main screen controller.
 */
public class MainScreenController extends Pane implements Initializable {

    private final String path = "/client/windows/fxml/";

    private boolean welcome = true;
    private int state = -1;

    private ArrayList<Controller> controllers = new ArrayList<>();
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane welcomePane;
    @FXML
    private MenuButton logoutButton;
    @FXML
    private MenuItem logout;
    @FXML
    private Text dayField;
    @FXML
    private Text userNameField;

    @FXML
    private Button agendaButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button overviewButton;
    @FXML
    private Button leaderboardButton;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private AnchorPane menuBar;
    @FXML
    private TranslateTransition slide;
    @FXML
    private Line line;
    @FXML
    private Circle profileImage;
    @FXML
    private Text usernameField;

    private double xcoord = 0;
    private double ycoord = 0;

    /**
     * This function links the different screens to their fxml files.
     */
    public MainScreenController() {
        addController("agenda.fxml");
        addController("profile.fxml");
        addController("overview.fxml");
        addController("leaderboard.fxml");
    }


    public ArrayList<Controller> getControllers() {
        return controllers;
    }

    /**
     * Loads the .fxml file and adds its controller to the arrayList
     *
     * @param fxmlName the fxml file name
     */
    private void addController(String fxmlName) {
        URL fxmlPath = this.getClass().getResource(path + fxmlName);
        FXMLLoader loader = new FXMLLoader(fxmlPath);
        try {
            Pane pane = loader.load();
            Controller controller = loader.getController();
            controller.setMainScreenController(this);
            controller.setPane(pane);
            controllers.add(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
     * This function minimizes the window, with the minus button.
     *
     * @param event MouseEvent type
     */
    @FXML
    public void minimize(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * This function will update x and y when the mouse is pressed.
     *
     * @param event MouseEvent type
     */
    @FXML
    private void pressed(MouseEvent event) {
        xcoord = event.getSceneX();
        ycoord = event.getSceneY();
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

        stage.setX(event.getScreenX() - xcoord);
        stage.setY(event.getScreenY() - ycoord);
    }

    /**
     * This function will disconnect the user from the server and the session will be closed.
     * It happens when pressing the logout field in the dropdown menu under the user profile image.
     */
    @FXML
    private void logout() throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/client/loginScreen/login.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        login.initStyle(StageStyle.TRANSPARENT);
        login.show();
        login.getIcons().add(new Image("client/windows/images/icon.png"));
        //TODO Clean up, save stuff to database and close session id
    }

    /**
     * When a button is selected/unselected,
     * this function changes its style and the displayed screen.
     */
    @FXML
    private void selectedButton() {
        //if button is selected remove welcome screen

        if (welcome) {
            mainPane.getChildren().remove(welcomePane);
            mainPane.setStyle("-fx-background-color:null;");
            welcome = false;
        }
        // If the button is focused change the active pane and the color
        styleFocused(agendaButton, 0);
        styleFocused(profileButton, 1);
        styleFocused(overviewButton, 2);
        styleFocused(leaderboardButton, 3);

        agendaButton.setOnMouseEntered(this::hideButtonAction);
        profileButton.setOnMouseEntered(this::hideButtonAction);
        overviewButton.setOnMouseEntered(this::hideButtonAction);
        leaderboardButton.setOnMouseEntered(this::hideButtonAction);

    }


    /**
     * Function to hide the popovers.
     * @param event
     */
    public void hideButtonAction(javafx.scene.input.MouseEvent event) {
        AgendaController agendaController = (AgendaController) controllers.get(0);

        agendaController.getPopOver1().hide();
        agendaController.getPopOver2().hide();
        agendaController.getPopOver3().hide();
    }

    /**
     * This function enables per button checking if it is focused by the user,
     * which in affect will add or remove a pane from the main screen.
     *
     * @param button The button that is checked if it is focused
     */
    private void styleFocused(Button button, int stt) {
        String css1 = "-fx-background-color:#ffffff;-fx-text-fill:#95e743;-jfx-button-type:RAISED;";
        String css2 = "-fx-background-color:#8C8686;-fx-text-fill:white;-jfx-button-type:FLAT;";
        FadeTransition ft;
        Pane pane = controllers.get(stt).getPane();
        if (button.isFocused() && state != stt) {
            button.setStyle(css1);

            ft = new FadeTransition(Duration.millis(1000), button);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();

            // Showing any of the panes a.k.a fxml-s passed in as an argument
            mainPane.getChildren().add(pane);

            pane.toBack();
            state = stt;
            controllers.get(state).update();
        }
        if (!button.isFocused()) {
            button.setStyle(css2);
            mainPane.getChildren().remove(pane);
        }
    }

    /**
     * When the toggle button is pressed, the menu bar will be hidden/shown.
     */
    @FXML
    private void toggleMenuShowHide() {
        if (toggleButton.isSelected()) {
            // animation
            RotateTransition rt = new RotateTransition(Duration.millis(400), toggleButton);
            rt.setFromAngle(0);
            rt.setToAngle(-180);
            rt.setCycleCount(1);
            rt.setAutoReverse(true);
            rt.play();

            // Hide menu bar
            slide = new TranslateTransition(Duration.millis(500), menuBar);
            slide.setFromX(0);
            slide.setToX(-165);
            slide.setRate(1);
            slide.play();

            agendaButton.setVisible(false);
            profileButton.setVisible(false);
            overviewButton.setVisible(false);
            leaderboardButton.setVisible(false);
            line.setVisible(false);
        } else {
            // animation
            RotateTransition rt = new RotateTransition(Duration.millis(300), toggleButton);
            rt.setFromAngle(0);
            rt.setToAngle(90);
            rt.setCycleCount(1);
            rt.setAutoReverse(true);
            rt.play();

            // Show menu bar
            slide = new TranslateTransition(Duration.millis(500), menuBar);
            slide.setFromX(-165);
            slide.setToX(0);
            slide.setRate(1);
            slide.play();

            agendaButton.setVisible(true);
            profileButton.setVisible(true);
            overviewButton.setVisible(true);
            leaderboardButton.setVisible(true);
            line.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameField.setText(Main.clientUser.getUsername());
        dayField.setText("" + Main.clientUser.getStreakLength());
        for (Controller controller : controllers) {
            controller.init();
        }
    }

    public Pane getAgenda() {
        return controllers.get(0).getPane();
    }

    public void setUsernameField(String username) {
        usernameField.setText(username);
        usernameField.setText(username);
    }

    public void setProfileImage(Image image) {
        profileImage.setFill(new ImagePattern(image));
    }
}
