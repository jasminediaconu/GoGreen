package client.user;

import com.google.gson.annotations.Expose;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Client user.
 */
public class ClientUser extends User {

    private String carType;
    private String carEmissionType;
    private int streakLength;
    private boolean solarPower;
    private boolean LEDs;
    private int roomTemp;

    @Expose(deserialize = false, serialize = false)
    private Image profileImage;
    private String imageURL;
    @Expose(deserialize = false, serialize = false)
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
    public String getCarEmissionType() {
        return carEmissionType;
    }

    /**
     * Sets the users car emission to the argument carEmissionType.
     *
     * @param carEmmisionType String type.
     */
    public void setCarEmmisionType(String carEmmisionType) {
        this.carEmissionType = carEmmisionType;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ClientUser that = (ClientUser) obj;
        return streakLength == that.streakLength &&
                solarPower == that.solarPower &&
                LEDs == that.LEDs &&
                roomTemp == that.roomTemp &&
                Objects.equals(carType, that.carType) &&
                Objects.equals(carEmissionType, that.carEmissionType) &&
                Objects.equals(following, that.following);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carType, carEmissionType, streakLength, solarPower, LEDs, roomTemp, following);
    }
}
