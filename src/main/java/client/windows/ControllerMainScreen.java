package client.windows;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainScreen implements Initializable {

    private double x = 0;
    private double y = 0;
    private boolean welcome = true;
    private int state = -1;
    @FXML private javafx.scene.layout.AnchorPane mainPane;
    @FXML private javafx.scene.layout.Pane welcomePane;
    @FXML private javafx.scene.control.Button logoutButton;
    @FXML private javafx.scene.control.Button agendaButton;
    @FXML private javafx.scene.control.Button profileButton;
    @FXML private javafx.scene.control.Button overviewButton;
    @FXML private javafx.scene.control.Button leaderboardButton;
    @FXML private javafx.scene.layout.Pane agenda;
    @FXML private javafx.scene.layout.Pane profile;
    @FXML private javafx.scene.layout.Pane overview;
    @FXML private javafx.scene.layout.Pane leaderboard;
    @FXML private javafx.scene.control.ToggleButton toggleButton;
    @FXML private javafx.scene.layout.AnchorPane menuBar;
    @FXML private TranslateTransition slide;
    @FXML private javafx.scene.shape.Line line;

    private void fillSceneTransparent(Parent root, MouseEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
    }

    /**
     * This function links the different screens to their fxml files.
     */
    public ControllerMainScreen(MouseEvent event) {
        try {
            String path = "/client/windows/fxml/";
            Parent root = FXMLLoader.load(getClass().getResource(path + "mainScreen.fxml"));
            profile = FXMLLoader.load(this.getClass().getResource(path + "profile.fxml"));
            agenda = FXMLLoader.load(this.getClass().getResource(path + "agenda.fxml"));
            overview = FXMLLoader.load(this.getClass().getResource(path + "overview.fxml"));
            leaderboard = FXMLLoader.load(this.getClass().getResource(path + "leaderboard.fxml"));
            fillSceneTransparent(root, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void close(MouseEvent event) {

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

    /**
     * The logout button closes the window.
     */
    @FXML
    private void logoutButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
    /**
     * When a button is selected/unselected changes its style and the displayed screen.
     */
    @FXML
    private void selectedButton() {
        //if button is selected remove welcome screen
        String css = "-fx-background-color:#ffffff;-fx-text-fill:#95e743;-jfx-button-type:RAISED;";
        String css2 = "-fx-background-color:#8C8686;-fx-text-fill:white;-jfx-button-type:FLAT;";

        if (welcome) {
            mainPane.getChildren().remove(welcomePane);
            mainPane.setStyle("-fx-background-color:null;");
            welcome = false;
        }
        FadeTransition ft;
        // If the button is focused change the active pane and the color
        if (agendaButton.isFocused() && state != 0) {
            agendaButton.setStyle(css);

            ft = new FadeTransition(Duration.millis(1000), agendaButton);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();

            mainPane.getChildren().add(agenda);
            agenda.toBack();
            state = 0;
        }
        if (profileButton.isFocused() && state != 1) {
            profileButton.setStyle(css);

            ft = new FadeTransition(Duration.millis(1000), agendaButton);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();

            mainPane.getChildren().add(profile);
            profile.toBack();
            state = 1;
        }
        if (overviewButton.isFocused() && state != 2) {
            overviewButton.setStyle(css);

            ft = new FadeTransition(Duration.millis(1000), agendaButton);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();

            mainPane.getChildren().add(overview);
            overview.toBack();
            state = 2;
        }
        if (leaderboardButton.isFocused() && state != 3) {
            leaderboardButton.setStyle(css);

            ft = new FadeTransition(Duration.millis(1000), agendaButton);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();

            mainPane.getChildren().add(leaderboard);
            leaderboard.toBack();
            state = 3;
        }

        if (!agendaButton.isFocused()) {
            agendaButton.setStyle(css2);
            mainPane.getChildren().remove(agenda);
        }
        if (!profileButton.isFocused()) {
            profileButton.setStyle(css2);
            mainPane.getChildren().remove(profile);
        }
        if (!overviewButton.isFocused()) {
            overviewButton.setStyle(css2);
            mainPane.getChildren().remove(overview);
        }
        if (!leaderboardButton.isFocused()) {
            leaderboardButton.setStyle(css2);
            mainPane.getChildren().remove(leaderboard);
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

    }
}