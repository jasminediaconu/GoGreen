package client.user;

/**
 * The type Car.
 */
public class Car {

    private final static String[] cars = {"Compact Car", "Average Car", "Heavy Car", "Sports Car", "SUV", "Pickup-Truck", "Truck"};
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
     * Gets emission type.
     *
     * @return the emission type
     */
    public int getEmissionType() {
        return emissionType;
    }
}
