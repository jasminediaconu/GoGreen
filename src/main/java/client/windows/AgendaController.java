package client.windows;

import client.objects.Activity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXNodesList;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class provides functionality for:
 * - the agenda.fxml and all inner sub-screens.
 * - creates the plus button
 * - loads items into the activity popup dropdown menus
 *
 * @author gforghieri
 */
public class AgendaController implements Initializable {

    @FXML Pane agenda;
    @FXML ScrollPane scrollAgenda;
    @FXML FontAwesomeIcon delete;
    @FXML StackPane stack;
    @FXML private ComboBox<String> foodchoices = new ComboBox<>();

    private MainScreenController mainScreenController;
    private JFXButton ssbutton2;
    private JFXButton ssbutton3;
    private JFXButton ssbutton4;
    private ObservableList list = FXCollections.observableArrayList();
    private JFXNodesList nodesList;
    private GridPane gridPane;
    private Text dateText;
    private List<String> items;
    private List<Activity> activities;
    private VBox agendaBox;
    private JFXDialog dialog;

    /**
     * Constructor to be used in the MainScreenController.
     * To be able to use the variables, methods of this class.
     */
    public AgendaController() {
        nodesList = new JFXNodesList();
    }

    /**
     * This function will display a dialog message to the user when he want to delete an activity.
     * The dialog button contains a message and two buttons:
     * a close button and a delete one connected to the deleteActivity function.
     */
    private void deleteActivityDialog(int index) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        Text heading = new Text("Are you sure?");
        dialogLayout.setHeading(heading);
        JFXButton del = new JFXButton("Delete");
        del.setStyle("-fx-background-color: #c82333; -fx-text-fill: white");
        JFXButton close = new JFXButton("Close");
        String css = "-fx-border-color:#95e743;-fx-border-radius:2;-fx-background-color:#ecffe6";
        close.setStyle(css);
        del.setOnMouseClicked(e -> deleteActivity(index));
        close.setOnMouseClicked(e -> dialog.close());
        String message = "You are about to delete the activity. Do you want to proceed?";
        dialogLayout.setBody(new Text(message), close, del);
        dialog = new JFXDialog(stack, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialogLayout.setActions(del, close);
        dialog.show();
        dialog.isOverlayClose();
    }

    /**
     * This function will delete the activities from the agenda.
     * @param rowIndex int type.
     */
    private void deleteActivity(int rowIndex) {
        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex);
        // If there are no activities for that day, delete the date
        agendaBox.getChildren().removeIf(dateText -> getRowCount(gridPane) == 0);
        dialog.close();
    }

    /**
     * This function will add activities to the agenda through the JFXNodeList.
     */
    private void addActivity() {

    }

    /**
     * Count the number of rows in a pane.
     * @param pane GridPane
     * @return numRows
     */
    private int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if (rowIndex != null) {
                    numRows = Math.max(numRows, rowIndex + 1);
                }
            }
        }
        return numRows;
    }

    /**
     * Initialize agenda with the user activities.
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadActivity();

        //activities = new ArrayList<>();
        //ClientUser.getActivityList();
        // activityID = activity.getActivityID();
        // amount = activity.getAmount();
        // date = activity.getDate();
        // System.out.println(activityID);
        // System.out.println(amount);
        // System.out.println(date);
        // System.out.println(activity);
        // activities.add(activity);

        agendaBox = new VBox();

        agendaBox.setPadding(new Insets(20, 0, 0, 20));

        gridPane = new GridPane();
        gridPane.setLayoutX(420);

        String css = "-fx-background-position: 20; -fx-font-size: 28;";
        dateText = new Text("Fri 9 Mar");
        // NEED TO PARSE THE LOCAL DATE FROM THE ACTIVITY CLASS

        dateText.setStyle(css);
        agendaBox.getChildren().add(dateText);

        String path = "/client/windows/images/delete.png";

        items = new ArrayList<>();

        items.add("Vegan meal");
        items.add("Local produce");
        items.add("Public transport");
        items.add("LEDs");

        for (int i = 0; i < items.size(); i++) {
            Text text = new Text(items.get(i));
            text.setWrappingWidth(310.00);
            gridPane.add(text, 1, i);
            JFXButton button = new JFXButton("", new ImageView(path));
            gridPane.add(button,2,i);
            int ii = i;
            button.setOnMouseClicked(e -> deleteActivityDialog(ii));
        }

        /*
         * SORT ACTIVITIES BY DATE, SAVE DIFFERENT DATES IN DIFFERENT TEXT OBJECTS.
         * SHOW ACTIVITIES SORTED BY DATE, SAVE THEM IN A GRID PANE.
         * CREATE A NEW DELETE BUTTON WERE TO STORE THE IMAGE FOR EACH ACTIVITY ADDED.
         * THE GRID PANE NEEDS TO HAVE THE ACTIVITY NAME AND THE DELETE BUTTON.
         */

        gridPane.setHgap(20);
        agendaBox.getChildren().add(gridPane);

//      scrollAgenda.setContent(agendaBox);
        agendaBox.setSpacing(15);

        JFXButton ssbutton5 = new JFXButton("R1");
        ssbutton5.setButtonType(JFXButton.ButtonType.RAISED);
    }

    /**
     * This code creates the GREEN animated PLUS button when agenda is selected.
     */

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

    /**
     * Clears the plus button of the previously added 3 subbuttons.
     * So when the user loads the agenda page again only 3 buttons shop instead of many multiplies.
     */

    public void clearPlusButton() {
        nodesList = new JFXNodesList();
    }

    /**
     * Creates an empty white popup box for transportation button popup.
     * To be finished.
     */

    public void transportButtonAction(javafx.scene.input.MouseEvent event) {
        VBox vBox = new VBox();
        vBox.setPrefHeight(250.0);
        vBox.setPrefWidth(200.0);

        vBox.setStyle("-fx-background-color: white");

        PopOver popOver = new PopOver(vBox);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.show(ssbutton2);

    }

    /**
     * This method appends the foodWindow.fxml to the
     */
    @FXML
    public void foodButtonAction(javafx.scene.input.MouseEvent event) {
        loadActivity();
        mainScreenController = new MainScreenController();

        PopOver popOver = new PopOver(mainScreenController.getFoodWindow());
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.show(ssbutton3);

    }

    /**
     * Loads the food dropdown menu with items.
     */

    private void loadActivity() {

        //Clears everything in the observable list
        list.removeAll(list);

        String first = "Eating a vegetarian meal";
        String second = "Buying local produce";
        String third = "Svetoslav's stroopwafel";
        list.addAll(first, second, third);

        foodchoices.setItems(list);
        //mainScreen.getChildren().add(foodchoices);
    }

    /**
     * applyButton event
     * agendatext should be Jasmine's agendabox
     */
    @FXML
    void applyButton(MouseEvent event) {
        String activity = foodchoices.getValue();

//        if(activity==null) {
//            agendaBox.setText("PLease select a valid item.");
//        }
//        else {
//            agendatex.setText("Test" + activity);
//        }
  }


    /**
     * Creates an empty white popup box for energy button popup.
     * To be finished.
     */

    public void energyButtonAction(javafx.scene.input.MouseEvent event) {

        VBox vBox = new VBox();
        vBox.setPrefHeight(250.0);
        vBox.setPrefWidth(200.0);

        vBox.setStyle("-fx-background-color: white");

        PopOver popOver = new PopOver(vBox);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.show(ssbutton4);

    }

    public JFXNodesList getNodesList() {
        return nodesList;
    }

}
