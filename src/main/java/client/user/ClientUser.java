package client.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientUser extends User {

    private String carType;
    private String carEmmisionType;
    private int streakLength;
    private boolean solarPower;
    private boolean LEDs;
    private int roomTemp;

    private List<User> following;

    /**
     * The ClientUser constructor, inherits from User, and has all values the db also has.
     *
     * @param username        the username of the user
     * @param country         the country of the user
     * @param totalCo2        the total co2 the user has saved
     * @param carType         the type of car the user has
     * @param carEmissionType the type of emission of the users car
     * @param streakLength    the length of the users streak
     * @param solarPower      whether the user uses solar power
     * @param LEDs            whether the user uses LEDs in their house
     * @param roomtemp        the room temperature of the user
     */
    public ClientUser(String username, String country, double totalCo2, String carType, String carEmissionType, int streakLength, boolean solarPower, boolean LEDs, int roomtemp) {
        super(username, country, totalCo2);
        this.carType = carType;
        this.carEmmisionType = carEmissionType;
        this.streakLength = streakLength;
        this.solarPower = solarPower;
        this.LEDs = LEDs;
        this.roomTemp = roomtemp;

        following = new ArrayList<User>();
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
     * Sets the users car type to the argument carType
     *
     * @param carType String type
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * This function will get the users car emission type
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
     * Sets the users streak length to the argument streakLength
     *
     * @param streakLength int type
     */
    public void setStreakLength(int streakLength) {
        this.streakLength = streakLength;
    }

    /**
     * This function will get whether the user is using solar power
     *
     * @return if the user is using solar power
     */
    public boolean hasSolarPower() {
        return solarPower;
    }

    /**
     * This function will get whether the user is using LEDs
     *
     * @return if the user is using LEDs
     */
    public boolean hasLEDs() {
        return LEDs;
    }

    /**
     * This function will get the users room temperature
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
     * Sets the users solar power usage boolean to the argument solarPower
     *
     * @param solarPower boolean type
     */
    public void setSolarPower(boolean solarPower) {
        this.solarPower = solarPower;
    }

    /**
     * Sets the users LEDs usage boolean to the argument LEDS
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
