package client.windows;

import client.helper.RowCount;
import client.Main;
import client.ServerRequests;
import client.objects.Activity;
import client.objects.Item;
import client.user.ClientUser;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXNodesList;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * This class provides functionality for:
 * - the agenda.fxml and all inner sub-screens.
 * - creates the plus button
 * - loads items into the activity popup dropdown menus
 *
 * @author gforghieri
 */
public class AgendaController extends Controller implements Initializable {

    @FXML
    Pane agenda;
    @FXML
    FontAwesomeIcon delete;
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
    private JFXButton ssbutton2;
    private JFXButton ssbutton3;
    private JFXButton ssbutton4;
    private ObservableList foodList = FXCollections.observableArrayList();
    private ObservableList transportList = FXCollections.observableArrayList();
    private ObservableList energyList = FXCollections.observableArrayList();
    private JFXNodesList nodesList;

    private GridPane gridPane;
    private Text dateText;
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


        agendaBox = new VBox();
        agendaBox.setPadding(new Insets(20, 0, 0, 20));

        gridPane = new GridPane();
        gridPane.setLayoutX(420);

        if (Main.clientUser == null) {
            Main.clientUser = new ClientUser();
        }
        if (Main.clientUser.getActivityList() != null) {
            Multimap<LocalDate, Activity> activityMap = activityMap(Main.clientUser.getActivityList());
            showAgendaActivities(activityMap);
        }

        gridPane.setHgap(20);
//        agendaBox.getChildren().add(gridPane);
        scrollAgenda.setContent(agendaBox);
        agendaBox.setSpacing(15);

