package client;

import client.objects.Activity;
import client.serializer.LocalDateDeserializer;
import client.serializer.LocalDateSerializer;
import client.user.ClientUser;
import client.user.User;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

class ServerRequestsTest {

    private ServerRequests sv;

    @BeforeEach
    void setup() {
        sv = new ServerRequests();
        ServerRequests.requestUrl = "https://group72.herokuapp.com/";
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Main.gson = builder.setPrettyPrinting().create();
        Main.sessionID = "test";
    }

    @Test
    void loginNull() {
        Assert.assertNull(sv.login(null, "password"));
        Assert.assertNull(sv.login("username", null));
    }

    @Test
    void loginSyntax() {
        Assert.assertEquals("syntax", sv.login("usr", "password"));
        Assert.assertEquals("syntax", sv.login("username", "pwd"));
    }

    @Test
    void loginUsername() {
        Assert.assertEquals("username", sv.login("dafdsfsfgsrfsdafafasfas", "password"));
    }

    @Test
    void loginPassword() {
        Assert.assertEquals("password", sv.login("admin", "test"));
    }

    @Test
    void loginSuccess() {
        Assert.assertEquals("success", sv.login("admin", "admin"));
    }

    @Test
    void loginFail() {
        ServerRequests.requestUrl = "";
        Assert.assertEquals("fail", sv.login("username", "password"));
    }

    @Test
    void signUpNull() {
        Assert.assertNull(sv.signUp(null, "email@email.com", "password"));
        Assert.assertNull(sv.signUp("username", null, "password"));
        Assert.assertNull(sv.signUp("username", "email@email.com", null));
    }

    @Test
    void signUpSyntax() {
        Assert.assertEquals("syntax", sv.signUp("usr", "email@email.com", "password"));
        Assert.assertEquals("syntax", sv.signUp("username", "email", "password"));
        Assert.assertEquals("syntax", sv.signUp("username", "email@email.com", "pwd"));
    }

    @Test
    void signUpUsername() {
        Assert.assertEquals("username", sv.signUp("admin", "email@email.com", "password"));
    }

    @Test
    void signUpEmail() {
        Assert.assertEquals("email", sv.signUp("randomusername", "admin@test.com", "password"));
    }

    @Test
    void signUpSuccess() {
        String random = "TestAccount" + new Random().nextInt(10000);

        Assert.assertEquals("success", sv.signUp(random, random + "@gmail.com", "password"));
    }

    @Test
    void signUpFail() {
        ServerRequests.requestUrl = "";
        Assert.assertEquals("fail", sv.signUp("username", "email@email.com", "password"));
    }


    @Test
    void endSessionOK() {
        String response = sv.endSession();
        Assert.assertEquals("ok", response);
    }

    @Test
    void endSessionFail() {
        ServerRequests.requestUrl = "";
        Assert.assertNull(sv.endSession());
    }

    @Test
    void getItemsSuccess() {
        sv.getItems();
        Assert.assertNotEquals(0, Main.items.size());
    }

    @Test
    void getItemsFail() {
        ServerRequests.requestUrl = "";
        sv.getItems();
        Assert.assertEquals(0, Main.items.size());
    }

    @Test
    void addActivityTrue() {
        Activity activity = new Activity(2, 5, LocalDate.now());
        Assert.assertEquals(true, sv.addActivity(activity));

        Assert.assertEquals(true, sv.removeActivity(activity.getActivityID()));
    }

    @Test
    void addActivityNull() {
        Assert.assertEquals(false, sv.addActivity(null));
    }

    @Test
    void addActivityFalse() {
        ServerRequests.requestUrl = "";
        Assert.assertEquals(false, sv.addActivity(new Activity(2, 5, LocalDate.now())));
        Activity activity = new Activity(2, 5, LocalDate.now());
        ServerRequests.requestUrl = "https://group72.herokuapp.com/";
        Assert.assertNotEquals(false, sv.addActivity(activity));

        Assert.assertEquals(true, sv.removeActivity(activity.getActivityID()));
    }

    @Test
    void removeActivityTrue() {
        Assert.assertEquals(true, sv.removeActivity(10));
    }

    @Test
    void removeActivityFalseID() {
        Assert.assertEquals(false, sv.removeActivity(-1));
    }

    @Test
    void removeActivityFalseRequest() {
        ServerRequests.requestUrl = "";
        Assert.assertEquals(false, sv.removeActivity(1000));
    }

