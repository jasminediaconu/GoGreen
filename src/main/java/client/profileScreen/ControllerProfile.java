package client.profileScreen;

import client.Main;
import client.user.Car;
import com.jfoenix.controls.JFXComboBox;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.awt.image.BufferedImage;

/**
 * The type Controller profile.
 */
public class ControllerProfile {

    @FXML
    private javafx.scene.control.Button imageButton;
    @FXML
    private Text usernameField;
    @FXML
    private Text pointsField;
    @FXML
    private Circle profileImage;
    @FXML
    private Text countryField;
    @FXML
    private Text averageField;
    @FXML
    private JFXComboBox carTypeField;
    @FXML
    private JFXComboBox emissionTypeField;


    /**
     * Instantiates a new Controller profile.
     */
    public ControllerProfile() {

    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        LoadClientProfile loadClientProfile = new LoadClientProfile(this);
        loadClientProfile.setDaemon(false);
        loadClientProfile.execute();
    }

    /**
     * When the change profile button is pressed the user can chose a file.
     */
    @FXML
    private void buttonPressed() {
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

    /**
     * Sets username field.
     *
     * @param username the username
     */
    public void setUsernameField(String username) {
        usernameField.setText("Username: " + username);
    }

    /**
     * Sets points field.
     *
     * @param points the points
     */
    public void setPointsField(double points) {
        pointsField.setText("CO2 Saved: " + points);
    }


    /**
     * Sets the dedicated car fields in the profile screen.
     *
     * @param carType      the car type
     * @param emissionType the emission type
     */
    public void setCarFields(String carType, String emissionType) {
        carTypeField.getSelectionModel().select(Car.getCarIndex(carType));
        System.out.println(Car.getEmissionIndex(emissionType));
        emissionTypeField.getSelectionModel().select(Car.getEmissionIndex(emissionType));
    }

    /**
     * Sets country field.
     *
     * @param country the country
     */
    public void setCountryField(String country) {
        countryField.setText("Country: " + country);
    }

    /**
     * Sets average field.
     *
     * @param average the average
     */
    public void setAverageField(String average) {
        averageField.setText("Average: " + average);
    }

    /**
     * Sets profile image.
     *
     * @param image the image
     */
    public void setProfileImage(Image image) {
        profileImage.setFill(new ImagePattern(image));
    }


}
