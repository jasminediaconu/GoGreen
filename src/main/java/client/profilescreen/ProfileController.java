package client.profilescreen;

import client.Main;
import client.ServerRequests;
import client.helper.RowCount;
import client.objects.Activity;
import client.objects.Item;
import client.user.ClientUser;
import client.windows.AgendaController;
import client.windows.Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * The type Controller profile.
 */
public class ProfileController extends Controller {

    ObservableList transportList = FXCollections.observableArrayList();

    String itemName = null;

    @FXML
    private javafx.scene.control.Button imageButton;
    @FXML
    private Text usernameField;
    @FXML
    private Text pointsField;
    @FXML
    private Text streakField;
    @FXML
    private Circle profileImage;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField countryField;
    @FXML
    private JFXComboBox transportField;
    @FXML
    private JFXTextField ledsField;
    @FXML
    private JFXTextField solarPanelsField;
    @FXML
    private JFXTextField temperatureField;
    @FXML
    private JFXButton discardButton;
    @FXML
    private JFXButton saveButton;

    private ClientUser newSettings;

    /**
     * Instantiates a new Controller profile.
     */
    public ProfileController() {

    }

    @Override
    public void update() {

        if (Main.clientUser == null) {
            return;
        }

        newSettings = Main.clientUser.deepCopy();
        syncUI(Main.clientUser);
    }

    private void syncUI(ClientUser settings) {
        usernameField.setText("Username: " + settings.getUsername());
        emailField.setText(settings.getEmail());
        pointsField.setText("CO2 saved: " + Main.round(Main.clientUser.getTotalCo2(), 2) + " kg");
        streakField.setText("Streak: " + Main.clientUser.getStreakLength());
        solarPanelsField.setText("" + Main.clientUser.getSolarPower());
        ledsField.setText("" + Main.clientUser.getLeds());
        countryField.setText(settings.getCountry());
        temperatureField.setText("" + settings.getRoomTemp());
        setButtonsDisable(true);
        setProfileImage(ClientUser.profileImage);
        setTransportField(settings.getCarType(), settings.getCarEmissionType());
        if (mainScreenController != null) {
            mainScreenController.setUsernameField(settings.getUsername());

            if (ClientUser.profileImage != null) {
                mainScreenController.setProfileImage(ClientUser.profileImage);
            }
        }
    }


    @Override
    public void init() {

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        temperatureField.setTextFormatter(textFormatter);
        textFormatter = new TextFormatter<String>(filter);
        ledsField.setTextFormatter(textFormatter);
        textFormatter = new TextFormatter<String>(filter);
        solarPanelsField.setTextFormatter(textFormatter);
        initTransportField();
        update();
        updateAgendaCaller();
    }

    /**
     * When the change profile button is pressed the user can chose a file.
     */
    @FXML
    private void buttonPressed() {
        if (discardButton.isFocused()) {
            discardChanges();
        } else if (saveButton.isFocused()) {
            saveChanges();
        } else if (imageButton.isFocused()) {
            chooseImage();
        }
        checkNewSettings();
    }

    @FXML
    private void comboBoxSelected() {
        String[] tranportTypes = transportField.getValue().toString().split(", ");
        if (tranportTypes.length > 1) {
            newSettings.setCarEmissionType(tranportTypes[0]);
            newSettings.setCarType(tranportTypes[1]);
        }
        checkNewSettings();
    }

