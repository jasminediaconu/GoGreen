package client.objects;

public class Food extends Item {

    /**
     * This class stores the information for a Food object, data will be retrieved from the database.
     * @param itemID int type
     * @param name String type
     * @param co2 double type
     */
    public Food(int itemID, String name, double co2) {
        super(itemID, name, "food", co2);
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
        Food food = (Food) o;
        return food.getName().equals(this.getName()) &&
                food.getItemID() == this.getItemID() &&
                food.getType().equals(this.getType()) &&
                Double.compare(food.getCo2(), this.getCo2()) == 0;
    }
}
