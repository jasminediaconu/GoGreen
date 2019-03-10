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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;

import java.net.URL;

import java.time.LocalDate;
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

    private MainScreenController mainScreenController;
    private JFXButton ssbutton2;
    private JFXButton ssbutton3;
    private JFXButton ssbutton4;
    private ObservableList list = FXCollections.observableArrayList();
    private JFXNodesList nodesList;

    @FXML private ComboBox<String> foodchoices = new ComboBox<>();

    /**
     * Constructor to be used in the MainScreenController
     * To be able to use the variables, methods of this class
     */

    public AgendaController() {
        nodesList = new JFXNodesList();
    }

    @FXML ScrollPane scrollAgenda;
    @FXML FontAwesomeIcon delete;
    @FXML StackPane stack;

    GridPane gridPane;
    Text dateText;
    List<Activity> activities;
    Activity activity;
    int activityID;
    int itemID;
    double amount;
    LocalDate date;
    VBox agendaBox;
    Text a;
    Text b;
    Text c;
    ImageView img, img2, img3;
    JFXButton button, button2, button3;
    JFXDialog dialog;

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
        String css = "-fx-border-color:#95e743;-fx-border-radius:2 2 2 2;-fx-background-color:#ecffe6";
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
     */
    private void deleteActivity(int rowIndex) {
        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex);
        // If there are no activities for that day, delete the date
        agendaBox.getChildren().removeIf(dateText -> getRowCount(gridPane) == 0);
        dialog.close();
    }

    /**
     * Count the number of rows in a pane.
     * @param pane
     * @return
     */
    private int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if(rowIndex != null){
                    numRows = Math.max(numRows,rowIndex+1);
                }
            }
        }
        return numRows;
    }

    /**
     * Initialize agenda with the user activities.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadActivity();

        activities = new ArrayList<>();
        activities.add(new Activity(1, 1, LocalDate.now()));
        activities.add(new Activity(2, 5, LocalDate.now()));
        activities.add(new Activity(3, 8, LocalDate.now()));

        int i = 0;

        while(activities.contains(activity)) {
            activity = activities.get(i);
            dateText = new Text(activity.getDate().toString());
            i++;
        }

        //ClientUser.getActivityList();
        //       .getActivityList();
        //System.out.println(user.getActivityList());
        //System.out.println(retrieveActivities(new String()));
        // act = new ActivityClass(activityID, itemID, amount, date);

        // activityID = activity.getActivityID();
        // amount = activity.getAmount();
        // date = activity.getDate();
        // System.out.println(activityID);
        // System.out.println(amount);
        // System.out.println(date);
        // System.out.println(activity);
        // activities.add(activity);
        // System.out.println(activities);

        String css = "-fx-background-position: 20; -fx-font-size: 28;";
        String css2 = "-fx-font-size: 18;";

        agendaBox = new VBox();
        agendaBox.setPadding(new Insets(20, 0, 0, 20));

        /*
         * while (activityList.hasNext() {
         *
         * PARSE THE ACTIVITY DATA AND STORE THEM IN NEW VARIABLES,
         * ONE FOR DATE AND ONE FOR THE NAME OF THE ACTIVITY.
         *
         * }
         *
         * SORT ACTIVITIES BY DATE, SAVE DIFFERENT DATES IN DIFFERENT TEXT OBJECTS.
         * SHOW ACTIVITIES SORTED BY DATE, SAVE THEM IN A GRID PANE.
         * CREATE A NEW DELETE BUTTON WERE TO STORE THE IMAGE FOR EACH ACTIVITY ADDED.
         * THE GRID PANE NEEDS TO HAVE THE ACTIVITY NAME AND THE DELETE BUTTON.
         *
         * */
        dateText = new Text("Fri 9 Mar");

        gridPane = new GridPane();
        a = new Text("Bought vegetarian meal");
        b = new Text("Ate vegan food");
        c = new Text("Cycled");
        a.setStyle(css2);
        b.setStyle(css2);
        c.setStyle(css2);

        String path = "/client/windows/images/delete.png";

        img = new ImageView(path);
        img2 = new ImageView(path);
        img3 = new ImageView(path);

        button = new JFXButton("", img);
        button2 = new JFXButton("", img2);
        button3 = new JFXButton("", img3);

        dateText.setStyle(css);
        agendaBox.getChildren().add(dateText);

        gridPane.setHgap(20);

        //for(int i=0; i<)
        gridPane.add(a,1,1);
        gridPane.add(button,7,1);

        gridPane.add(b,1,2);
        gridPane.add(button2,7,2);

        gridPane.add(c,1,3);
        gridPane.add(button3,7,3);

        button.setOnMouseClicked(e -> deleteActivityDialog(gridPane.getRowIndex(button)));
        button2.setOnMouseClicked(e -> deleteActivityDialog(gridPane.getRowIndex(button2)));
        button3.setOnMouseClicked(e -> deleteActivityDialog(gridPane.getRowIndex(button3)));

        agendaBox.getChildren().add(gridPane);


        // agendaBox.getChildren().add();
        scrollAgenda.setContent(agendaBox);
        agendaBox.setSpacing(15);

        JFXButton ssbutton1 = new JFXButton("R1");
        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
    }

    /**
     * This code creates the GREEN animated PLUS button when agenda is selected
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
     * Clears the plus button of the previously added 3 subbuttons
     * So when the user loads the agenda page again only 3 buttons shop instead of many multiplies
     */
    public void clearPlusButton() {
        nodesList = new JFXNodesList();
    }

    /**
     * Creates an empty white popup box for transportation button popup
     * To be finished
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
     * Loads the food dropdown menu with items
     */
    private void loadActivity() {

        //Clears everything in the observable list
        list.removeAll(list);

        String a = "Eating a vegetarian meal";
        String b = "Buying local produce";
        String c = "Svetoslav's stroopwafel";
        list.addAll(a, b, c);

        foodchoices.setItems(list);

//        mainPane.getChildren().add(foodchoices);
    }

    /**
     * Creates an empty white popup box for energy button popup
     * To be finished
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