    @FXML
    private void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.TAB)) {
            if (emailField.getText().length() > 0 && countryField.getText().length() > 0
                    && temperatureField.getText().length() > 0 && ledsField.getText().length() > 0
                    && solarPanelsField.getText().length() > 0) {
                newSettings.setEmail(emailField.getText());
                newSettings.setCountry(countryField.getText());
                newSettings.setRoomTemp(Integer.parseInt(temperatureField.getText()));
                newSettings.setLeds(Integer.parseInt(ledsField.getText()));
                newSettings.setSolarPower(Integer.parseInt(solarPanelsField.getText()));

                checkNewSettings();
                profileImage.requestFocus();
            }
        }
    }

    /**
     * Function to choose a profile image.
     */
    private void chooseImage() {
        ImageChooser imageChooser = new ImageChooser();
        BufferedImage image = imageChooser.getBufferedImage();
        changeProfileImage(image);
        ServerRequests sv = new ServerRequests();
        sv.uploadProfileImage(image);
    }

    /**
     * Function to change the profile image.
     *
     * @param image profileImage
     */
    public void changeProfileImage(BufferedImage image) {
        if (image != null) {
            ClientUser.profileImage = SwingFXUtils.toFXImage(image, null);
            setProfileImage(ClientUser.profileImage);
            mainScreenController.setProfileImage(ClientUser.profileImage);
            setButtonsDisable(false);
        }
    }


    private void discardChanges() {
        newSettings = Main.clientUser.deepCopy();
        update();
    }

    private void saveChanges() {
        ServerRequests sv = new ServerRequests();
        Main.clientUser = newSettings.deepCopy();
        setButtonsDisable(true);
        sv.updateClientUserProfile();
        update();
        updateAgendaCaller();
    }


    /**
     * This method checks if the activity is present today if not apply it.
     * The method also checks if the amount on the profilepage matches the agenda's activity,
     * if not update it
     *
     * @param itemName name of the item
     * @param amount   amount of the item
     */
    public void updateAgenda(String itemName, double amount) {

        AgendaController agendaController = new AgendaController();

        Boolean isPresent = false;

        // If itemName(solar panel, leds, lower temp) matches then dont apply on agenda
        for (Activity activity : Main.clientUser.getFilteredList()) {
            Item item = Main.items.get(activity.getItemID() - 1);
            if ((item.getName().equals(itemName)) && !isPresent) {
                isPresent = true;

                // if activity present check if amount is the same if not update it
                if (activity.getAmount() != amount) {
                    int activityIndex = Main.clientUser.getActivityList().indexOf(activity);
                    ServerRequests sv = new ServerRequests();
                    int activityID = Main.clientUser.getActivityList().get(
                            activityIndex).getActivityID();
                    sv.removeActivity(activityID);

                    AgendaController.getGridPane().getChildren().removeIf(node ->
                            GridPane.getRowIndex(node) == activityIndex + 2);
                    // If there are no activities for that day, delete the date
                    AgendaController.getAgendaBox().getChildren().removeIf(dateText ->
                            RowCount.getRowCount(AgendaController.getGridPane()) == 0);

                    decreaseTotalCO2(activity);
                    Main.clientUser.removeFromActivityList(activity);


                    applyActivity(itemName, amount);
                }
            }
        }

        if (!isPresent) {
            applyActivity(itemName, amount);
        }
        // if user adds solarpanel, led, temperature on agenda
        // update it on the userprofile and save it.
    }

    /**
     * Update the agenda with the text fields from Profile.
     */
    public void updateAgendaCaller() {
        if (solarPanelsField.getText() != null && solarPanelsField.getText().length() > 0) {
            updateAgenda("Solar panel", Double.parseDouble(solarPanelsField.getText()));
        }
        if (temperatureField.getText() != null && temperatureField.getText().length() > 0) {
            updateAgenda("Lower temperature", 21 - Double.parseDouble(temperatureField.getText()));
        }
        if (ledsField.getText() != null && ledsField.getText().length() > 0) {
            updateAgenda("LEDs", Double.parseDouble(ledsField.getText()));
        }
    }

    private void checkNewSettings() {
        setButtonsDisable(newSettings.equals(Main.clientUser));
    }

    private void setButtonsDisable(Boolean disable) {
        discardButton.setDisable(disable);
        saveButton.setDisable(disable);
    }

    /**
     * Sets page disable.
     *
     * @param disable the disable
     */
    public void setPageDisable(boolean disable) {
        emailField.setDisable(disable);
        countryField.setDisable(disable);
        transportField.setDisable(disable);
        ledsField.setDisable(disable);
        solarPanelsField.setDisable(disable);
        temperatureField.setDisable(disable);
    }


    /**
     * Sets the dedicated car fields in the profile screen.
     *
     * @param carType      the car type
     * @param emissionType the emission type
     */
    public void setTransportField(String carType, String emissionType) {
        Object object = transportList.filtered(e ->
                e.toString().equals(emissionType + ", " + carType)).get(0);
        transportField.getSelectionModel().select(object);

    }

    private void initTransportField() {

        if (transportList.size() < 1) {
            transportList.addAll(Main.items.stream().filter(item ->
                    item.getType().equals("transport")).map(item ->
                    item.getName()).collect(Collectors.toList()));
            transportList = transportList.filtered(e -> e.toString().split(", ").length > 1);
            transportField.setItems(transportList);
            transportField.getSelectionModel().clearSelection();
        }
    }


    /**
     * Sets profile image.
     *
     * @param image the image
     */
    public void setProfileImage(Image image) {
        if (image != null) {
            profileImage.setFill(new ImagePattern(image));
        }
    }

    /**
     * applyActivity event.
     * Applies the activity to the agenda
     */
    @FXML
    private void applyActivity(String itemName, double amount) {

        AgendaController agendaController = new AgendaController();

        ServerRequests sv = new ServerRequests();
        double parsedAmount = amount;
        LocalDate date = LocalDate.now();

        if (itemName != null && parsedAmount > 0) {
            int itemID = Main.items.stream().filter(x ->
                    x.getName().equals(itemName)).collect(Collectors.toList()).get(0).getItemID();
            Activity activity = new Activity(itemID, parsedAmount, date);
            if (sv.addActivity(activity)) {
                Main.clientUser.addToActivityList(activity);

                increaseTotalCO2(activity);

                agendaController.showAgendaActivities(
                        agendaController.activityMap(Main.clientUser.getActivityList()));
            }
        }
    }

    private void increaseTotalCO2(Activity activity) {
        ServerRequests sv = new ServerRequests();
        Main.clientUser.increaseTotalCo2(findValueFromActivity(activity));
        sv.updateClientUserProfile();
    }

    private void decreaseTotalCO2(Activity activity) {
        ServerRequests sv = new ServerRequests();
        Main.clientUser.increaseTotalCo2(-findValueFromActivity(activity));
        sv.updateClientUserProfile();
    }

    private double findValueFromActivity(Activity activity) {
        Item item = Main.items.get(activity.getItemID() - 1);
        double value = Main.round(activity.getAmount() * item.getCo2(), 2);
        if (item.getType().equals("food")) {
            value /= 1000.0;
        }
        return value;
    }
}
