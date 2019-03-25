package server.helper;

public class ClientUserClass {

    public String username;
    public String country;
    public String email;
    public String imageUrl;
    public double totalCo2;

    public String carType;
    public String carEmissionType;
    public int streakLength;
    public int solarPower;
    public int leds;
    public int roomTemp;

    /**
     * This is the ClientUserClass, all client user classes will be populated here.
     *
     * @param username        String type
     * @param country         String type
     * @param email           String type
     * @param imageUrl        String type
     * @param totalCo2        double type
     * @param carType         String type
     * @param carEmissionType String type
     * @param streakLength    int type
     * @param solarPower      int type
     * @param leds            int type
     * @param roomTemp        int type
     */
    @SuppressWarnings("sizes") //JSON needs 11 parameters to be passed through to the user
    public ClientUserClass(String username, String country, String email, String imageUrl,
                           double totalCo2, String carType, String carEmissionType,
                           int streakLength, int solarPower, int leds, int roomTemp) {
        this.username = username;
        this.country = country;
        this.email = email;
        this.imageUrl = imageUrl;
        this.totalCo2 = totalCo2;
        this.carType = carType;
        this.carEmissionType = carEmissionType;
        this.streakLength = streakLength;
        this.solarPower = solarPower;
        this.leds = leds;
        this.roomTemp = roomTemp;
    }

    public ClientUserClass() {
    }

}
