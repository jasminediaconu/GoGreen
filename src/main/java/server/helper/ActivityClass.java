package server.helper;

import java.time.LocalDate;

public class ActivityClass {

    public int activityID;
    public int itemID;
    public double amount;
    public LocalDate date;

    /**
     * This is the ActivityClass class, all activities will be populated in here.
     * @param itemID int type
     * @param amount double type
     * @param date LocalDate type
     */
    public ActivityClass(int activityID, int itemID, double amount, LocalDate date) {
        this.activityID = activityID;
        this.itemID = itemID;
        this.amount = amount;
        this.date = date;
    }

}
