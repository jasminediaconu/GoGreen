package client.profileScreen;

import client.Main;
import client.user.ClientUser;
import client.user.User;
import com.google.gson.Gson;
import com.victorlaerte.asynctask.AsyncTask;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * The type Request profile task.
 */
public class RequestProfileTask extends AsyncTask {

    private ControllerProfile controller;
    private String json = "";

    /**
     * Instantiates a new Request profile task.
     *
     * @param controllerProfile the controller profile
     */
    public RequestProfileTask(ControllerProfile controllerProfile) {
        this.controller = controllerProfile;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public Boolean doInBackground(Object[] params) {
        //todo request data from server

        //json = ServerRequests.getProfile();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        Main.clientUser = (ClientUser) user;
        Image image = null;
        image = new Image(Main.clientUser.getImageURL());
        Main.clientUser.setProfileImage(image);
        return true;
    }

    @Override
    public void onPostExecute(Object params) {
        controller.getUsernameField().setText(Main.clientUser.getUsername());
        controller.getPointsField().setText("" + Main.clientUser.getTotalCo2());
        controller.getCarField().setText(Main.clientUser.getCarType()
                + " - " + Main.clientUser.getCarEmmisionType());
        controller.getCountryField().setText(Main.clientUser.getCountry());
        controller.getProfileImage().setFill(new ImagePattern(Main.clientUser.getProfileImage()));

    }

    @Override
    public void progressCallback(Object[] params) {

    }
}
