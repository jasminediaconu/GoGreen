package client.objects;

public class Transport extends Item {

    /**
     * This class stores the information for a Transport object, data will be retrieved from the database.
     * @param itemID int type
     * @param name String type
     * @param co2 double type
     */
    public Transport(int itemID, String name, double co2) {
        super(itemID, name, "transport", co2);
    }

    /**
     * This function compares this Item with another Item to check if they are equal.
     * @param o Object type
     * @return a boolean, whether they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return transport.getName().equals(this.getName()) &&
                transport.getItemID() == this.getItemID() &&
                transport.getType().equals(this.getType()) &&
                Double.compare(transport.getCo2(), this.getCo2()) == 0;
    }
}
