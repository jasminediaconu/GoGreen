package client.profilescreen;

import client.user.ClientUser;
import com.victorlaerte.asynctask.AsyncTask;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

/**
 * The type Send profile image.
 */
public class SendProfileUpdate extends AsyncTask {


    private Object update;

    /**
     * Instantiates a new Send profile update.
     *
     * @param update This either is a clientUser or a image
     */
    public SendProfileUpdate(Object update) {
        this.update = update;
    }


    @Override
    public void onPreExecute() {

    }

    @Override
    public Boolean doInBackground(Object[] params) {

        //todo send image to server

        if (update instanceof ClientUser) {
            updateClientUser();
        } else if (update instanceof Image) {
            updateProfileImage();
        }


        return true;
    }

    private void updateClientUser() {
        ClientUser update = (ClientUser) this.update;
        //todo sent to server
    }

    private void updateProfileImage() {
        BufferedImage update = SwingFXUtils.fromFXImage((Image) this.update, null);
        //todo send new profile image
    }

    @Override
    public void onPostExecute(Object params) {

    }

    @Override
    public void progressCallback(Object[] params) {

    }
}
