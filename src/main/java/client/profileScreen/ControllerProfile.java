package client.profileScreen;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

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
    private Text carField;
    @FXML
    private Text countryField;
    @FXML
    private Text averageField;
    @FXML
    private Circle profileImage;


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
        RequestProfileTask task = new RequestProfileTask(this);
        task.setDaemon(false);
        task.execute();
    }

    @FXML
    private void buttonPressed() {

    }

    /**
     * Gets username field.
     *
     * @return the username field
     */
    public Text getUsernameField() {
        return usernameField;
    }

    /**
     * Gets points field.
     *
     * @return the points field
     */
    public Text getPointsField() {
        return pointsField;
    }

    /**
     * Gets car field.
     *
     * @return the car field
     */
    public Text getCarField() {
        return carField;
    }

    /**
     * Gets country field.
     *
     * @return the country field
     */
    public Text getCountryField() {
        return countryField;
    }

    /**
     * Gets average field.
     *
     * @return the average field
     */
    public Text getAverageField() {
        return averageField;
    }

    /**
     * Gets profile image.
     *
     * @return the profile image
     */
    public Circle getProfileImage() {
        return profileImage;
    }

    
}
