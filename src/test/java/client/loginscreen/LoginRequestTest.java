package client.loginscreen;

import client.Main;
import client.serializer.LocalDateDeserializer;
import client.serializer.LocalDateSerializer;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class LoginRequestTest {

    public static Main main;
    private LoginRequest loginRequest;


    @Before
    public void setup() {
        main = new Main();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Main.gson = builder.setPrettyPrinting().create();
    }

    @Test
    public void loginSucces() {
        loginRequest = new LoginRequest("admin", "admin", false, new LoginController());
        boolean res = loginRequest.doInBackground(null);
        loginRequest.onPostExecute(null);
        loginRequest.progressCallback(null);
        Assert.assertTrue(res);

    }

    @Test
    public void loginFail() {
        loginRequest = new LoginRequest("adsf", "admin", false, null);
        boolean res = loginRequest.doInBackground(null);
        Assert.assertFalse(res);

        loginRequest = new LoginRequest("admin", "adfsdfghfd", false, null);
        res = loginRequest.doInBackground(null);
        Assert.assertFalse(res);

        loginRequest = new LoginRequest(null, null, false, null);
        res = loginRequest.doInBackground(null);
        Assert.assertFalse(res);

        loginRequest = new LoginRequest("", "", false, new LoginController());
        res = loginRequest.doInBackground(null);
        Assert.assertFalse(res);

        loginRequest.onPostExecute(null);

    }

    @Test
    public void loadImage() {

        loginRequest = new LoginRequest(null, null, false, null);
        loginRequest.login();
        loginRequest.getUserProfile();
        loginRequest.clientUser.setImageUrl(null);
        boolean res = loginRequest.loadImage();
        Assert.assertFalse(res);
        //unable to test because of JAVAFX
//
//

//
//        loginRequest.clientUser.setImageURL("default");
//        res = loginRequest.loadImage();
//        Assert.assertTrue(res);
//
//        loginRequest.clientUser.setImageURL("https://iculture.textopus.nl/wp-content/uploads/2014/06/The-Test-Fun-for-Friends-iPhone-iPad.png");
//        res = loginRequest.loadImage();
//        Assert.assertTrue(res);

    }
}
