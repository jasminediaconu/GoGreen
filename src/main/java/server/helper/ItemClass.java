package server.helper;

public class ItemClass {
    public int itemID;
    public String name;
    public String type;
    public double co2;

    /**
     * This class stores the information for an ItemClass, data will be retrieved from the database.
     *
     * @param itemID int type
     * @param name   String type
     * @param type   String type
     * @param co2    double type
     */
    public ItemClass(int itemID, String name, String type, double co2) {
        this.itemID = itemID;
        this.name = name;
        this.type = type;
        this.co2 = co2;
    }
}
