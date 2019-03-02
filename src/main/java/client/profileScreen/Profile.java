package client.profileScreen;

import com.google.gson.Gson;

public class Profile {

    private static String userName = "tes";
    private static String car = "";
    private static int points = -1;
    private static String country = "";
    private static float average = -1f;
    private static boolean init = false;

    private Profile() {

    }

    /*
     * ProfileListener : Provide a listener to be executed when the resources are retreived by the client
     */
    public static void init(ProfileListener profileListener) {
        if (!init) {
            //todo get json from server
            loadJson("{\"userName\":\"jan\",\"car\":\"tes\",\"points\":\"100\",\"country\":\"nether\",\"average\":\"20f\",\"init\":\"true\"}");
            profileListener.onProfileLoaded();
            //request
        } else {
            //profile is prev loaded from server
            profileListener.onProfileLoaded();
        }
    }

    public static String getCar() {
        return car;
    }

    public static int getPoints() {
        return points;
    }

    public static String getCountry() {
        return country;
    }

    public static boolean isInit() {
        return init;
    }

    public static String getUserName() {
        return userName;
    }

    public static float getAverage() {
        return average;
    }

    private static void loadJson(String json) {
        Gson gson = new Gson();
        ProfileLoad load = gson.fromJson(json, ProfileLoad.class);
        userName = load.userName;
        car = load.car;
        points = load.points;
        country = load.country;
        average = load.average;
    }

    private class ProfileLoad {
        String userName = "tes";
        String car = "";
        int points = -1;
        String country = "";
        float average = -1f;
        boolean init = false;
    }
}
