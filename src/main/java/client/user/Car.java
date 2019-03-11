package client.user;

/**
 * The type Car.
 */
public class Car {

    private final static String[] cars = {"Compact Car", "Average Car", "Heavy Car", "Sport Car", "SUV", "Pickup-Truck", "Truck"};
    private final static String[] emissions = {"Gasoline", "Diesel", "Gas", "Electric"};

    private int carType = -1;
    private int emissionType = -1;

    /**
     * Instantiates a new Car.
     *
     * @param carType       the car type
     * @param emmissionType the emmission type
     */
    public Car(String carType, String emmissionType) {
        this.carType = getCarIndex(carType);
        this.emissionType = getEmissionIndex(emmissionType);
    }

    /**
     * Instantiates a new Car.
     *
     * @param carType       the car type
     * @param emmissionType the emmission type
     */
    public Car(int carType, int emmissionType) {
        this.carType = carType;
        this.emissionType = emmissionType;
    }

    public Car(){

    }

    /**
     * Gets car index.
     *
     * @param carType the car type
     * @return the car index
     */
    public static int getCarIndex(String carType) {
        for (int x = 0; x < cars.length; x++) {
            if (cars[x].equalsIgnoreCase(carType)) {
                return x;
            }
        }
        return -1;
    }

    /**
     * Gets emmission index.
     *
     * @param emmission the emmission
     * @return the emmission index
     */
    public static int getEmissionIndex(String emmission) {
        for (int x = 0; x < emissions.length; x++) {
            if (emissions[x].equalsIgnoreCase(emmission)) {
                return x;
            }
        }
        return -1;
    }

    /**
     * Gets car type.
     *
     * @return the car type
     */
    public int getCarType() {
        return carType;
    }

    /**
     * Set car type.
     *
     * @param carType the car type
     */
    public void setCarType(int carType) {
        this.carType = carType;
    }

    public void setCarName(String carType) {
        this.carType = getCarIndex(carType);
    }

    /**
     * Gets emission type.
     *
     * @return the emission type
     */
    public int getEmissionType() {
        return emissionType;
    }

    /**
     * Set emission type.
     *
     * @param emissionType the emission type
     */
    public void setEmissionType(int emissionType) {
        this.emissionType = emissionType;
    }

    public void setEmissionName(String emissionType) {
        this.emissionType = getEmissionIndex(emissionType);
    }

    public String getCarName() {
        return cars[carType];
    }

    public String getEmissionName() {
        return emissions[emissionType];
    }

    @Override
    public boolean equals(Object object) {

        if (object instanceof Car) {
            Car that = (Car) object;
            if (emissionType == that.emissionType && carType == that.carType) {
                return true;
            }
        }
        return false;
    }
}