        JFXButton ssbutton5 = new JFXButton("R1");
        ssbutton5.setButtonType(JFXButton.ButtonType.RAISED);
    }

    @Override
    public void init() {
        loadPlusButton();
        pane.getChildren().add(nodesList);
    }

    /**
     * This function will display a dialog message to the user when he wants to delete an activity.
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
     *
     * @param rowIndex int type.
     */
    private void deleteActivity(int rowIndex) {
        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex);
        // If there are no activities for that day, delete the date
        agendaBox.getChildren().removeIf(dateText -> RowCount.getRowCount(gridPane) == 0);
        dialog.close();
    }

    private Multimap<LocalDate, Activity> activityMap(List<Activity> activities) {
        Multimap<LocalDate, Activity> multimap = ArrayListMultimap.create();
        for (Activity a : activities) {
            multimap.put(a.getDate(), a);
        }
        return multimap;
    }

    /**
     * This function shows the activity on the agenda
     * Takes in a multimap(date from datepicker and activity object)
     * Wout is still looking into how to show the activity instantly after adding
     * without reloading the app
     * @param activityMap
     */
    private void showAgendaActivities(Multimap<LocalDate, Activity> activityMap) {
        agendaBox.getChildren().removeAll();


        gridPane = new GridPane();
        gridPane.setLayoutX(420);

        String path = "/client/windows/images/delete.png";

        int counter = 0;
        for (LocalDate date : activityMap.keySet()) {
            String css = "-fx-background-position: 20; -fx-font-size: 28;";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyy");
            dateText = new Text(formatter.format(date));

            dateText.setStyle(css);
            gridPane.add(dateText, 1, counter);
            counter++;

            for (Activity activity : activityMap.get(date)) {
                Item item = Main.items.get(activity.getItemID()-1);
                Text text = new Text(item.getName() + ", co2: " + round((item.getCo2()*activity.getAmount())/1000, 2));
                text.setWrappingWidth(310.00);
                gridPane.add(text, 1, counter);
                JFXButton button = new JFXButton("", new ImageView(path));
                gridPane.add(button, 2, counter);
                int ii = counter;
                button.setOnMouseClicked(e -> deleteActivityDialog(ii));
                counter++;
            }
        }

        gridPane.setHgap(20);
        agendaBox.getChildren().add(gridPane);
    }
    /**
     * This function rounds a double value to N decimal places.
     * @param value double type
     * @param places int type
     * @return a double rounded down to N decimal places
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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
        loadTransportItems();

        try {
            transportWindow = FXMLLoader.load(getClass().getResource("/client/windows/fxml/transportWindow.fxml"));
        } catch (IOException e) {}


        PopOver popOver = new PopOver(transportWindow);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.show(ssbutton2);
    }

//        VBox vBox = new VBox();
//        vBox.setPrefHeight(250.0);
//        vBox.setPrefWidth(200.0);
//
//        vBox.setStyle("-fx-background-color: white");
//
//        PopOver popOver = new PopOver(vBox);
//        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
//        popOver.show(ssbutton2);

    /**
     * This method appends the foodWindow.fxml to the 3rd node i.e. the foodButton
     */
    @FXML
    public void foodButtonAction(javafx.scene.input.MouseEvent event) {

        try {
            foodWindow = FXMLLoader.load(getClass().getResource("/client/windows/fxml/foodWindow.fxml"));
        } catch (IOException e) {}


        PopOver popOver = new PopOver(foodWindow);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.show(ssbutton3);
    }

    /**
     * Loads the food dropdown menu with items.
     */

    private void loadFoodItems() {

        //Clears everything in the observable list
        if (foodList.size() < 1)
            foodList.addAll(Main.items.stream().filter(item -> item.getType().equals("food")).map(item -> item.getName()).collect(Collectors.toList()));

        foodChoices.setItems(foodList);
        //mainScreen.getChildren().add(foodChoices);
    }

    /**
     * Loads the transport dropdown menu with items from the database
     *
     */
    private void loadTransportItems() {

        //Clears everything in the observable list
        if (transportList.size() < 1)
            transportList.addAll(Main.items.stream().filter(item -> item.getType().equals("transport")).map(item -> item.getName()).collect(Collectors.toList()));

        transportChoices.setItems(transportList);
        //mainScreen.getChildren().add(foodChoices);

    }



    /**
     * Creates an empty white popup box for energy button popup.
     * To be finished.
     */

    public void energyButtonAction(javafx.scene.input.MouseEvent event) {
        try {
            energyWindow = FXMLLoader.load(getClass().getResource("/client/windows/fxml/energyWindow.fxml"));
        } catch (IOException e) {}


        PopOver popOver = new PopOver(energyWindow);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.show(ssbutton4);

//        VBox vBox = new VBox();
//        vBox.setPrefHeight(250.0);
//        vBox.setPrefWidth(200.0);
//
//        vBox.setStyle("-fx-background-color: white");
//
//        PopOver popOver = new PopOver(vBox);
//        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
//        popOver.show(ssbutton4);

    }

    /**
     * Loads the energy dropdown menu with items from the database
     */

    private void loadEnergyItems() {

        //Clears everything in the observable list
        if (energyList.size() < 1)
            energyList.addAll(Main.items.stream().filter(item -> item.getType().equals("energy")).map(item -> item.getName()).collect(Collectors.toList()));

        energyChoices.setItems(energyList);
        //mainScreen.getChildren().add(foodChoices);
    }

    /**
     * applyButton event
     * Applies the activity to the agenda, still needs a restart of the application
     */
    @FXML
    void applyButton(MouseEvent event) {
        String itemName = foodChoices.getValue();
        ServerRequests sv = new ServerRequests();
        double parsedAmount = Double.parseDouble(amount.getText());
        LocalDate date = datepicker.getValue();

        if (itemName != null && parsedAmount > 0 && date != null) {
            System.out.println(date.toString());
            int itemID = Main.items.stream().filter(x -> x.getName().equals(itemName)).collect(Collectors.toList()).get(0).getItemID();
            Activity activity = new Activity(itemID, parsedAmount, date);
            if (sv.addActivity(activity)) {
                Main.clientUser.addToActivityList(activity);
                //refresh agenda

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

