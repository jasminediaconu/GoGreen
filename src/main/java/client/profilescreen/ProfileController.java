package client.profilescreen;

import client.Main;
import client.ServerRequests;
import client.user.ClientUser;
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
import java.util.function.UnaryOperator;

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
    private JFXToggleButton leds;
    @FXML
    private JFXToggleButton solarPanels;
    @FXML
    private JFXTextField tempratureField;
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
        pointsField.setText("CO2 saved: " + Main.round(Main.clientUser.getTotalCo2(), 2));
        streakField.setText("Streak: " + Main.clientUser.getStreakLength());
        solarPanels.setSelected(settings.hasSolarPower());
        leds.setSelected(settings.hasLeds());
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
        if (leds.isFocused()) {
            newSettings.setLeds(leds.isSelected());
        } else if (solarPanels.isFocused()) {
            newSettings.setSolarPower(solarPanels.isSelected());
        } else if (discardButton.isFocused()) {
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
            newSettings.setEmail(emailField.getText());
            newSettings.setCountry(countryField.getText());
            newSettings.setRoomTemp(Integer.parseInt(tempratureField.getText()));

            checkNewSettings();
            profileImage.requestFocus();
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
        leds.setDisable(disable);
        solarPanels.setDisable(disable);
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
}
