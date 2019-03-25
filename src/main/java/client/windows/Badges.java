package client.windows;

import client.Main;
import client.ServerRequests;
import client.objects.Activity;
import client.user.User;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Stream;

public class Badges {

    /**
     * This function unlocks the "Nomad" badge.
     * To unlock it the user needs to travel 100 km.
     * @param btn badge button.
     */
    public static void badge1(JFXButton btn) {
        ServerRequests sv = new ServerRequests();
        double sum = 0;
        List<Activity> activities = sv.retrieveActivities("y");
        for(Activity a : activities) {
            if(a.getItemID() >= 8) {
                sum += a.getAmount();
                if (sum >= Main.achievements.get(0).getGoal()) {
                    btn.setStyle("-fx-opacity: 100%;");
                }
            }
        }
    }

    /**
     * This function unlocks the "Go deeper" badge.
     * To unlock it the user needs to travel 100,000 km.
     * @param btn badge button.
     */
    public static void badge2(JFXButton btn) {
        ServerRequests sv = new ServerRequests();
        double sum = 0;
        List<Activity> activities = sv.retrieveActivities("y");
        for(Activity a : activities) {
            if(a.getItemID() >= 8) {
                sum += a.getAmount();
                if (sum >= Main.achievements.get(1).getGoal()) {
                    btn.setStyle("-fx-opacity: 100%;");
                }
            }
        }
    }

    /**
     * This function unlocks the "Do you even try?" badge.
     * To unlock it the user needs to eat a regular meal 5 days in a row.
     * @param btn badge button.
     */
    public static void badge3(JFXButton btn) {
        ServerRequests sv = new ServerRequests();
        int count = 0;
        List<Activity> activities = sv.retrieveActivities("y");
        activities.sort((x, y) -> x.getItemID());

        for(int i = 0; i < activities.size(); i++) {
            if(activities.get(i).getActivityID() == 1
                    && activities.get(i).getDate().minusDays(1).equals(activities.get(i+1).getDate())) {
                    count++;
                    if(count >= Main.achievements.get(2).getGoal()) {
                        btn.setStyle("-fx-opacity: 100%;");
                    }
            }
        }
    }

    /**
     * This function unlocks the "The lie" badge.
     * To unlock it the user needs to eat 5 vegetarian meals in a day.
     * @param btn badge button.
     */
    public static void badge4(JFXButton btn) {
        ServerRequests sv = new ServerRequests();
        List<Activity> activities = sv.retrieveActivities("y");
        for(Activity a : activities) {
            if(a.getAmount() >=  Main.achievements.get(3).getGoal() && a.getItemID() == 2) {
                btn.setStyle("-fx-opacity: 100%;");
            }
        }
    }

    /**
     * This function unlocks the "Acquire hardware" badge.
     * To unlock it the user needs to install 1 solar panel.
     * @param btn badge button.
     */
    public static void badge5(JFXButton btn) {
        ServerRequests sv = new ServerRequests();
        double sum = 0;
        List<Activity> activities = sv.retrieveActivities("y");
        for(Activity a : activities) {
            if(a.getItemID() == 6) {
                sum += a.getAmount();
                if (sum >= Main.achievements.get(4).getGoal()) {
                    btn.setStyle("-fx-opacity: 100%;");
                }
            }
        }
    }

    /**
     * This function unlocks the "Photosynthesis" badge.
     * To unlock it the user needs to install 10 solar panels.
     * @param btn badge button.
     */
    public static void badge6(JFXButton btn) {
        ServerRequests sv = new ServerRequests();
        double sum = 0;
        List<Activity> activities = sv.retrieveActivities("y");
        for(Activity a : activities) {
            if(a.getItemID() == 6) {
                sum += a.getAmount();
                if (sum >= Main.achievements.get(5).getGoal()) {
                    btn.setStyle("-fx-opacity: 100%;");
                }
            }
        }
    }

    /**
     * This function unlocks the "Keep it frosty" badge.
     * To unlock it the user needs to have a standard temperature of 18°C in the house.
     * @param btn badge button.
     */
    public static void badge7(JFXButton btn) {
        int temperature = Main.clientUser.getRoomTemp();
        if (temperature == Main.achievements.get(6).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Getting started" badge.
     * To unlock it the user needs to add at least one activity.
     * @param btn badge button.
     */
    public static void badge8(JFXButton btn) {
        ServerRequests sv = new ServerRequests();
        List<Activity> activities = sv.retrieveActivities("y");
        int activityNumber = activities.size();
        if (activityNumber >= Main.achievements.get(7).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Persistent" badge.
     * To unlock it the user needs to login 5 days in a row.
     * @param btn badge button.
     */
    public static void badge9(JFXButton btn) {
        int streak = Main.clientUser.getStreakLength();
        if (streak >= Main.achievements.get(8).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Stalker" badge.
     * To unlock it the user needs to follow at least 10 other users.
     * @param btn badge button.
     */
    public static void badge10(JFXButton btn) {
        int following = Main.clientUser.getFollowing().size();
        if (following >= Main.achievements.get(9).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "You are the n°1!" badge.
     * To unlock it the user needs to be the 1st player globally.
     * @param btn badge button.
     */
    public static void badge11(JFXButton btn) {
        ServerRequests sv = new ServerRequests();
        ObservableList<User> globalUsers =
                FXCollections.observableArrayList(sv.getGlobalBestProfile());
        if (globalUsers.get(0).getUsername().equals(Main.clientUser.getUsername())) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Top player" badge.
     * To unlock it the user needs to be in the top 10 globally.
     * @param btn badge button.
     */
    public static void badge12(JFXButton btn) {
        ServerRequests sv = new ServerRequests();
        ObservableList<User> globalUsers =
                FXCollections.observableArrayList(sv.getGlobalBestProfile());
        for (User u : globalUsers) {
            if (u.getUsername().equals(Main.clientUser.getUsername())) {
                btn.setStyle("-fx-opacity: 100%;");
            }
        }
    }

    /**
     * This function unlocks the "Bronze medal" badge.
     * To unlock it the user needs to save an amount of 500 CO2.
     * @param btn badge button.
     */
    public static void badge13(JFXButton btn) {
        int co2 = (int)Math.round(Main.clientUser.getTotalCo2());
        if (co2 >= Main.achievements.get(12).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Silver medal" badge.
     * To unlock it the user needs to save an amount of 5,000 CO2.
     * @param btn badge button.
     */
    public static void badge14(JFXButton btn) {
        int co2 = (int)Math.round(Main.clientUser.getTotalCo2());
        if (co2 >= Main.achievements.get(13).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Gold medal" badge.
     * To unlock it the user needs to save an amount of 50,000 CO2.
     * @param btn badge button.
     */
    public static void badge15(JFXButton btn) {
        int co2 = (int)Math.round(Main.clientUser.getTotalCo2());
        if (co2 >= Main.achievements.get(14).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }
}