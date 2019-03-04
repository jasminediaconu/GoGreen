package client.windows;
import com.jfoenix.controls.*;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ControllerMainScreen {

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

    JFXNodesList nodesList = new JFXNodesList();

    /**
     * This function links the different screens to their fxml files.
     */
    public ControllerMainScreen() {
        try {
            profile = FXMLLoader.load(this.getClass().getResource("/client/windows/fxml/profile.fxml"));
            agenda = FXMLLoader.load(this.getClass().getResource("/client/windows/fxml/agenda.fxml"));
            overview = FXMLLoader.load(this.getClass().getResource("/client/windows/fxml/overview.fxml"));
            leaderboard = FXMLLoader.load(this.getClass().getResource("/client/windows/fxml/leaderboard.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * The logout button closes the window.
//     */
//    @FXML
//    private void logoutButtonAction() throws IOException {
//        // get a handle to the stage
//        Parent root = FXMLLoader.load(getClass().getResource("../loginscreen/login.fxml"));
//        fillSceneTransparent(root, event);
//        // do what you have to do
//    }


    @FXML
    void logout(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../loginscreen/login.fxml"));
       fillSceneTransparent(root, event);
    }


    public void fillSceneTransparent(Parent root, MouseEvent event){
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);
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

            JFXButton ssbutton1 = new JFXButton("+");
            ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
            ssbutton1.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");

            JFXButton ssbutton2 = new JFXButton("T");
            ssbutton2.setButtonType(JFXButton.ButtonType.RAISED);
            ssbutton2.getStyleClass().addAll("animated-option-button", "animated-option-sub-button2");
            ssbutton2.setId("foodbutton");

            JFXButton ssbutton3 = new JFXButton("F");
            ssbutton3.setButtonType(JFXButton.ButtonType.RAISED);
            ssbutton3.getStyleClass().addAll("animated-option-button", "animated-option-sub-button3");

            JFXButton ssbutton4 = new JFXButton("E");
            ssbutton4.setButtonType(JFXButton.ButtonType.RAISED);
            ssbutton4.getStyleClass().addAll("animated-option-button", "animated-option-sub-button4");

            nodesList.addAnimatedNode(ssbutton1);
            nodesList.addAnimatedNode(ssbutton2);
            nodesList.addAnimatedNode(ssbutton3);
            nodesList.addAnimatedNode(ssbutton4);
            nodesList.setSpacing(10);
            nodesList.setRotate(180);
            nodesList.setLayoutX(940);
            nodesList.setLayoutY(690);

            mainPane.getChildren().add(agenda);
            mainPane.getChildren().add(nodesList);

            agenda.toBack();
            state = 0;
        }

        /**
         * To remove the plus button from other screens if Agenda is not selected
         */
        else if (!(agendaButton.isFocused() && state != 0)) {
            mainPane.getChildren().remove(nodesList);
            nodesList = new JFXNodesList();
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

    private void foodPopup() {

        JFXPopup pop = new JFXPopup();
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