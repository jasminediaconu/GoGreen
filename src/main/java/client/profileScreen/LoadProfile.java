package client.profileScreen;

import client.Main;
import client.user.ClientUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorlaerte.asynctask.AsyncTask;
import javafx.scene.image.Image;

import java.io.IOException;

/**
 * The type Request profile task.
 */
public class LoadProfile extends AsyncTask {

    private ControllerProfile controller;
    private String json = "";

    /**
     * Instantiates a new Request profile task.
     *
     * @param controllerProfile the controller profile
     */
    public LoadProfile(ControllerProfile controllerProfile) {
        this.controller = controllerProfile;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public Boolean doInBackground(Object... params) {
        //todo request data from server

        //json = ServerRequests.getProfile();

        //////////////////////////////////////////Debug
        ClientUser clientUser = new ClientUser();
        clientUser.setStreakLength(3);
        clientUser.setCarEmmisionType("emType");
        clientUser.setCarType("carType");
        clientUser.setLEDs(true);
        clientUser.setRoomTemp(30);
        clientUser.setSolarPower(false);
        clientUser.setImageURL("https://iculture.textopus.nl/wp-content/uploads/2014/06/The-Test-Fun-for-Friends-iPhone-iPad.png");
        clientUser.setTotalCo2(400d);
        clientUser.setCountry("nethercountry");
        clientUser.setUsername("testName");
        /////////////////////////////////////////

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            json = mapper.writeValueAsString(clientUser);
            Main.clientUser = mapper.readValue(json, ClientUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ///


        Image image = null;
        image = new Image(Main.clientUser.getImageURL());
        Main.clientUser.setProfileImage(image);
        return true;
    }

    @Override
    public void onPostExecute(Object params) {
        controller.setUsernameField(Main.clientUser.getUsername());
        controller.setPointsField(Main.clientUser.getTotalCo2());
        controller.setCountryField(Main.clientUser.getCountry());
        controller.setCarField(Main.clientUser.getCarType()
                + " - " + Main.clientUser.getCarEmmisionType());
        controller.setProfileImage(Main.clientUser.getProfileImage());
    }

    @Override
    public void progressCallback(Object[] params) {

    }
}
