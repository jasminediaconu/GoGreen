package client.windows;

import client.Main;
import client.ServerRequests;
import client.helper.RowCount;
import client.objects.Activity;
import client.objects.Item;
import client.profilescreen.ProfileController;
import client.user.ClientUser;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXNodesList;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

//CHECKSTYLE:OFF
//CHECKSTYLE:ON

/**
 * This class provides functionality for:
 * - the agenda.fxml and all inner sub-screens.
 * - creates the plSus button
 * - loads items into the activity popup dropdown menus
 *
 * @author gforghieri
 */
public class AgendaController extends Controller implements Initializable {

    @FXML
    Pane agenda;
    @FXML
    private ScrollPane scrollAgenda = new ScrollPane();
    @FXML
    private StackPane stack;
    @FXML
    private ComboBox<String> foodChoices = new ComboBox<>();
    @FXML
    private ComboBox<String> energyChoices = new ComboBox<>();
    @FXML
    private ComboBox<String> transportChoices = new ComboBox<>();
    @FXML
    private TextField amount = new TextField();
    @FXML
    private DatePicker datepicker = new DatePicker();
    @FXML
    private Pane foodWindow;
    @FXML
    private Pane transportWindow;
    @FXML
    private Pane energyWindow;

    private MainScreenController mainScreenController;
    private JFXButton ssbutton1;
    private JFXButton ssbutton2;
    private JFXButton ssbutton3;
    private JFXButton ssbutton4;
    private ObservableList foodList = FXCollections.observableArrayList();
    private ObservableList transportList = FXCollections.observableArrayList();
    private ObservableList energyList = FXCollections.observableArrayList();
    private PopOver popOver1 = new PopOver();
    private PopOver popOver2 = new PopOver();
    private PopOver popOver3 = new PopOver();

    public PopOver getPopOver1() {
        return popOver1;
    }

    public PopOver getPopOver2() {
        return popOver2;
    }

    public PopOver getPopOver3() {
        return popOver3;
    }

    private String itemName;
    private Text dateText;
    private JFXNodesList nodesList;
    private static JFXDialog dialog;
    private static StackPane stackPane;

    private static GridPane gridPane;
    private static VBox agendaBox;

    public static GridPane getGridPane() {
        return gridPane;
    }

    public static VBox getAgendaBox() {
        return agendaBox;
    }

    /**
     * Constructor to be used in the MainScreenController.
     * To be able to use the variables, methods of this class.
     */
    public AgendaController() {
        nodesList = new JFXNodesList();
    }

    /**
     * Initialize agenda with the user activities.
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFoodItems();
        loadTransportItems();
        loadEnergyItems();
    }

    /**
     * This function is called only once when control reaches the MainScreenController.
     */
    @Override
    public void init() {
        loadPlusButton();
        pane.getChildren().add(nodesList);

        agendaBox = new VBox();
        agendaBox.setPadding(new Insets(20, 0, 0, 20));

        gridPane = new GridPane();
        gridPane.setLayoutX(420);

        if (Main.clientUser == null) {
            Main.clientUser = new ClientUser();
        }
        if (Main.clientUser.getActivityList() != null) {
            Multimap<LocalDate, Activity> activityMap =
                    activityMap(Main.clientUser.getActivityList());
            showAgendaActivities(activityMap);
        }

        gridPane.setHgap(20);
        //        agendaBox.getChildren().add(gridPane);

        agendaBox.getChildren().add(gridPane);

        scrollAgenda.setContent(agendaBox);
        agendaBox.setSpacing(15);


        JFXButton ssbutton5 = new JFXButton("R1");
        ssbutton5.setButtonType(JFXButton.ButtonType.RAISED);

        stackPane = stack;

    }

    /**
     * This function will display a dialog message to the user when he wants to delete an activity.
     * The dialog button contains a message and two buttons:
     * a close button and a delete one connected to the deleteActivity function.
     */
    private void deleteActivityDialog(int activityIndex, int rowCounter) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        Text heading = new Text("Are you sure?");
        dialogLayout.setHeading(heading);
        JFXButton del = new JFXButton("Delete");
        del.setStyle("-fx-background-color: #c82333; -fx-text-fill: white");
        JFXButton close = new JFXButton("Close");
        String css = "-fx-border-color:#95e743;-fx-border-radius:2;-fx-background-color:#ecffe6";
        close.setStyle(css);
        del.setOnMouseClicked(e -> deleteActivity(activityIndex, rowCounter));
        close.setOnMouseClicked(e -> dialog.close());
        String message = "You are about to delete the activity. Do you want to proceed?";
        dialogLayout.setBody(new Text(message), close, del);
        dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialogLayout.setActions(del, close);

