package client.windows;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ControllerMainScreen {

    // Main screen
    @FXML
    private javafx.scene.layout.AnchorPane mainPane;
    @FXML
    private javafx.scene.layout.Pane welcomePane;
    private boolean welcome = true;

    // Logout button
    @FXML
    private javafx.scene.control.Button logoutButton;
    // Menu buttons
    @FXML
    private javafx.scene.control.Button agendaButton;
    @FXML
    private javafx.scene.control.Button profileButton;
    @FXML
    private javafx.scene.control.Button overviewButton;
    @FXML
    private javafx.scene.control.Button leaderboardButton;
    // Different panes for different screens
    @FXML
    private javafx.scene.layout.Pane agenda;
    @FXML
    private javafx.scene.layout.Pane profile;
    @FXML
    private javafx.scene.layout.Pane overview;
    @FXML
    private javafx.scene.layout.Pane leaderboard;
    // Toggle button
    @FXML
    private javafx.scene.control.ToggleButton toggleButton;
    // Menu bar
    @FXML
    private javafx.scene.layout.AnchorPane menuBar;
    // Slide effect
    @FXML
    private TranslateTransition slide;
    // Line
    @FXML
    private javafx.scene.shape.Line line;

    private int state = -1;

    //init
    public ControllerMainScreen(){
        try {
            profile = FXMLLoader.load(this.getClass().getResource("/client/windows/fxml/profile.fxml"));
            agenda = FXMLLoader.load(this.getClass().getResource("/client/windows/fxml/agenda.fxml"));
            overview = FXMLLoader.load(this.getClass().getResource("/client/windows/fxml/overview.fxml"));
            leaderboard = FXMLLoader.load(this.getClass().getResource("/client/windows/fxml/leaderboard.fxml"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Logout button closes the window.
     */
    @FXML
    private void logoutButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void selectedButton() throws IOException {
        //if button is selected remove welcome screen
        if(welcome) {
            mainPane.getChildren().remove(welcomePane);
            welcome = false;
        }

        FadeTransition ft;
        // If the button is focused change the active pane and the color
        if (agendaButton.isFocused() && state != 0) {
            agendaButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #95e743; -jfx-button-type: RAISED;");

            ft = new FadeTransition(Duration.millis(1000), agendaButton);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();


            mainPane.getChildren().add(agenda);
            agenda.toBack();
            state = 0;
        }
        if (profileButton.isFocused() && state != 1) {
            profileButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #95e743; -jfx-button-type: RAISED;");

            ft = new FadeTransition(Duration.millis(1000), profileButton);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();

            mainPane.getChildren().add(profile);
            profile.toBack();
            state = 1;
        }
        if (overviewButton.isFocused() && state != 2) {
            overviewButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #95e743; -jfx-button-type: RAISED;");

            ft = new FadeTransition(Duration.millis(1000), overviewButton);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();

            mainPane.getChildren().add(overview);
            overview.toBack();
            state = 2;
        }
        if (leaderboardButton.isFocused() && state != 3) {
            leaderboardButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #95e743; -jfx-button-type: RAISED;");

            ft = new FadeTransition(Duration.millis(1000), leaderboardButton);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();

            mainPane.getChildren().add(leaderboard);
            leaderboard.toBack();
            state = 3;
        }

        // If the button is not focused set the defaut background
        if (!agendaButton.isFocused()) {
            agendaButton.setStyle("-fx-background-color: #8C8686; -fx-text-fill: white; -jfx-button-type: FLAT;");
            mainPane.getChildren().remove(agenda);
        }
        if (!profileButton.isFocused()) {
            profileButton.setStyle("-fx-background-color: #8C8686; -fx-text-fill: white; -jfx-button-type: FLAT;");
            mainPane.getChildren().remove(profile);
        }
        if (!overviewButton.isFocused()) {
            overviewButton.setStyle("-fx-background-color: #8C8686; -fx-text-fill: white; -jfx-button-type: FLAT;");
            mainPane.getChildren().remove(overview);
        }
        if (!leaderboardButton.isFocused()) {
            leaderboardButton.setStyle("-fx-background-color:#8C8686; -fx-text-fill: white; -jfx-button-type: FLAT;");
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
}
