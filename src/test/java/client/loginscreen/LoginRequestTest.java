package client.loginscreen;

import client.Main;
import client.serializer.LocalDateDeserializer;
import client.serializer.LocalDateSerializer;
import com.google.gson.GsonBuilder;
import javafx.embed.swing.JFXPanel;
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
        loginRequest = new LoginRequest("admin", "admin", new LoginController());
        boolean res = loginRequest.doInBackground(null);
        Assert.assertTrue(res);
        loginRequest.onPostExecute(null);
    }

    @Test
    public void loginFail() {
        loginRequest = new LoginRequest("adsf", "admin", null);
        boolean res = loginRequest.doInBackground(null);
        Assert.assertFalse(res);

        loginRequest = new LoginRequest("admin", "adf", null);
        res = loginRequest.doInBackground(null);
        Assert.assertFalse(res);

        loginRequest = new LoginRequest(null, null, null);
        res = loginRequest.doInBackground(null);
        Assert.assertFalse(res);

        loginRequest = new LoginRequest("", "", new LoginController());
        res = loginRequest.doInBackground(null);
        Assert.assertFalse(res);

        loginRequest.onPostExecute(null);

    }

    @Test
    public void loadImage() {
        loginRequest = new LoginRequest(null, null, null);
        loginRequest.login();
        loginRequest.getUserProfile();

        JFXPanel jfxPanel = new JFXPanel();

        loginRequest.clientUser.setImageURL(null);
        boolean res = loginRequest.loadImage();
        Assert.assertFalse(res);

        loginRequest.clientUser.setImageURL("default");
        res = loginRequest.loadImage();
        Assert.assertTrue(res);

        loginRequest.clientUser.setImageURL("https://iculture.textopus.nl/wp-content/uploads/2014/06/The-Test-Fun-for-Friends-iPhone-iPad.png");
        res = loginRequest.loadImage();
        Assert.assertTrue(res);

    }
}
