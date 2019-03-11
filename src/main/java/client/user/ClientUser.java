package client.user;

import client.objects.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientUser extends User {

    private String carType;
    private String carEmissionType;
    private int streakLength;
    private boolean solarPower;
    private boolean LEDs;
    private int roomTemp;

    private List<User> following;
    private List<Activity> activityList;

    /**
     * The ClientUser constructor, inherits from User, and has all values the db also has.
     * @param username the username of the user
     * @param country the country of the user
     * @param totalCo2 the total co2 the user has saved
     * @param carType the type of car the user has
     * @param carEmissionType the type of emission of the users car
     * @param streakLength the length of the users streak
     * @param solarPower whether the user uses solar power
     * @param LEDs whether the user uses LEDs in their house
     * @param roomtemp the room temperature of the user
     */
    public ClientUser(String username, String country, double totalCo2, String carType, String carEmissionType, int streakLength, boolean solarPower, boolean LEDs, int roomtemp) {
        super(username, country, totalCo2);
        this.carType = carType;
        this.carEmissionType = carEmissionType;
        this.streakLength = streakLength;
        this.solarPower = solarPower;
        this.LEDs = LEDs;
        this.roomTemp = roomtemp;

        following = new ArrayList<User>();
        activityList = new ArrayList<Activity>();
    }

    /**
     * This function will get the users car type.
     * @return the type of car the user has.
     */
    public String getCarType() {
        return carType;
    }

    /**
     * This function will get the users car emission type.
     * @return the type of emission of the users car.
     */
    public String getCarEmissionType() {
        return carEmissionType;
    }

    /**
     * This function will get the users streak length.
     * @return the length of the users streak.
     */
    public int getStreakLength() {
        return streakLength;
    }

    /**
     * This function will get whether the user is using solar power.
     * @return if the user is using solar power.
     */
    public boolean hasSolarPower() {
        return solarPower;
    }

    /**
     * This function will get whether the user is using LEDs.
     * @return if the user is using LEDs.
     */
    public boolean hasLEDs() {
        return LEDs;
    }

    /**
     * This function will get the users room temperature.
     * @return the temperature of the users room.
     */
    public int getRoomTemp() {
        return roomTemp;
    }

    /**
     * Sets the users car type to the argument carType.
     * @param carType String type.
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * Sets the users car emission to the argument carEmissionType.
     * @param carEmissionType String type.
     */
    public void setCarEmmisionType(String carEmissionType) {
        this.carEmissionType = carEmissionType;
    }

    /**
     * Sets the users streak length to the argument streakLength.
     * @param streakLength int type.
     */
    public void setStreakLength(int streakLength) {
        this.streakLength = streakLength;
    }

    /**
     * Sets the users solar power usage boolean to the argument solarPower.
     * @param solarPower boolean type.
     */
    public void setSolarPower(boolean solarPower) {
        this.solarPower = solarPower;
    }

    /**
     * Sets the users LEDs usage boolean to the argument LEDS.
     * @param LEDs boolean type.
     */
    public void setLEDs(boolean LEDs) {
        this.LEDs = LEDs;
    }

    /**
     * Sets the users room temperature to the argument roomTemp.
     * @param roomTemp int type.
     */
    public void setRoomTemp(int roomTemp) {
        this.roomTemp = roomTemp;
    }

    /**
     * This function will get the activity list of this User.
     * @return a List of activities.
     */
    public List<Activity> getActivityList() {
        return activityList;
    }

    /**
     * This function will add an Activity to the User's list of activities.
     * @param a Activity type.
     */
    public void addToActivityList(Activity a) {
        activityList.add(a);
    }

    /**
     * This function will set the current Activity list to a new list.
     * @param activityList List Activity type.
     */
    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    /**
     * This function compares this ClientUser with another ClientUser to check if they are equal.
     * @param o Object type
     * @return a boolean, whether they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ClientUser that = (ClientUser) o;
        return streakLength == that.streakLength
                && solarPower == that.solarPower
                && LEDs == that.LEDs
                && roomTemp == that.roomTemp
                && Objects.equals(carType, that.carType)
                && Objects.equals(carEmissionType, that.carEmissionType)
                && Objects.equals(following, that.following)
                && Objects.equals(activityList, that.activityList);
    }

    /**
     * This function will hash the ClientUser class.
     * @return the hashed ClientUser.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carType, carEmissionType, streakLength, solarPower, LEDs, roomTemp, following, activityList);
    }

}
