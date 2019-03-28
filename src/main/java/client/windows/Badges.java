package client.windows;

import client.Main;
import client.ServerRequests;
import client.objects.Activity;
import client.user.Achievement;
import client.user.ClientUser;
import client.user.User;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Badges {

    private static List<Achievement> achievementList = Main.achievements.stream().collect(Collectors.toList());
    private static ClientUser user = new ClientUser(Main.clientUser.getUsername(),
            Main.clientUser.getCountry(), Main.clientUser.getTotalCo2(), Main.clientUser.getStreakLength(),
            Main.clientUser.getSolarPower(), Main.clientUser.getLeds(), Main.clientUser.getRoomTemp(),
            Main.clientUser.getEmail(), Main.clientUser.getImageURL(), Main.clientUser.getCarType(),
            Main.clientUser.getCarEmissionType());
    private static ServerRequests sv = new ServerRequests();
    private static List<Activity> activities = sv.retrieveActivities("y");
    private static ObservableList<User> globalUsers =
            FXCollections.observableArrayList(sv.getGlobalBestProfile());

    /**
     * This function unlocks the "Nomad" badge.
     * To unlock it the user needs to travel 100 km.
     *
     * @param btn badge button.
     */
    public static void badge1(JFXButton btn) {
        double sum = 0;
        for (Activity a : activities) {
            if (a.getItemID() >= 8) {
                sum += a.getAmount();
                if (sum >= achievementList.get(0).getGoal()) {
                    btn.setStyle("-fx-opacity: 100%;");
                }
            }
        }
    }

    /**
     * This function unlocks the "Go deeper" badge.
     * To unlock it the user needs to travel 100,000 km.
     *
     * @param btn badge button.
     */
    public static void badge2(JFXButton btn) {
        double sum = 0;

        for (Activity a : activities) {
            if (a.getItemID() >= 8) {
                sum += a.getAmount();
                if (sum >= achievementList.get(1).getGoal()) {
                    btn.setStyle("-fx-opacity: 100%;");
                }
            }
        }
    }

    /**
     * This function unlocks the "Do you even try?" badge.
     * To unlock it the user needs to eat a regular meal 5 days in a row.
     *
     * @param btn badge button.
     */
    public static void badge3(JFXButton btn) {
        int count = 0;
        List<Activity> filteredList = new ArrayList<>();

        for (Activity a : activities) {
            if (a.getItemID() == 1) {
                filteredList.add(a);
            }
        }
        if(!filteredList.isEmpty()) {
            filteredList.sort(Comparator.comparing(Activity::getDate));
            filteredList.sort((x, y) -> x.getItemID());

            LocalDate date = filteredList.get(0).getDate();
            System.out.println(filteredList);

            for (int i = 0; i < filteredList.size(); i++) {
                if (filteredList.get(i).getDate().equals(date.plusDays(i))) {
                    count++;
                    if (count >= achievementList.get(2).getGoal()) {
                        btn.setStyle("-fx-opacity: 100%;");
                    }
                }
            }
        }
    }

    /**
     * This function unlocks the "The lie" badge.
     * To unlock it the user needs to eat 1kg of vegetarian meal in a day.
     *
     * @param btn badge button.
     */
    public static void badge4(JFXButton btn) {
        for (Activity a : activities) {
            if (a.getAmount() >= achievementList.get(3).getGoal() && a.getItemID() == 2) {
                btn.setStyle("-fx-opacity: 100%;");
            }
        }
    }

    /**
     * This function unlocks the "Acquire hardware" badge.
     * To unlock it the user needs to install 1 solar panel.
     *
     * @param btn badge button.
     */
    public static void badge5(JFXButton btn) {
        double sum = 0;
        for (Activity a : activities) {
            if (a.getItemID() == 6) {
                sum += a.getAmount();
                if (sum >= achievementList.get(4).getGoal()) {
                    btn.setStyle("-fx-opacity: 100%;");
                }
            }
        }
    }

    /**
     * This function unlocks the "Photosynthesis" badge.
     * To unlock it the user needs to install 10 solar panels.
     *
     * @param btn badge button.
     */
    public static void badge6(JFXButton btn) {
        double sum = 0;
        for (Activity a : activities) {
            if (a.getItemID() == 6) {
                sum += a.getAmount();
                if (sum >= achievementList.get(5).getGoal()) {
                    btn.setStyle("-fx-opacity: 100%;");
                }
            }
        }
    }

    /**
     * This function unlocks the "Keep it frosty" badge.
     * To unlock it the user needs to have a standard temperature of 18°C in the house.
     *
     * @param btn badge button.
     */
    public static void badge7(JFXButton btn) {
        int temperature = user.getRoomTemp();
        if (temperature == achievementList.get(6).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Getting started" badge.
     * To unlock it the user needs to add at least one activity.
     *
     * @param btn badge button.
     */
    public static void badge8(JFXButton btn) {
        int activityNumber = activities.size();
        if (activityNumber >= achievementList.get(7).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Persistent" badge.
     * To unlock it the user needs to login 5 days in a row.
     *
     * @param btn badge button.
     */
    public static void badge9(JFXButton btn) {
        int streak = user.getStreakLength();
        if (streak >= achievementList.get(8).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Stalker" badge.
     * To unlock it the user needs to follow at least 10 other users.
     *
     * @param btn badge button.
     */
    public static void badge10(JFXButton btn) {
        int following = user.getFollowing().size();
        if (following >= achievementList.get(9).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "You are the n°1!" badge.
     * To unlock it the user needs to be the 1st player globally.
     *
     * @param btn badge button.
     */
    public static void badge11(JFXButton btn) {
        if (globalUsers.get(0).getUsername().equals(user.getUsername())) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Top player" badge.
     * To unlock it the user needs to be in the top 10 globally.
     *
     * @param btn badge button.
     */
    public static void badge12(JFXButton btn) {
        for (User u : globalUsers) {
            if (u.getUsername().equals(user.getUsername())) {
                btn.setStyle("-fx-opacity: 100%;");
            }
        }
    }

    /**
     * This function unlocks the "Bronze medal" badge.
     * To unlock it the user needs to save an amount of 500 CO2.
     *
     * @param btn badge button.
     */
    public static void badge13(JFXButton btn) {
        int co2 = (int) Math.round(user.getTotalCo2());
        if (co2 >= achievementList.get(12).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Silver medal" badge.
     * To unlock it the user needs to save an amount of 5,000 CO2.
     *
     * @param btn badge button.
     */
    public static void badge14(JFXButton btn) {
        int co2 = (int) Math.round(user.getTotalCo2());
        if (co2 >= achievementList.get(13).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }

    /**
     * This function unlocks the "Gold medal" badge.
     * To unlock it the user needs to save an amount of 50,000 CO2.
     *
     * @param btn badge button.
     */
    public static void badge15(JFXButton btn) {
        int co2 = (int) Math.round(user.getTotalCo2());
        if (co2 >= achievementList.get(14).getGoal()) {
            btn.setStyle("-fx-opacity: 100%;");
        }
    }
}