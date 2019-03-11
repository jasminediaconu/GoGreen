package client.windows;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController extends Pane implements Initializable {


    JFXNodesList nodesList = new JFXNodesList();

    AgendaController agendaController = new AgendaController();

    @FXML
    MenuButton user;
    @FXML
    private Pane foodWindow;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Pane welcomePane;
    @FXML
    private Pane agenda;
    @FXML
    private Pane profile;
    @FXML
    private Pane overview;
    @FXML
    private Pane leaderboard;

    @FXML
    private MenuItem logoutButton;
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

    private double xcoord = 0;
    private double ycoord = 0;
    private boolean welcome = true;
    private int state = -1;

    /**
     * This function links the different screens to their fxml files.
     */
    public MainScreenController() {
        try {
            String path = "/client/windows/fxml/";
            profile = FXMLLoader.load(this.getClass().getResource(path + "profile.fxml"));
            agenda = FXMLLoader.load(this.getClass().getResource(path + "agenda.fxml"));
            overview = FXMLLoader.load(this.getClass().getResource(path + "overview.fxml"));
            leaderboard = FXMLLoader.load(this.getClass().getResource(path + "leaderboard.fxml"));
            foodWindow = FXMLLoader.load(this.getClass().getResource(path + "foodWindow.fxml"));
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
        Stage stage = (Stage) user.getScene().getWindow();
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
        styleFocused(agendaButton, agenda, 0);
        styleFocused(profileButton, profile, 1);
        styleFocused(overviewButton, overview, 2);
        styleFocused(leaderboardButton, leaderboard, 3);

    }

    /**
     * This function enables per button checking if it is focused by the user,
     * which in affect will add or remove a pane from the main screen.
     *
     * @param button The button that is checked if it is focused
     * @param pane   The pane that clicking the button will affect
     */
    private void styleFocused(Button button, Pane pane, int stt) {
        String css1 = "-fx-background-color:#ffffff;-fx-text-fill:#95e743;-jfx-button-type:RAISED;";
        String css2 = "-fx-background-color:#8C8686;-fx-text-fill:white;-jfx-button-type:FLAT;";
        FadeTransition ft;
        if (button.isFocused() && state != stt) {
            button.setStyle(css1);

            ft = new FadeTransition(Duration.millis(1000), button);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();

            // Loading the plusbutton with the nodes and styling
            agendaController.loadPlusButton();

            //
            nodesList = agendaController.getNodesList();

            // Showing any of the panes a.k.a fxml-s passed in as an argument
            mainPane.getChildren().add(pane);

            if (pane.equals(agenda)) {
                mainPane.getChildren().add(nodesList);

            } else if (!pane.equals(agenda)) {
                // Remove the plus  button if Agenda is not the screen the user selected
                // Assign an empty nodeList to the plus button,
                // so the next time the user clicks Agenda
                // Only 4 nodes are shown in total when clicking the plus button

                mainPane.getChildren().remove(agendaController.getNodesList());
                agendaController.clearPlusButton();
            }

            pane.toBack();
            state = stt;
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

    @FXML
    private void applyActivity(MouseEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Pane getAgenda() {
        return agenda;
    }

    public Pane getFoodWindow() {
        return foodWindow;
    }
}
