package client.loginscreen;

import client.Main;
import client.serializer.LocalDateDeserializer;
import client.serializer.LocalDateSerializer;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class SignUpRequestTest {

    public static Main main;
    private SignUpRequest signUpRequest;

    public String genString() {

        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String randString = RandomStringUtils.random(length, useLetters, useNumbers);
        return randString;
    }

    @Before
    public void setup() {
        main = new Main();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Main.gson = builder.setPrettyPrinting().create();
    }

    @Test
    public void signUpSucces() {
        signUpRequest = new SignUpRequest(genString(), "4trafasdfasdfasdfasdfdf", genString() + "@gmail.com", new SignUpController());
        Boolean res = signUpRequest.doInBackground(null);
        Assert.assertTrue(res);

        signUpRequest.onPostExecute(null);
    }

    @Test
    public void signUpFail() {
        signUpRequest = new SignUpRequest(null, null, null, null);
        Boolean res = signUpRequest.doInBackground(null);
        Assert.assertFalse(res);

        signUpRequest = new SignUpRequest("admin", "4trafasdfasdfasdfasdfdf", genString() + "@gmail.com", null);
        res = signUpRequest.doInBackground(null);
        Assert.assertFalse(res);

        signUpRequest = new SignUpRequest(";;/asdf", "admin", genString() + "@test.com", null);
        res = signUpRequest.doInBackground(null);
        Assert.assertFalse(res);

        signUpRequest = new SignUpRequest(genString(), "4trafasdfasdfasdfasdfdf", "admin@test.com", null);
        res = signUpRequest.doInBackground(null);
        Assert.assertFalse(res);

        signUpRequest = new SignUpRequest(genString(), "4trafasdfasdfasdfasdfdf", ";null", new SignUpController());
        res = signUpRequest.doInBackground(null);
        Assert.assertFalse(res);

        signUpRequest.onPostExecute(null);
    }
}
