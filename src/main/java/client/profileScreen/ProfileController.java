package client.profileScreen;

import client.Main;
import client.user.Car;
import client.user.ClientUser;
import client.windows.Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
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
    
    private void syncUI(ClientUser settins) {
        setProfileImage(settins.getProfileImage());
        usernameField.setText("Username: " + settins.getUsername());
        emailField.setText(settins.getEmail());
        pointsField.setText("CO2 saved: " + Main.clientUser.getTotalCo2());
        streakField.setText("Streak: " + Main.clientUser.getStreakLength());
        setCarFields(settins.getCar().getCarType(), settins.getCar().getEmissionType());
        solarPanels.setSelected(settins.hasSolarPower());
        leds.setSelected(settins.hasLEDs());
        countryField.setText(settins.getCountry());
        tempratureField.setText("" + settins.getRoomTemp());
        System.out.println(settins.getCar().getCarName());
        setButtonsDisable(settins.equals(Main.clientUser));
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        setButtonsDisable(true);
        setPageDisable(true);

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        tempratureField.setTextFormatter(textFormatter);

        LoadClientProfile loadClientProfile = new LoadClientProfile(this);
        loadClientProfile.setDaemon(false);
        loadClientProfile.execute();
    }

    /**
     * When the change profile button is pressed the user can chose a file.
     */
    @FXML
    private void buttonPressed() {
        if (leds.isFocused()) {
            newSettings.setLEDs(leds.isSelected());
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
        Car car = newSettings.getCar();
        if (carTypeField.isFocused()) {
            car.setCarType(carTypeField.getSelectionModel().getSelectedIndex());
        } else {
            car.setEmissionType(emissionTypeField.getSelectionModel().getSelectedIndex());
        }
        SendProfileUpdate sendProfileImage = new SendProfileUpdate(car,
                SendProfileUpdate.CAR_UPDATE);
        sendProfileImage.setDaemon(true);
        sendProfileImage.execute();

        checkNewSettings();
    }

    @FXML
    private void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.TAB)) {
            if (emailField.isFocused()) {
                newSettings.setEmail(emailField.getText());
            } else if (countryField.isFocused()) {
                newSettings.setCountry(countryField.getText());
            } else if (tempratureField.isFocused()) {
                newSettings.setRoomTemp(Integer.parseInt(tempratureField.getText()));
            }
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

            SendProfileUpdate sendProfileImage = new SendProfileUpdate(image,
                    SendProfileUpdate.PROFILE_IMAGE_UPDATE);
            sendProfileImage.setDaemon(true);
            sendProfileImage.execute();
        }
    }

    private void discardChanges() {
        newSettings = Main.clientUser.deepCopy();
        setButtonsDisable(true);
        update();
    }

    private void saveChanges() {
        Main.clientUser = newSettings.deepCopy();
        setButtonsDisable(true);
        //todo send profile update
    }

    private void setButtonsDisable(Boolean disable) {
        discardButton.setDisable(disable);
        saveButton.setDisable(disable);
    }

    private void checkNewSettings() {
        setButtonsDisable(newSettings.equals(Main.clientUser));
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
    public void setCarFields(int carType, int emissionType) {
        carTypeField.getSelectionModel().select(carType);
        emissionTypeField.getSelectionModel().select(emissionType);
    }


    /**
     * Sets profile image.
     *
     * @param image the image
     */
    public void setProfileImage(Image image) {
        if (image != null) {
            profileImage.setFill(new ImagePattern(image));
            setButtonsDisable(false);
        }
    }
}