    @Test
    void retrieveActivitiesSuccess() {
        Assert.assertNotEquals(null, sv.retrieveActivities("h"));
        Assert.assertNotEquals(null, sv.retrieveActivities("y"));
        List<Activity> activityList = sv.retrieveActivities("w");
        Assert.assertNotEquals(0, activityList.size());
        Assert.assertNotEquals(null, activityList.get(0));
    }

    @Test
    void retrieveActivitiesFail() {
        ServerRequests.requestUrl = "";
        Assert.assertNotEquals(null, sv.retrieveActivities("h"));
        Assert.assertNotEquals(null, sv.retrieveActivities("y"));
        List<Activity> activityList = sv.retrieveActivities("w");
        Assert.assertEquals(0, activityList.size());
    }

    @Test
    void getClientUserProfile() {
        ClientUser cu = sv.getClientUserProfile();
        Assert.assertNotNull(cu);
        Assert.assertEquals("admin", cu.getUsername());
    }

    @Test
    void getNullClientUserProfile() {
        Main.sessionID = "";
        Assert.assertNull(sv.getClientUserProfile());
    }

    @Test
    void updateClientUserProfileSuccess() {
        Main.clientUser = sv.getClientUserProfile();
        Assert.assertEquals(true, sv.updateClientUserProfile());
    }

    @Test
    void updateClientUserProfileFail() {
        Assert.assertEquals(false, sv.updateClientUserProfile());
    }

    @Test
    void followUserSuccess(){
        sv.unFollowUser("wouthaakman");
        Assert.assertEquals(true, sv.followUser("wouthaakman"));
    }

    @Test
    void followUserAlreadyFollow(){
        sv.followUser("wouthaakman");
        Assert.assertEquals(false, sv.followUser("wouthaakman"));
    }

    @Test
    void followUserNoUser(){
        Assert.assertEquals(false, sv.followUser("ashdlfjas345dfasdjfasfhskdfjhasjdf"));
    }

    @Test
    void unFollowUserSucces(){
        sv.followUser("wouthaakman");
        Assert.assertEquals(true, sv.unFollowUser("wouthaakman"));
    }

    @Test
    void unFollowUserAlready(){
        sv.unFollowUser("wouthaakman");
        Assert.assertEquals(true, sv.unFollowUser("wouthaakman"));
    }

    @Test
    void unFollowUserNoUser(){
        Assert.assertEquals(true, sv.unFollowUser("ashdlfjas345dfasdjfasfhskdfjhasjdf"));
    }

    @Test
    void recoverPasswordFail(){
        Assert.assertEquals("syntax", sv.recoverPassword("messy"));
    }

    @Test
    void recoverPasswordSucces(){
        Assert.assertEquals("oopproject72@gmail.com", sv.recoverPassword("oopproject72@gmail.com"));
    }

    @Test
    void changePasswordNoPassword(){
        Assert.assertEquals("syntax", sv.changePassword("0000", ""));
    }

    @Test
    void changePasswordNullID(){
        Assert.assertEquals("fail", sv.changePassword(null, "342423"));
    }

    @Test
    void changePasswordNullPassword(){
        Assert.assertEquals("fail", sv.changePassword("0000", "000000"));
    }

    @Test
    void changePasswordFail(){
        Assert.assertEquals("fail", sv.changePassword("000", "000000"));
    }

    @Test
    void getAchievementsFail(){
        String id = Main.sessionID;
        Main.sessionID = "";
        sv.getAchievements();
        Main.sessionID = id;
    }

    @Test
    void getFollowingProfileOK() {
        List<User> users = sv.getFollowingProfile();
        Assert.assertNotEquals(0, users.size());
        Assert.assertNotNull(users.get(0));
    }

    @Test
    void getFollowingProfileNull() {
        ServerRequests.requestUrl = "";
        List<User> users = sv.getFollowingProfile();
        Assert.assertEquals(0, users.size());
    }

    @Test
    void unFollowNoSession(){
        String id = Main.sessionID;
        Main.sessionID = "";
        Assert.assertEquals(false, sv.unFollowUser("admin"));
    }

    @Test
    void getGlobalBestProfileOK() {
        List<User> users = sv.getGlobalBestProfile();
        Assert.assertNotEquals(0, users.size());
        Assert.assertNotNull(users.get(0));
    }

    @Test
    void getGlobalBestProfileNull() {
        ServerRequests.requestUrl = "";
        List<User> users = sv.getGlobalBestProfile();
        Assert.assertEquals(0, users.size());
    }

    @Test
    void getUserIDMinusOne() {
        ServerRequests.requestUrl = "";
        Assert.assertEquals(-1, sv.getUserID());
    }

    @Test
    void getUserIDOne() {
        Assert.assertEquals(1, sv.getUserID());
    }
}