        dialog.show();
    }

    /**
     * Function to delete an activity.
     *
     * @param activityIndex index of the activity
     * @param rowCounter    amount of rows
     */
    public void deleteActivity(int activityIndex, int rowCounter) {
        ServerRequests sv = new ServerRequests();
        int activityID = Main.clientUser.getActivityList().get(activityIndex).getActivityID();

        sv.removeActivity(activityID);

        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowCounter);
        // If there are no activities for that day, delete the date
        agendaBox.getChildren().removeIf(dateText -> RowCount.getRowCount(gridPane) == 0);
        dialog.close();
    }

    /**
     * Create a multimap with activities and dates.
     *
     * @param activities list of activities
     * @return multimap
     */
    public Multimap<LocalDate, Activity> activityMap(List<Activity> activities) {
        Multimap<LocalDate, Activity> multimap = ArrayListMultimap.create();
        for (Activity a : activities) {
            multimap.put(a.getDate(), a);
        }
        return multimap;
    }

    /**
     * This function shows the activity on the agenda.
     * Takes in a multimap(date from datepicker and activity object)
     *
     * @param activityMap Multimap type.
     */
    public void showAgendaActivities(Multimap<LocalDate, Activity> activityMap) {
        gridPane.getChildren().clear();

        String path = "/client/windows/images/delete.png";

        int rowCounter = 0;
        int activityCounter = 0;
        for (LocalDate date : activityMap.keySet()) {
            if (rowCounter > 0) {
                rowCounter++;
            }
            String css = "-fx-background-position: 20; -fx-font-size: 28;";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyy");
            dateText = new Text(formatter.format(date));

            dateText.setStyle(css);
            gridPane.add(dateText, 1, rowCounter);
            for (Activity activity : activityMap.get(date)) {
                rowCounter++;
                Item item = Main.items.get(activity.getItemID() - 1);
                String unit = "";
                Double co2Saved = Main.round(item.getCo2() * activity.getAmount(), 2);


                if (item.getType().equals("food")) {
                    unit = "g";
                    co2Saved = co2Saved / 1000;
                } else if (item.getType().equals("transport")) {
                    unit = "km";
                }
                Text text = new Text(item.getName() + ", amount: " + activity.getAmount()
                        + unit + ", CO2 saved: " + co2Saved + "kg");

                text.setWrappingWidth(310.00);
                gridPane.add(text, 1, rowCounter);
                JFXButton button = new JFXButton("", new ImageView(path));
                gridPane.add(button, 2, rowCounter);
                int ii = activityCounter++;
                int iii = rowCounter;
                button.setOnMouseClicked(e -> deleteActivityDialog(ii, iii));
            }
        }
    }

    /**
     * This code creates the GREEN animated PLUS button when agenda is selected.
     */

    public void loadPlusButton() {
        // This code creates the GREEN animated PLUS button when agenda is selected
        ssbutton1 = new JFXButton();
        ssbutton1.setId("plusbutton");
        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton1.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");

        nodesList.addAnimatedNode(ssbutton1, (expanded, duration) -> {
            List<KeyFrame> frames = new ArrayList<>();
            frames.add(new KeyFrame(duration,
                    new KeyValue(ssbutton1.rotateProperty(),
                            expanded ? 180 : 0, Interpolator.EASE_BOTH)));
            return frames;
        }
        );

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
     * Creates the transportation popup box.
     * To be finished.
     */
    public void transportButtonAction(javafx.scene.input.MouseEvent event) {
        loadTransportItems();
        String path = "/client/windows/fxml/transportWindow.fxml";
        try {
            transportWindow = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!(popOver1.isShowing())) {
            popOver1 = new PopOver(transportWindow);
            popOver1.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
            popOver1.setDetachable(false);
            popOver1.show(ssbutton2);

        }
    }

    /**
     * Loads the transport dropdown menu with items from the database.
     */
    private void loadTransportItems() {

        transportList.addAll("Walking",
                "By bike",
                "Public transport",
                "By car");
        transportChoices.setItems(transportList);
    }


    /**
     * This method appends the foodWindow.fxml to the 3rd node i.e. the foodButton
     */
    @FXML
    public void foodButtonAction(javafx.scene.input.MouseEvent event) {
        String path = "/client/windows/fxml/foodWindow.fxml";

        try {
            foodWindow = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!popOver2.isShowing()) {
            popOver2 = new PopOver(foodWindow);
            popOver2.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
            popOver2.setDetachable(false);
            popOver2.show(ssbutton3);
        }

    }


    /**
     * Loads the food dropdown menu with items.
     */
    private void loadFoodItems() {
        //Clears everything in the observable list
        if (foodList.size() < 1) {
            foodList.addAll(Main.items.stream().filter(item ->
                    item.getType().equals("food")).map(item ->
                    item.getName()).collect(Collectors.toList()));
        }
        foodChoices.setItems(foodList);
        //mainScreen.getChildren().add(foodChoices);
    }


    /**
     * Creates an empty white popup box for energy button popup.
     * To be finished.
     */
    public void energyButtonAction(javafx.scene.input.MouseEvent event) {
        String path = "/client/windows/fxml/energyWindow.fxml";

        try {
            energyWindow = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!popOver3.isShowing()) {
            popOver3 = new PopOver(energyWindow);
            popOver3.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
            popOver3.setDetachable(false);
            popOver3.show(ssbutton4);
        }
    }

    /**
     * Loads the energy dropdown menu with items from the database.
     */
    private void loadEnergyItems() {

        //Clears everything in the observable list
        if (energyList.size() < 1) {
            energyList.addAll(Main.items.stream().filter(item ->
                    item.getType().equals("energy")).map(item ->
                    item.getName()).collect(Collectors.toList()));
        }
        energyChoices.setItems(energyList);
        //mainScreen.getChildren().add(foodChoices);
    }

    @FXML
    void applyTransport(MouseEvent event) {
        itemName = transportChoices.getValue();
        if (itemName.equals("By car")) {
            itemName = Main.clientUser.getCarEmissionType() + ", " + Main.clientUser.getCarType();
        }
        applyButton(itemName);
    }

    @FXML
    void applyFood(MouseEvent event) {
        itemName = foodChoices.getValue();
        applyButton(itemName);
    }

    @FXML
    void applyEnergy(MouseEvent event) {
        itemName = energyChoices.getValue();
        applyButton(itemName);
    }


    /**
     * applyButton event.
     * Applies the activity to the agenda
     */
    @FXML
    private void applyButton(String itemName) {

        ProfileController profileController = new ProfileController();

        ServerRequests sv = new ServerRequests();
        double parsedAmount = -1;
        if (amount.getText() != null && amount.getText().length() > 0) {
            parsedAmount = Double.parseDouble(amount.getText());
        }

        LocalDate date = datepicker.getValue();

        if (itemName.equals("Solar panel")) {
            profileController.updateAgenda(itemName, parsedAmount);
            Main.clientUser.setSolarPower((int) parsedAmount);
            sv.updateClientUserProfile();
            showAgendaActivities(activityMap(Main.clientUser.getActivityList()));
            return;
        } else if (itemName.equals("Lower Temperature")) {
            profileController.updateAgenda(itemName, parsedAmount);
            Main.clientUser.setRoomTemp(21 - (int) parsedAmount);
            sv.updateClientUserProfile();
            showAgendaActivities(activityMap(Main.clientUser.getActivityList()));
            return;
        } else if (itemName.equals("LEDs")) {
            profileController.updateAgenda(itemName, parsedAmount);
            Main.clientUser.setLeds((int) parsedAmount);
            sv.updateClientUserProfile();
            showAgendaActivities(activityMap(Main.clientUser.getActivityList()));
            return;
        }

        if (itemName != null && parsedAmount > 0 && date != null) {
            System.out.println(date.toString());
            int itemID = Main.items.stream().filter(x ->
                    x.getName().equals(itemName)).collect(Collectors.toList()).get(0).getItemID();
            Activity activity = new Activity(itemID, parsedAmount, date);
            if (sv.addActivity(activity)) {
                Main.clientUser.addToActivityList(activity);

                Item item = Main.items.get(activity.getItemID() - 1);
                double addition = activity.getAmount() * item.getCo2();
                if (!item.getType().equals("energy")) {
                    addition /= 1000.0;
                }
                Main.clientUser.increaseTotalCo2(addition);
                sv.updateClientUserProfile();

                showAgendaActivities(activityMap(Main.clientUser.getActivityList()));
            }
        }
    }

    public JFXNodesList getNodesList() {
        return nodesList;
    }

    @Override
    public void update() {

    }
}