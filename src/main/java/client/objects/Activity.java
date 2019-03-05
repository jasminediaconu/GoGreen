package client.objects;

import java.time.LocalDate;
import java.util.Objects;

public class Activity {

    private int activityID;
    private Item item;
    private double amount;
    private LocalDate date;

    /**
     * This is the activity class, all activities will be populated in here.
     * @param activityID int type
     * @param item Item type
     * @param amount double type
     * @param date LocalDate type
     */
    public Activity(int activityID, Item item, double amount, LocalDate date) {
        this.activityID = activityID;
        this.item = item;
        this.amount = amount;
        this.date = date;
    }

    /**
     * This function will get the activity ID
     * @return the activityID
     */
    public int getActivityID() {
        return activityID;
    }

    /**
     * This function will get the Item belonging to this Activity
     * @return the Item of this Activity
     */
    public Item getItem() {
        return item;
    }

    /**
     * This function will return the wager of the co2 of the Item, in terms of amount
     * @return a double of amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * This function will return the date this activity was created.
     * @return a LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * This function compares this Activity with another Activity to check if they are equal.
     * @param o Object type
     * @return a boolean, whether they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return activityID == activity.activityID &&
                Double.compare(activity.amount, amount) == 0 &&
                Objects.equals(item, activity.item) &&
                Objects.equals(date, activity.date);
    }

    /**
     * This function will hash the Activity class
     * @return the hashed Activity
     */
    @Override
    public int hashCode() {
        return Objects.hash(activityID, item, amount, date);
    }
}
