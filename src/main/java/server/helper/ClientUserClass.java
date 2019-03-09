package server.helper;

public class ClientUserClass {

    public String username;
    public String country;
    public double totalCo2;

    public String carType;
    public String carEmissionType;
    public int streakLength;
    public boolean solarPower;
    public boolean LEDs;
    public int roomTemp;

    /**
     * This is the ClientUserClass, all client user classes will be populated here
     *
     * @param username String type
     * @param country String type
     * @param totalCo2 double type
     * @param carType String type
     * @param carEmissionType String type
     * @param streakLength int type
     * @param solarPower boolean type
     * @param LEDs boolean type
     * @param roomTemp int type
     */
    public ClientUserClass(String username, String country, double totalCo2, String carType, String carEmissionType, int streakLength, boolean solarPower, boolean LEDs, int roomTemp) {
        this.username = username;
        this.country = country;
        this.totalCo2 = totalCo2;
        this.carType = carType;
        this.carEmissionType = carEmissionType;
        this.streakLength = streakLength;
        this.solarPower = solarPower;
        this.LEDs = LEDs;
        this.roomTemp = roomTemp;
    }

}
