package server.helper;

public class ActivityClass {

    public int activityID;
    public int itemID;
    public double amount;
    public String date;

    /**
     * This is the ActivityClass class, all activities will be populated in here.
     * @param itemID int type
     * @param amount double type
     * @param date LocalDate type
     */
    public ActivityClass(int activityID, int itemID, double amount, String date) {
        this.activityID = activityID;
        this.itemID = itemID;
        this.amount = amount;
        this.date = date;
    }

}
