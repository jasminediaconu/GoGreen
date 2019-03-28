package server.user;

import client.ServerRequests;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import server.ServerApp;
import server.helper.AchievementClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AchievementController {

    private static PreparedStatement kmsTraveled;
    private static PreparedStatement uniqueRegularMeals;
    private static PreparedStatement kgVegetarianMeal;
    private static PreparedStatement solarPanels;
    private static PreparedStatement temperature;
    private static PreparedStatement activityAmount;
    private static PreparedStatement streakLength;
    private static PreparedStatement followTenPeople;
    private static PreparedStatement globalSpot;
    private static PreparedStatement totalCo2;

    private static PreparedStatement selectAchievements;
    private static PreparedStatement insertAndSelectUserAchievements;
    private static PreparedStatement updateUserAchievements;

    static {
        try {
            kmsTraveled = ServerApp.dbConnection.prepareStatement(
                    "SELECT sum(amount) FROM user_activities AS ua JOIN (SELECT itemid FROM items"
                    + " WHERE type = 'transport') AS it ON ua.itemid = it.itemid WHERE userid = ?;"
            );

            uniqueRegularMeals = ServerApp.dbConnection.prepareStatement(
                    ""
            );

            kgVegetarianMeal = ServerApp.dbConnection.prepareStatement(
                    ""
            );

            solarPanels = ServerApp.dbConnection.prepareStatement(
                    "SELECT sum(amount) FROM user_activities AS ua JOIN (SELECT itemid FROM items"
                            + " WHERE name = 'Solar panel') AS it ON ua.itemid = it.itemid WHERE userid = ?;"
            );

            activityAmount = ServerApp.dbConnection.prepareStatement(
                    ""
            );

            streakLength = ServerApp.dbConnection.prepareStatement(
                    ""
            );

            followTenPeople = ServerApp.dbConnection.prepareStatement(
                    ""
            );

            globalSpot = ServerApp.dbConnection.prepareStatement(
                    "SELECT row_number FROM (SELECT userid, ROW_NUMBER() OVER (ORDER BY totalco2 DESC) FROM user_profile) AS up WHERE userid = 598;"
            );

            totalCo2 = ServerApp.dbConnection.prepareStatement(
                    "SELECT totalco2 FROM user_profile WHERE userid = ?;"
            );




            selectAchievements = ServerApp.dbConnection.prepareStatement(
                    "SELECT * FROM achievements;"
            );

            insertAndSelectUserAchievements = ServerApp.dbConnection.prepareStatement(
                    "INSERT INTO user_achievements (\"userid\")"
                            + "SELECT ? WHERE NOT EXISTS ("
                            + "SELECT 1 FROM user_achievements WHERE userid = ?);"
            );

            updateUserAchievements = ServerApp.dbConnection.prepareStatement(
                    "UPDATE user_achiements SET ach1 = ?, ach2 = ?, ach3 = ?,"
                    + "ach4 = ?, ach5 = ?, ach6 = ?, ach7 = ?, ach8 = ?, ach9 = ?,"
                    + "ach10 = ?, ach11= ?, ach12 = ?, ach13 = ?, ach14 = ? "
                    + "WHERE userid = ?";
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function will get all the achievements from the database.
     *
     * @return a list of achievements
     */
    @RequestMapping(value = "/getAchievements", method = RequestMethod.POST)
    private String getAchievements() {
        try {
            List<AchievementClass> achievements = new ArrayList<AchievementClass>();
            ResultSet result = selectAchievements.executeQuery();
            while(result.next()) {
                achievements.add(new AchievementClass(result.getInt("achievementid"),
                        result.getString("title"), result.getString("description"),
                        result.getString("path"), 0, result.getInt("goal"), false));
            }

            return ServerApp.gson.toJson(achievements);
        }catch(SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
