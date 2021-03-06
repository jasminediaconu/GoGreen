package client.objects;

import java.util.Objects;

public class Item {

    private int itemID;
    private String name;
    private String type;
    private double co2;

    /**
     * This class stores the information for an Item, data will be retrieved from the database.
     *
     * @param itemID int type
     * @param name   String type
     * @param type   String type
     * @param co2    double type
     */
    public Item(int itemID, String name, String type, double co2) {
        this.itemID = itemID;
        this.name = name;
        this.type = type;
        this.co2 = co2;
    }

    /**
     * This function will get the item's ID.
     *
     * @return the ID of the item.
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * This function will get the item's name.
     *
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * This function will get the item's type.
     *
     * @return the type of the item.
     */
    public String getType() {
        return type;
    }

    /**
     * This function will get the item's co2.
     *
     * @return the default co2 of this item.
     */
    public double getCo2() {
        return co2;
    }

    /**
     * Equals method.
     *
     * @param object Object type.
     * @return a boolean, whether they are equal or not.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Item item = (Item) object;
        return itemID == item.itemID
                && Double.compare(item.co2, co2) == 0
                && Objects.equals(name, item.name)
                && Objects.equals(type, item.type);
    }
}
