package client.user;

import client.objects.Activity;
import com.google.gson.annotations.Expose;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Client user.
 */
public class ClientUser extends User {

    private int streakLength;
    private boolean solarPower;
    private boolean LEDs;
    private int roomTemp;
    private String email;
    private String imageURL;
    private String carType;
    private String carEmissionType;

    @Expose(deserialize = false, serialize = false)
    private Image profileImage;
    @Expose(deserialize = false, serialize = false)
    private List<User> following = new ArrayList<>();
    @Expose(deserialize = false, serialize = false)
    private List<Activity> activityList;


    /**
     * Instantiates a new Client user.
     */
    public ClientUser() {
    }

    public ClientUser(String username, String country, double totalco2, int streakLength, boolean solarPower, boolean LEDs, int roomTemp, String email, String imageURL, String carType, String carEmissionType) {
        super(username, country, totalco2);
        this.streakLength = streakLength;
        this.solarPower = solarPower;
        this.LEDs = LEDs;
        this.roomTemp = roomTemp;
        this.email = email;
        this.imageURL = imageURL;
        this.carType = carType;
        this.carEmissionType = carEmissionType;
    }

    /**
     * This function will get the users car type
     * @return the car type of the user
     */
    public String getCarType() {
        return carType;
    }

    /**
     * This function will set the car tpe
     * @param carType String type
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * This function will get the users car emission type
     * @return the emission type of the car
     */
    public String getCarEmissionType() {
        return carEmissionType;
    }

    /**
     * This function will set the users car emission type
     * @param carEmissionType String type
     */
    public void setCarEmissionType(String carEmissionType) {
        this.carEmissionType = carEmissionType;
    }

    /**
     * This function will get the users streak length
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
    public boolean hasSolarPower() {
        return solarPower;
    }

    /**
     * This function will get whether the user is using LEDs.
     *
     * @return if the user is using LEDs
     */
    public boolean hasLEDs() {
        return LEDs;
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
     * Sets the users room temperature to the argument roomTemp
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
    public void setSolarPower(boolean solarPower) {
        this.solarPower = solarPower;
    }

    /**
     * Gets profile image.
     *
     * @return the profile image
     */
    public Image getProfileImage() {
        return profileImage;
    }

    /**
     * Sets profile image.
     *
     * @param profileImage the profile image
     */
    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageURL() {
        return imageURL;
    }


    /**
     * Sets image url.
     *
     * @param imageURL the image url
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Sets the users LEDs usage boolean to the argument LEDS.
     *
     * @param LEDs boolean type
     */
    public void setLEDs(boolean LEDs) {
        this.LEDs = LEDs;
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
    }

    /**
     * This function will add an Activity to the User's list of activities.
     *
     * @param a Activity type
     */
    public void addToActivityList(Activity a) {
        activityList.add(a);
    }

    /**
     * This function compares this ClientUser with another ClientUser to check if they are equal.
     *
     * @param obj Object type
     * @return a boolean, whether they are equal or not
     */
    @Override

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ClientUser that = (ClientUser) obj;
        if (streakLength == that.streakLength &&
                email.equals(that.email) &&
                solarPower == that.solarPower &&
                LEDs == that.LEDs &&
                roomTemp == that.roomTemp &&
                email.equalsIgnoreCase(that.email) &&
                Objects.equals(following, that.following) &&
                totalCo2 == that.totalCo2 &&
                username.equals(that.username) &&
                Objects.equals(that.country, country) &&
                carType.equals(that.carType) &&
                carEmissionType.equals(that.carEmissionType)) {
            return true;
        }
        return false;
    }

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
        clientUser.setImageURL(imageURL);
        clientUser.setSolarPower(solarPower);
        clientUser.setRoomTemp(roomTemp);
        clientUser.setLEDs(LEDs);
        clientUser.setStreakLength(streakLength);
        clientUser.setTotalCo2(totalCo2);
        clientUser.setCarType(carType);
        clientUser.setCarEmissionType(carEmissionType);

        return clientUser;
    }

    @Override
    public String toString() {
        return "[username:" + username + ";email: " + email + ";country: " + country
                + ";streak:" + streakLength + ";CO2: " + totalCo2
                + "; LED: " + LEDs + "; solar: " + solarPower + "; temp: " + roomTemp
                + "; Car type: " + carType + "; Car emission type: " + carEmissionType + "]";
    }
}
