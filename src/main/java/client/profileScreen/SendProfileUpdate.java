package client.profileScreen;

import client.user.Car;
import client.windows.MainScreen;

import com.victorlaerte.asynctask.AsyncTask;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

/**
 * The type Send profile image.
 */
public class SendProfileUpdate extends AsyncTask {

    /**
     * The constant USERNAME_UPDATE.
     */
    public static final int USERNAME_UPDATE = 0;
    /**
     * The constant CAR_UPDATE.
     */
    public static final int CAR_UPDATE = 1;
    /**
     * The constant PROFILE_IMAGE_UPDATE.
     */
    public static final int PROFILE_IMAGE_UPDATE = 2;
    private Object update;
    private int updateType;

    /**
     * Instantiates a new Send profile update.
     *
     * @param update     the data which needs to be send to the server.
     * @param updateType the update type
     */
    public SendProfileUpdate(Object update, int updateType) {
        this.update = update;
        this.updateType = updateType;
    }


    @Override
    public void onPreExecute() {

    }

    @Override
    public Boolean doInBackground(Object[] params) {

        //todo send image to server

        switch (updateType) {
            case USERNAME_UPDATE:
                updateUserName();
                break;
            case CAR_UPDATE:
                updateCar();
                break;

            case PROFILE_IMAGE_UPDATE:
                updateProfileImage();

        }


        return true;
    }

    private void updateUserName() {
        if (update instanceof String) {
            String update = (String) this.update;
            //todo send new username
        }
    }

    private void updateCar() {
        if (update instanceof Car) {
            Car update = (Car) this.update;
            //todo send new car info
        }

    }

    private void updateProfileImage() {
        if (update instanceof Image) {
            BufferedImage update = SwingFXUtils.fromFXImage((Image) this.update, null);
            //todo send new profile image
        }
    }

    @Override
    public void onPostExecute(Object params) {

    }

    @Override
    public void progressCallback(Object[] params) {

    }
}
