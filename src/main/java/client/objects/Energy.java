package client.objects;

public class Energy extends Item {

    /**
     * This class stores the information for an Energy object, data will be retrieved from the database.
     * @param name String type
     * @param co2 double type
     */
    public Energy(String name, double co2) {
        super(name, "energy", co2);
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
        Energy energy = (Energy) o;
        return energy.getName().equals(this.getName()) &&
                energy.getType().equals(this.getType()) &&
                Double.compare(energy.getCo2(), this.getCo2()) == 0;
    }
}
