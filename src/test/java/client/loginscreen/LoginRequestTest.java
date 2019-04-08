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
        loginRequest = new LoginRequest("root", "root", false, new LoginController());
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
}
