package client.profileScreen;

import com.victorlaerte.asynctask.AsyncTask;

import java.awt.image.BufferedImage;

/**
 * The type Send profile image.
 */
public class SendProfileImage extends AsyncTask {

    private BufferedImage profileImage;

    /**
     * Instantiates a new Send profile image.
     *
     * @param profileImage the profile image
     */
    public SendProfileImage(BufferedImage profileImage) {
        this.profileImage = profileImage;
    }


    @Override
    public void onPreExecute() {

    }

    @Override
    public Object doInBackground(Object[] params) {

        //todo send image to server


        return null;
    }

    @Override
    public void onPostExecute(Object params) {

    }

    @Override
    public void progressCallback(Object[] params) {

    }
}
