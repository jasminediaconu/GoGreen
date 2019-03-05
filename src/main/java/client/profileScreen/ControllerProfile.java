package client.profileScreen;

import com.victorlaerte.asynctask.AsyncTask;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
    private ImageView profileImage;


    public ControllerProfile() {

    }

    @FXML
    private void buttonPressed() {
    }

    private class Task extends AsyncTask {
        @Override
        public void onPreExecute() {

        }

        @Override
        public Boolean doInBackground(Object... params) {
            return true;
        }

        @Override
        public void onPostExecute(Object params) {

        }

        @Override
        public void progressCallback(Object... params) {

        }
    }


}
