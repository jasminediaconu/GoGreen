package client.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Client user.
 */
public class ClientUser extends User {

    @Expose
    private String carType;
    @Expose
    private String carEmmisionType;
    @Expose
    private int streakLength;
    @Expose
    private boolean solarPower;
    @Expose
    private boolean LEDs;
    @Expose
    private int roomTemp;

    @JsonIgnore
    private Image profileImage;
    @Expose
    private String imageURL;

    private List<User> following = new ArrayList<>();


    /**
     * Instantiates a new Client user.
     */
    public ClientUser() {
    }

    /**
     * This function will get the users car type.
     *
     * @return the type of car the user has.
     */
    public String getCarType() {
        return carType;
    }

    /**
     * Sets the users car type to the argument carType.
     *
     * @param carType String type
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * This function will get the users car emission type.
     *
     * @return the type of emission of the users car
     */
    public String getCarEmmisionType() {
        return carEmmisionType;
    }

    /**
     * Sets the users car emission to the argument carEmissionType.
     *
     * @param carEmmisionType String type.
     */
    public void setCarEmmisionType(String carEmmisionType) {
        this.carEmmisionType = carEmmisionType;
    }

    /**
     * This function will get the users streak length.
     *
     * @return the length of the users streak.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientUser that = (ClientUser) o;
        return streakLength == that.streakLength &&
                solarPower == that.solarPower &&
                LEDs == that.LEDs &&
                roomTemp == that.roomTemp &&
                Objects.equals(carType, that.carType) &&
                Objects.equals(carEmmisionType, that.carEmmisionType) &&
                Objects.equals(following, that.following);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carType, carEmmisionType, streakLength, solarPower, LEDs, roomTemp, following);
    }
}
