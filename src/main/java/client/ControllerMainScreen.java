package client;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerMainScreen {

    // Logout button
    @FXML
    private javafx.scene.control.Button logoutButton;

    /**
     * Logout button closes the window
     */
    @FXML
    private void logoutButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    // Menu buttons
    @FXML private javafx.scene.control.Button selectedButton1;
    @FXML private javafx.scene.control.Button selectedButton2;
    @FXML private javafx.scene.control.Button selectedButton3;
    @FXML private javafx.scene.control.Button selectedButton4;

    // Different panes for different screens
    @FXML private javafx.scene.layout.Pane selectedPane1;
    @FXML private javafx.scene.layout.Pane selectedPane2;
    @FXML private javafx.scene.layout.Pane selectedPane3;
    @FXML private javafx.scene.layout.Pane selectedPane4;

    /**
     * When a menu button is clicked the pane changes and the selected menu tab is highlighted
     */
    @FXML
    private void selectedButton() {

        FadeTransition ft;
        // If the button is focused change the active pane and the color
        if(selectedButton1.isFocused()) {
            selectedButton1.setStyle("-fx-background-color: #f8ee96; -fx-text-fill: black; -jfx-button-type: RAISED;");

            selectedPane1.setVisible(true);

            ft = new FadeTransition(Duration.millis(1000), selectedButton1);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();
        }
        if(selectedButton2.isFocused()) {
            selectedButton2.setStyle("-fx-background-color: #f8ee96; -fx-text-fill: black; -jfx-button-type: RAISED;");

            selectedPane2.setVisible(true);

            ft = new FadeTransition(Duration.millis(1000), selectedButton2);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();
        }
        if(selectedButton3.isFocused()) {
            selectedButton3.setStyle("-fx-background-color: #f8ee96; -fx-text-fill: black; -jfx-button-type: RAISED;");

            selectedPane3.setVisible(true);

            ft = new FadeTransition(Duration.millis(1000), selectedButton3);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();
        }
        if(selectedButton4.isFocused()) {
            selectedButton4.setStyle("-fx-background-color: #f8ee96; -fx-text-fill: black; -jfx-button-type: RAISED;");

            selectedPane4.setVisible(true);

            ft = new FadeTransition(Duration.millis(1000), selectedButton4);
            ft.setFromValue(0.6);
            ft.setToValue(1.0);
            ft.play();
        }

        // If the button is not focused set the defaut background
        if(!selectedButton1.isFocused()) {
            selectedButton1.setStyle("-fx-background-color: #0a9703; -fx-text-fill: white; -jfx-button-type: FLAT;");
            selectedPane1.setVisible(false);
        }
        if(!selectedButton2.isFocused()) {
            selectedButton2.setStyle("-fx-background-color: #0a9703; -fx-text-fill: white; -jfx-button-type: FLAT;");
            selectedPane2.setVisible(false);
        }
        if(!selectedButton3.isFocused()) {
            selectedButton3.setStyle("-fx-background-color: #0a9703; -fx-text-fill: white; -jfx-button-type: FLAT;");
            selectedPane3.setVisible(false);
        }
        if(!selectedButton4.isFocused()) {
            selectedButton4.setStyle("-fx-background-color: #0a9703; -fx-text-fill: white; -jfx-button-type: FLAT;");
            selectedPane4.setVisible(false);
        }
    }

    // Toggle button
    @FXML private javafx.scene.control.ToggleButton toggleButton;
    // Menu bar
    @FXML private javafx.scene.layout.AnchorPane menuBar;
    // Slide effect
    @FXML private TranslateTransition slide;

    /**
     * When the toggle button is pressed, the menu bar will be hidden/shown
     */
    @FXML
    private void toggleMenuShowHide() {
        if(toggleButton.isSelected()) {
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

            selectedButton1.setVisible(false);
            selectedButton2.setVisible(false);
            selectedButton3.setVisible(false);
            selectedButton4.setVisible(false);


        }
        else {
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

            selectedButton1.setVisible(true);
            selectedButton2.setVisible(true);
            selectedButton3.setVisible(true);
            selectedButton4.setVisible(true);
        }
    }


}
