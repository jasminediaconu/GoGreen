package client.windows;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;

import java.net.URL;
import java.util.ResourceBundle;

public class AgendaController implements Initializable {

    MainScreenController mainScreenController;

    JFXButton ssbutton2;
    JFXButton ssbutton3;
    JFXButton ssbutton4;
    private JFXNodesList nodesList;

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> foodchoices = new ComboBox<>();


    public AgendaController() {
        nodesList = new JFXNodesList();
    }


    public void loadPlusButton() {
        // This code creates the GREEN animated PLUS button when agenda is selected
        JFXButton ssbutton1 = new JFXButton();
        ssbutton1.setId("plusbutton");
        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton1.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");

        ssbutton2 = new JFXButton();
        ssbutton2.setId("transportbutton");
        ssbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton2.getStyleClass().addAll("animated-option-button", "animated-option-sub-button2");
        ssbutton2.setOnMouseClicked(this::transportButtonAction);


        ssbutton3 = new JFXButton();
        ssbutton3.setId("foodbutton");
        ssbutton3.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton3.getStyleClass().addAll("animated-option-button", "animated-option-sub-button3");
        ssbutton3.setOnMouseClicked(this::foodButtonAction);

        ssbutton4 = new JFXButton();
        ssbutton4.setId("energybutton");
        ssbutton4.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton4.getStyleClass().addAll("animated-option-button", "animated-option-sub-button4");
        ssbutton4.setOnMouseClicked(this::energyButtonAction);

        nodesList.getStylesheets().add("client/windows/css/agenda.css");

        nodesList.addAnimatedNode(ssbutton1);
        nodesList.addAnimatedNode(ssbutton2);
        nodesList.addAnimatedNode(ssbutton3);
        nodesList.addAnimatedNode(ssbutton4);
        nodesList.setSpacing(10);
        nodesList.setRotate(180);
        nodesList.setLayoutX(940);
        nodesList.setLayoutY(690);
    }

    public void clearPlusButton() {
        nodesList = new JFXNodesList();
    }

    /**
     * Giuliano transportPopup method
     */

    public void transportButtonAction(MouseEvent event) {

        VBox vBox = new VBox();
        vBox.setPrefHeight(250.0);
        vBox.setPrefWidth(200.0);

        vBox.setStyle("-fx-background-color: white");

        PopOver popOver = new PopOver(vBox);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.show(ssbutton2);

    }

    /**
     * Giuliano foodPopup method
     */
    @FXML
    public void foodButtonAction(MouseEvent event) {

        loadActivity();
        mainScreenController = new MainScreenController();

        PopOver popOver = new PopOver(mainScreenController.getFoodWindow());
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.show(ssbutton3);

    }

    private void loadActivity() {

        //Clears everything in the observable list
        list.removeAll(list);

        String a = "Eating a vegetarian meal";
        String b = "Buying local produce";
        String c = "Svetoslav's stroopwafel";
        list.addAll(a,b,c);

        foodchoices.setItems(list);

//        mainPane.getChildren().add(foodchoices);
    }
    /**
     * Giuliano energyPopup method
     */
    public void energyButtonAction(MouseEvent event) {

        VBox vBox = new VBox();
        vBox.setPrefHeight(250.0);
        vBox.setPrefWidth(200.0);

        vBox.setStyle("-fx-background-color: white");

        PopOver popOver = new PopOver(vBox);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.show(ssbutton4);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadActivity();
    }


    public JFXNodesList getNodesList() {
        return nodesList;
    }
}
