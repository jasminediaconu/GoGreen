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
        clientUser.setCar(new Car("Sport Car", "Electric"));
        clientUser.setLEDs(false);
        clientUser.setRoomTemp(30);
        clientUser.setSolarPower(true);
        clientUser.setImageURL("https://iculture.textopus.nl/wp-content/uploads/2014/06/The-Test-Fun-for-Friends-iPhone-iPad.png");
        clientUser.setTotalCo2(400d);
        clientUser.setCountry("nethercountry");
        clientUser.setUsername("testName");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        json = gson.toJson(clientUser);

        ///////////////////////////////////////// end of Debug

        if (json != null && json.length() != 0) {
            Main.clientUser = getClientUser(json);
            controller.setPageDisable(false);
        }
        return true;
    }

    /**
     * Init the client User from json.
     *
     * @param json the json retrieved from the server.
     *
     * @return the ClientUser formed form the json.
     */
    ClientUser getClientUser(String json) {
        Gson gson = new Gson();
        ClientUser clientUser = gson.fromJson(json, ClientUser.class);

        String url = clientUser.getImageURL();
        if (url != null && url.length() != 0) {
            clientUser.setProfileImage(new Image(url));
        }
        return clientUser;
    }


    @Override
    public void onPostExecute(Object params) {
        controller.update();
    }

    @Override
    public void progressCallback(Object[] params) {

    }
}
