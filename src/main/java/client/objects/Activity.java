package client.objects;

import java.time.LocalDate;
import java.util.Objects;

public class Activity {

    private int activityID;
    private int itemID;
    private double amount;
    private LocalDate date;

    /**
     * This is the activity class, all activities will be populated in here.
     *
     * @param itemID int type
     * @param amount double type
     * @param date   LocalDate type
     */
    public Activity(int itemID, double amount, LocalDate date) {
        this.activityID = -1;
        this.itemID = itemID;
        this.amount = amount;
        this.date = date;
    }

    /**
     * This function will get the activity ID.
     *
     * @return the activityID.
     */
    public int getActivityID() {
        return activityID;
    }

    /**
     * This function will set the activity ID.
     * This function should be called after the database added the activity,
     * since by default the id is -1.
     *
     * @param activityID int type.
     */
    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    /**
     * This function will get the itemID belonging to this Activity.
     *
     * @return the itemID of this Activity
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * This function changes the current itemId to a new itemID.
     *
     * @param itemID int type
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * This function will return the wager of the co2 of the Item, in terms of amount.
     *
     * @return a double of amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * This function changes the current amount to a new amount.
     *
     * @param amount double type
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * This function will return the date this activity was created.
     *
     * @return a LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * This function changes the current date to a new date.
     *
     * @param date LocalDate type.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * This function compares this Activity with another Activity to check if they are equal.
     *
     * @param object Object type
     * @return a boolean, whether they are equal or not
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Activity activity = (Activity) object;
        return activityID == activity.activityID
                && Double.compare(activity.amount, amount) == 0
                && itemID == activity.itemID
                && Objects.equals(date, activity.date);
    }
}
