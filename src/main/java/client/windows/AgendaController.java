package client.windows;

import client.ServerRequests;
import client.objects.Activity;
import client.user.ClientUser;
import client.user.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import server.helper.ActivityClass;

import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static client.ServerRequests.retrieveActivities;

public class AgendaController implements Initializable {

    @FXML ScrollPane scrollAgenda;
    @FXML FontAwesomeIcon delete;
    @FXML StackPane stack;

    ClientUser cu;
    User user;
    ActivityClass act;
    Activity activity;
    int activityID;
    int itemID;
    double amount;
    String username;
    String country;
    String date;
    String carType;
    String carEmissionType;
    int streakLength;
    double totalCo2;
    boolean solarPower;
    boolean LEDs;
    int roomtemp;
    List<Activity> activities;
    GridPane gridPane;
    Text dateText;
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
    private void deleteActivityDialog() {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        Text heading = new Text("Are you sure?");
        heading.setStyle("-fx-text-fill: #95e743");
        dialogLayout.setHeading(heading);
        JFXButton del = new JFXButton("Delete");
        del.setStyle("-fx-background-color: #c82333; -fx-text-fill: white");
        JFXButton close = new JFXButton("Close");
        String css = "-fx-border-color:#95e743;-fx-border-radius:2 2 2 2;-fx-background-color:#ecffe6";
        close.setStyle(css);
        del.setOnMouseClicked(e -> deleteActivity());
        close.setOnMouseClicked(e -> dialog.close());
        String message = "You are about to delete the activity. Do you want to proceed?";
        dialogLayout.setBody(new Text(message), close, del);
        dialog = new JFXDialog(stack, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialogLayout.setActions(del, close);
        dialog.show();

    }

    /**
     * This function will delete the activities from the agenda.
     */
    private void deleteActivity() {
    }

    /**
     * Parse activities and load them on the agenda
     */
    private void loadActivity() {
        //while () {
        //}
    }

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        VBox agendaBox = new VBox();
        agendaBox.setPadding(new Insets(20, 0, 0, 20));
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

        button.setOnMouseClicked(e -> deleteActivityDialog());
        button2.setOnMouseClicked(e -> deleteActivityDialog());
        button3.setOnMouseClicked(e -> deleteActivityDialog());

        dateText.setStyle(css);
        agendaBox.getChildren().add(dateText);

        gridPane.setHgap(20);
        gridPane.add(a,1,1);
        gridPane.add(button,7,1);

        gridPane.add(b,1,2);
        gridPane.add(button2,7,2);

        gridPane.add(c,1,3);
        gridPane.add(button3,7,3);

        agendaBox.getChildren().add(gridPane);
       // agendaBox.getChildren().add();
        scrollAgenda.setContent(agendaBox);
        agendaBox.setSpacing(15);

        JFXButton ssbutton1 = new JFXButton("R1");
        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);

//        JFXButton ssbutton2 = new JFXButton("R2");
//        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
//
//        JFXButton ssbutton3 = new JFXButton("R3");
//        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
//
//        JFXNodesList nodesList = new JFXNodesList();

//        nodesList.addAnimatedNode(ssbutton1);
//        nodesList.addAnimatedNode(ssbutton2);
//        nodesList.addAnimatedNode(ssbutton3);
    }
}
