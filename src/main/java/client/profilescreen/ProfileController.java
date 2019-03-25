package client.profilescreen;

import client.Main;
import client.ServerRequests;
import client.objects.Activity;
import client.objects.Item;
import client.user.ClientUser;
import client.windows.AgendaController;
import client.windows.Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * The type Controller profile.
 */
public class ProfileController extends Controller {

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
    private JFXComboBox carTypeField;
    @FXML
    private JFXComboBox emissionTypeField;
    @FXML
    private JFXTextField ledsField;
    @FXML
    private JFXTextField solarPanelsField;
    @FXML
    private JFXTextField tempratureField;
    @FXML
    private JFXButton discardButton;
    @FXML
    private JFXButton saveButton;

    private ClientUser newSettings;

    String itemName = null;


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
        pointsField.setText("CO2 saved: " + Main.round(Main.clientUser.getTotalCo2(), 2));
        streakField.setText("Streak: " + Main.clientUser.getStreakLength());
        solarPanelsField.setText("" + Main.clientUser.getSolarPower());
        ledsField.setText("" + Main.clientUser.getLeds());
        countryField.setText(settings.getCountry());
        tempratureField.setText("" + settings.getRoomTemp());
        setButtonsDisable(true);
        setProfileImage(settings.getProfileImage());
        setCarFields(settings.getCarType(), settings.getCarEmissionType());
        if (mainScreenController != null) {
            mainScreenController.setUsernameField(settings.getUsername());

            if (settings.getProfileImage() != null) {
                mainScreenController.setProfileImage(settings.getProfileImage());
            }
        }
    }


    /**
     * Initialize.
     */
    @FXML
    public void initialize() {

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        tempratureField.setTextFormatter(textFormatter);
        textFormatter = new TextFormatter<String>(filter);
        ledsField.setTextFormatter(textFormatter);
        textFormatter = new TextFormatter<String>(filter);
        solarPanelsField.setTextFormatter(textFormatter);
    }

    @Override
    public void init() {
        update();
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
        if (carTypeField.isFocused()) {
            String[] carType = carTypeField.getValue().toString().split("'");
            if (carType.length == 1) {
                newSettings.setCarType(carType[0]);
            } else {
                newSettings.setCarType(carType[1]);
            }
        } else if (emissionTypeField.isFocused()) {
            String[] carEmissionType = emissionTypeField.getValue().toString().split("'");
            if (carEmissionType.length == 1) {
                newSettings.setCarEmissionType(carEmissionType[0]);
            } else {
                newSettings.setCarEmissionType(carEmissionType[1]);
            }
        }

        checkNewSettings();
    }

    @FXML
    private void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.TAB)) {
            if (emailField.getText().length() > 0 && countryField.getText().length() > 0
                    && tempratureField.getText().length() > 0 && ledsField.getText().length() > 0
                    && solarPanelsField.getText().length() > 0) {
                newSettings.setEmail(emailField.getText());
                newSettings.setCountry(countryField.getText());
                newSettings.setRoomTemp(Integer.parseInt(tempratureField.getText()));
                newSettings.setLeds(Integer.parseInt(ledsField.getText()));
                newSettings.setSolarPower(Integer.parseInt(solarPanelsField.getText()));

                checkNewSettings();
                profileImage.requestFocus();
            }
        }
    }

    private void chooseImage() {
        ImageChooser imageChooser = new ImageChooser();
        BufferedImage image = imageChooser.getBufferedImage();
        if (image != null) {
            Main.clientUser.setProfileImage(SwingFXUtils.toFXImage(image, null));
            setProfileImage(Main.clientUser.getProfileImage());
            mainScreenController.setProfileImage(Main.clientUser.getProfileImage());
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

        // if solarpanel amount changed call updateAgenda ("Solar panel", solarpanel.getamount)
        // if led amount changed call updateAgenda("LEDs", led.getamount)
        // if tempereate changed call updateAgenda("Lower temperature, tepmpratorfield.getamount")

    }

    private void updateAgenda (String itemName, int amount){
        // Check the activities of today, if there is no solarpanel add the amount which is in the solarpanel
        // amount textfield to the agenda of today when the user clicks save.
        // Also do this when the user logs in for solarpanels, leds, temperature
        ArrayList<Activity> filteredActivities = new ArrayList<>();

        // Filter activities by today's date
        for (Activity activity : Main.clientUser.getActivityList()) {
            if (activity.getDate().equals(java.time.LocalDate.now()));
                filteredActivities.add(activity);
     }

        // If from today's activities the name of the item is not on the agenda or the amount is different
        for (Activity activity : filteredActivities) {
            Item item = Main.items.get(activity.getItemID() - 1);
            if ((!(item.getName().equals(itemName)))) {
                // add the item to the agenda
            }
            // the amount is different
            else if (activity.getAmount() != amount) {
             // update the amount of the activity on the agenda
            }
        }

     // if user adds solarpanel, led, temperature on agenda update it on the userprofile and save it.
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
        carTypeField.setDisable(disable);
        emissionTypeField.setDisable(disable);
        ledsField.setDisable(disable);
        solarPanelsField.setDisable(disable);
        tempratureField.setDisable(disable);
    }


    /**
     * Sets the dedicated car fields in the profile screen.
     *
     * @param carType      the car type
     * @param emissionType the emission type
     */
    public void setCarFields(String carType, String emissionType) {

        Label car = (Label) carTypeField.getItems().filtered(e ->
                ((Label) e).getText().equals(carType)).get(0);
        Label emission = (Label) emissionTypeField.getItems().filtered(e ->
                ((Label) e).getText().equals(emissionType)).get(0);
        carTypeField.getSelectionModel().select(car);
        emissionTypeField.getSelectionModel().select(emission);
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

//    /**
//     * applyButton event.
//     * Applies the activity to the agenda
//     */
//    @FXML
//    private void applyActivity(String itemName) {


//        ServerRequests sv = new ServerRequests();
//        double parsedAmount = -1;
//        if (amount.getText() != null && amount.getText().length() > 0) {
//            parsedAmount = Double.parseDouble(amount.getText());
//        }
//
//        LocalDate date = java.time.LocalDate.now();
//
//        if (itemName != null && parsedAmount > 0 && date != null) {
//            System.out.println(date.toString());
//            int itemID = Main.items.stream().filter(x ->
//                    x.getName().equals(itemName)).collect(Collectors.toList()).get(0).getItemID();
//            Activity activity = new Activity(itemID, parsedAmount, date);
//            if (sv.addActivity(activity)) {
//                Main.clientUser.addToActivityList(activity);
//                showAgendaActivities(activityMap(Main.clientUser.getActivityList()));
//            }
//        }
//    }


}
