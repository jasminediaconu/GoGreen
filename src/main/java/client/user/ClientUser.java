package client.user;

import com.google.gson.annotations.Expose;

import client.Main;

import client.objects.Activity;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The type Client user.
 */
public class ClientUser extends User {
    @Expose(deserialize = false, serialize = false)
    public static Image profileImage;

    private int streakLength;

    private int leds;
    private int solarPower;

    private int roomTemp;

    private String email;

    private String carType;
    private String carEmissionType;

    @Expose(deserialize = false, serialize = false)
    private List<User> following = new ArrayList<>();
    @Expose(deserialize = false, serialize = false)
    private List<Activity> activityList;


    /**
     * Instantiates a new Client user.
     */
    public ClientUser() {
    }

    /**
     * Instantiates a new Client user.
     *
     * @param username        the username
     * @param country         the country
     * @param totalCo2        the totalCo2
     * @param streakLength    the streak length
     * @param solarPower      the solar power
     * @param leds            the leds
     * @param roomTemp        the room temp
     * @param email           the email
     * @param carType         the car type
     * @param carEmissionType the car emission type
     */
    //CHECKSTYLE:OFF
    @SuppressWarnings("ParameterNumberCheck")//We need these parameters for Gson.
    public ClientUser(String username, String country, double totalCo2, int streakLength,
                      int solarPower, int leds, int roomTemp, String email,
                      String carType, String carEmissionType) {
        super(username, country, totalCo2);
        this.streakLength = streakLength;
        this.solarPower = solarPower;
        this.leds = leds;
        this.roomTemp = roomTemp;
        this.email = email;
        this.carType = carType;
        this.carEmissionType = carEmissionType;
    }
    //CHECKSTYLE:ON

    /**
     * This function will get the users car type.
     *
     * @return the car type of the user
     */
    public String getCarType() {
        return carType;
    }

    /**
     * This function will set the car tpe.
     *
     * @param carType String type
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * This function will get the users car emission type.
     *
     * @return the emission type of the car
     */
    public String getCarEmissionType() {
        return carEmissionType;
    }

    /**
     * This function will set the users car emission type.
     *
     * @param carEmissionType String type
     */
    public void setCarEmissionType(String carEmissionType) {
        this.carEmissionType = carEmissionType;
    }

    /**
     * This function will get the users streak length.
     *
     * @return the length of the users streak
     */
    public int getStreakLength() {
        return streakLength;
    }

    /**
     * Sets the users streak length to the argument streakLength.
     *
     * @param streakLength int type
     */
    public void setStreakLength(int streakLength) {
        this.streakLength = streakLength;
    }

    /**
     * This function will get whether the user is using solar power.
     *
     * @return if the user is using solar power
     */
    public int getSolarPower() {
        return solarPower;
    }

    /**
     * This function will get whether the user is using leds.
     *
     * @return if the user is using leds
     */
    public int getLeds() {
        return leds;
    }

    /**
     * This function will get the users room temperature.
     *
     * @return the temperature of the users room
     */
    public int getRoomTemp() {
        return roomTemp;
    }

    /**
     * Sets the users room temperature to the argument roomTemp.
     *
     * @param roomTemp int type
     */
    public void setRoomTemp(int roomTemp) {
        this.roomTemp = roomTemp;
    }

    /**
     * Sets the users solar power usage boolean to the argument solarPower.
     *
     * @param solarPower boolean type
     */
    public void setSolarPower(int solarPower) {
        this.solarPower = solarPower;
    }

    /**
     * Sets the users leds usage boolean to the argument leds.
     *
     * @param leds boolean type
     */
    public void setLeds(int leds) {
        this.leds = leds;
    }


    /**
     * Gets email.
     *
     * @return the email This function will add an Activity to the User's list of activities.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Gets activity list.
     *
     * @return the activity list
     */
    public List<Activity> getActivityList() {
        return this.activityList;
    }

    /**
     * This function will set the current Activity list to a new list.
     *
     * @param activityList List Activity type.
     */
    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
        this.activityList = activityList;
    }

    /**
     * This function will add an Activity to the User's list of activities.
     *
     * @param activity Activity type
     */
    public void addToActivityList(Activity activity) {
        activityList.add(activity);
    }

    public void removeFromActivityList(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * This function will return the User List following.
     *
     * @return the User List following.
     */
    public List<User> getFollowing() {
        return this.following;
    }

    /**
     * This function will set the current User List following to a new list.
     *
     * @param userList List User type.
     */
    public void setFollowing(List<User> userList) {
        this.following = userList;
    }

    /**
     * This function will add a User to the User List following.
     *
     * @param user User type.
     */
    public void addFollowing(User user) {
        following.add(user);
    }


    /**
     * This function compares this ClientUser with another ClientUser to check if they are equal.
     *
     * @param obj Object type
     * @return a boolean, whether they are equal or not
     */
    //CHECKSTYLE:OFF
    @SuppressWarnings("CyclomaticComplexityCheck") //all 11 parameters should be checked,
    // therefore the cyclomatic complexity of 11 is justified
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClientUser that = (ClientUser) obj;
        if (streakLength == that.streakLength
                && email.equals(that.email)
                && solarPower == that.solarPower
                && leds == that.leds
                && roomTemp == that.roomTemp
                && Objects.equals(following, that.following)
                && totalCo2 == that.totalCo2
                && username.equals(that.username)
                && Objects.equals(that.country, country)
                && Objects.equals(carType, that.carType)
                && Objects.equals(carEmissionType, that.carEmissionType)) {
            return true;
        }
        return false;
    }
    //CHECKSTYLE:ON

    /**
     * Deep copy client user.
     *
     * @return the client user
     */
    public ClientUser deepCopy() {
        ClientUser clientUser = new ClientUser();
        clientUser.setEmail(email);
        clientUser.setUsername(username);
        clientUser.setCountry(country);
        clientUser.setSolarPower(solarPower);
        clientUser.setRoomTemp(roomTemp);
        clientUser.setLeds(leds);
        clientUser.setStreakLength(streakLength);
        clientUser.setTotalCo2(totalCo2);
        clientUser.setCarType(carType);
        clientUser.setActivityList(activityList);
        clientUser.setCarEmissionType(carEmissionType);

        return clientUser;
    }

    @Override
    public String toString() {
        return "[username:" + username + ";email: " + email + ";country: " + country
                + ";streak:" + streakLength + ";CO2: " + totalCo2
                + "; led: " + leds + "; solar: " + solarPower + "; temp: " + roomTemp
                + "; Car type: " + carType + "; Car emission type: " + carEmissionType + "]";
    }

    /**
     * Make a new list containing the filtered activities.
     *
     * @return the filtered list
     */
    public List<Activity> getFilteredList() {
        List<Activity> filteredActivities = new ArrayList<>();
        for (Activity activity : Main.clientUser.getActivityList()) {
            if (activity.getDate().equals(LocalDate.now())) {
                filteredActivities.add(activity);
            }
        }
        return filteredActivities;
    }
}
