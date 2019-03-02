package client.profileScreen;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 */
public class ControllerProfile implements ProfileListener {

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
    private ImageView profileImage;


    public ControllerProfile() {

    }


    @FXML
    private void initialize() {
        Profile.init(this);
    }

    @FXML
    private void buttonPressed() {
    }

    /*
     * When the profile is retrieved from the server this function will be executed
     * This function inits all the fields from the fxml
     */
    @Override
    public void onProfileLoaded() {
        usernameField.setText("UserName: " + Profile.getUserName());
        pointsField.setText("Score: " + Profile.getPoints());
        carField.setText("Car: " + Profile.getCar());
        countryField.setText("Country: " + Profile.getCountry());
        averageField.setText("Average: " + Profile.getAverage());
        //profileImage.setImage(new Image( new SwingFXUtils(null, null)));

    }


}
