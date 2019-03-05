package client.profileScreen;

import com.google.gson.Gson;

/**
 * The type Profile.
 */
public class Profile {

    private static String userName = "tes";
    private static String car = "";
    private static int points = -1;
    private static String country = "";
    private static float average = -1f;
    private static boolean init = false;

    private Profile() {

    }

    /**
     * Init.
     *
     * @param profileListener the profile listener
     */
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

    /**
     * Gets car.
     *
     * @return the car
     */
    public static String getCar() {
        return car;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public static int getPoints() {
        return points;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public static String getCountry() {
        return country;
    }

    /**
     * Is init boolean.
     *
     * @return the boolean
     */
    public static boolean isInit() {
        return init;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * Gets average.
     *
     * @return the average
     */
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
        /**
         * The User name.
         */
        String userName = "tes";
        /**
         * The Car.
         */
        String car = "";
        /**
         * The Points.
         */
        int points = -1;
        /**
         * The Country.
         */
        String country = "";
        /**
         * The Average.
         */
        float average = -1f;
        /**
         * The Init.
         */
        boolean init = false;
    }
}
