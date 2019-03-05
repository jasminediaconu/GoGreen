package client.objects;

public abstract class Item {

    private String name;
    private String type;
    private double co2;

    /**
     * This class stores the information for an Item, data will be retrieved from the database.
     * @param name String type
     * @param type String type
     * @param co2 double type
     */
    public Item(String name, String type, double co2){
        this.name = name;
        this.type = type;
        this.co2 = co2;
    }

    /**
     * This function will get the item's name
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * This function will get the item's type
     * @return the type of the item
     */
    public String getType() {
        return type;
    }

    /**
     * This function will get the item's co2
     * @return the default co2 of this item
     */
    public double getCo2() {
        return co2;
    }

    /**
     * This function compares this Item with another Item to check if they are equal.
     * @param o Object type
     * @return a boolean, whether they are equal or not
     */
    public abstract boolean equals(Object o);
}
