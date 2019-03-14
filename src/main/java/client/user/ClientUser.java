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

    private Car car = new Car();
    private int streakLength = 0;
    private boolean solarPower = false;
    private boolean LEDs = false;
    private int roomTemp = 21;
    private String email = "";

    @Expose(deserialize = false, serialize = false)
    private Image profileImage;
    private String imageURL;
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
     * Gets car.
     *
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * This function will get the users car emission type
     *
     * @param car the car
     * @return the type of emission of the users car >>>>>>> 37e663ff148bcf3b286d9713501244a34b537f1a
     */
    public void setCar(Car car) {
        this.car = car;
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
     * Sets room temp.
     *
     * @param roomTemp the room temp
     */
    /* Sets the users room temperature to the argument roomTemp
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
     * Sets the users solar power usage boolean to the argument solarPower
     *
     * @param solarPower boolean type
    >>>>>>> 37e663ff148bcf3b286d9713501244a34b537f1a
     */

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageURL() {
        return imageURL;
    }


    /**
     * <<<<<<< HEAD
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
                Objects.equals(car, that.car)) {
            return true;
        }
        return false;
    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(car.getCarType(), car.getEmissionType(), streakLength, solarPower, LEDs, roomTemp, following);
//    }

    /**
     * Deep copy client user.
     *
     * @return the client user
     */
    public ClientUser deepCopy() {
        ClientUser clientUser = new ClientUser();
        if (car != null) {
            clientUser.setCar(new Car(car.getCarType(), car.getEmissionType()));
        }
        clientUser.setEmail(email);
        clientUser.setUsername(username);
        clientUser.setCountry(country);
        clientUser.setImageURL(imageURL);
        clientUser.setSolarPower(solarPower);
        clientUser.setRoomTemp(roomTemp);
        clientUser.setLEDs(LEDs);
        clientUser.setStreakLength(streakLength);
        clientUser.setTotalCo2(totalCo2);

        return clientUser;
    }

    @Override
    public String toString() {
        return "[username:" + username + ";email: " + email + ";country: " + country
                + ";streak:" + streakLength + ";CO2: " + totalCo2
                + "; LED: " + LEDs + "; solar: " + solarPower + "; temp: " + roomTemp
                + car.toString() + "]";
    }
}
