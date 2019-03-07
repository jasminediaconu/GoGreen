package client.profileScreen;

import client.Main;
import client.user.Car;
import client.user.ClientUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victorlaerte.asynctask.AsyncTask;
import javafx.scene.image.Image;

/**
 * The type Request profile task.
 */
public class LoadClientProfile extends AsyncTask {

    private ProfileController controller;
    private String json = "";

    /**
     * Instantiates a new Request profile task.
     *
     * @param profileController the controller profile
     */
    public LoadClientProfile(ProfileController profileController) {
        this.controller = profileController;
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
        clientUser.setCar(new Car("Sport Car","Electric"));
        clientUser.setLEDs(true);
        clientUser.setRoomTemp(30);
        clientUser.setSolarPower(false);
        clientUser.setImageURL("https://iculture.textopus.nl/wp-content/uploads/2014/06/The-Test-Fun-for-Friends-iPhone-iPad.png");
        clientUser.setTotalCo2(400d);
        clientUser.setCountry("nethercountry");
        clientUser.setUsername("testName");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        json = gson.toJson(clientUser);

        ///////////////////////////////////////// end of Debug

        if (json != null && json.length() != 0) {
            setClientUser(json);
        }
        return true;
    }

    /**
     * Init the client User from json.
     *
     * @param json the json retrieved from the server.
     */
    void setClientUser(String json) {
        Gson gson = new Gson();
        Main.clientUser = gson.fromJson(json, ClientUser.class);
        System.out.println("LEDS: " + Main.clientUser.hasLEDs());

        String url = Main.clientUser.getImageURL();
        if (url != null && url.length() != 0) {
            Main.clientUser.setProfileImage(new Image(url));
        }
    }


    @Override
    public void onPostExecute(Object params) {
        controller.setUsernameField(Main.clientUser.getUsername());
        controller.setPointsField(Main.clientUser.getTotalCo2());
        controller.setCountryField(Main.clientUser.getCountry());
        Car car = Main.clientUser.getCar();
        controller.setCarFields(car.getCarType(), car.getEmissionType());
        controller.setProfileImage(Main.clientUser.getProfileImage());
    }

    @Override
    public void progressCallback(Object[] params) {

    }
}
