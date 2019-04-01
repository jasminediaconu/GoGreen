package server.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.ServerApp;
import server.helper.AchievementClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
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

    private static PreparedStatement[] achQueries;

    private static PreparedStatement selectAchievements;
    private static PreparedStatement insertUserAchievements;
    private static PreparedStatement selectUserAchievements;
    private static PreparedStatement updateUserAchievements;

    static {
        try {
            kmsTraveled = ServerApp.dbConnection.prepareStatement(
                    "SELECT cast(sum(amount) as INTEGER) FROM user_activities AS ua JOIN"
                            + " (SELECT itemid FROM items WHERE type = 'transport') AS it"
                            + " ON ua.itemid = it.itemid WHERE userid = ?;"
            );

            uniqueRegularMeals = ServerApp.dbConnection.prepareStatement(
                    "SELECT cast(max(numdays) as INTEGER) maxseq "
                            + "FROM (SELECT grp, min(date) as date_start,"
                            + " max(date) AS mr_end, count(distinct date) AS numdays FROM"
                            + " (SELECT date, (date - cast(dense_rank() OVER (ORDER BY date)"
                            + " AS int)) AS grp FROM (SELECT date FROM user_activities AS ua"
                            + " JOIN (SELECT itemid FROM items WHERE name = 'Regular meal') AS it"
                            + " on ua.itemid = it.itemid WHERE userid = ? ORDER BY date ASC)"
                            + " AS t) AS t GROUP BY grp) AS t;"
            );

            kgVegetarianMeal = ServerApp.dbConnection.prepareStatement(
                    "SELECT cast(sum(amount) as INTEGER) "
                            + "FROM user_activities AS ua join (SELECT itemid FROM items"
                            + " WHERE name = 'Vegetarian meal') AS it on ua.itemid = it.itemid"
                            + " WHERE userid = ?;"
            );

            solarPanels = ServerApp.dbConnection.prepareStatement(
                    "SELECT cast(sum(amount) as INTEGER) "
                            + "FROM user_activities AS ua JOIN (SELECT itemid FROM items"
                            + " WHERE name = 'Solar panel') AS it ON ua.itemid = it.itemid"
                            + " WHERE userid = ?;"
            );

            temperature = ServerApp.dbConnection.prepareStatement(
                    "SELECT roomtemp as INTEGER) FROM user_profile WHERE userid = ?;"
            );

            activityAmount = ServerApp.dbConnection.prepareStatement(
                    "SELECT cast(count(*) as INTEGER) "
                            + "FROM user_activities WHERE userid = ? LIMIT 1;"
            );

            streakLength = ServerApp.dbConnection.prepareStatement(
                    "SELECT cast(streaklength as INTEGER) FROM user_profile WHERE userid = ?;"
            );

            followTenPeople = ServerApp.dbConnection.prepareStatement(
                    "SELECT cast(count(*) as INTEGER) FROM user_follows WHERE userid = ?;"
            );

            globalSpot = ServerApp.dbConnection.prepareStatement(
                    "SELECT cast(row_number as INTEGER) FROM (SELECT userid, ROW_NUMBER() OVER"
                            + " (ORDER BY totalco2 DESC) FROM user_profile) AS up"
                            + " WHERE userid = ?;"
            );

            totalCo2 = ServerApp.dbConnection.prepareStatement(
                    "SELECT cast(totalco2 as INTEGER) FROM user_profile WHERE userid = ?;"
            );

            achQueries = new PreparedStatement[15];
            achQueries[0] = kmsTraveled;
            achQueries[1] = kmsTraveled;
            achQueries[2] = uniqueRegularMeals;
            achQueries[3] = kgVegetarianMeal;
            achQueries[4] = solarPanels;
            achQueries[5] = solarPanels;
            achQueries[6] = temperature;
            achQueries[7] = activityAmount;
            achQueries[8] = streakLength;
            achQueries[9] = followTenPeople;
            achQueries[10] = globalSpot;
            achQueries[11] = globalSpot;
            achQueries[12] = totalCo2;
            achQueries[13] = totalCo2;
            achQueries[14] = totalCo2;



            selectAchievements = ServerApp.dbConnection.prepareStatement(
                    "SELECT * FROM achievements;"
            );

            insertUserAchievements = ServerApp.dbConnection.prepareStatement(
                    "INSERT INTO user_achievements (\"userid\") VALUES (?);"
            );

            selectUserAchievements = ServerApp.dbConnection.prepareStatement(
                    "SELECT * FROM user_achievements WHERE userid = ?;"
            );

            updateUserAchievements = ServerApp.dbConnection.prepareStatement(
                    "UPDATE user_achievements SET ach1 = ?, ach2 = ?, ach3 = ?,"
                    + " ach4 = ?, ach5 = ?, ach6 = ?, ach7 = ?, ach8 = ?, ach9 = ?,"
                    + " ach10 = ?, ach11= ?, ach12 = ?, ach13 = ?, ach14 = ?, ach15 = ? "
                    + " WHERE userid = ?;"
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
    private String getAchievements(@RequestParam String sessionID) {
        int userID = ServerApp.getUserIDfromSession(sessionID);
        if (userID == -1) {
            return null;
        }

        try {
            boolean[] achBooleans = getAchievementsArray(userID);

            List<AchievementClass> achievements = new ArrayList<AchievementClass>();
            ResultSet result = selectAchievements.executeQuery();
            while (result.next()) {
                achievements.add(new AchievementClass(result.getInt("achievementid"),
                        result.getString("title"), result.getString("description"),
                        result.getString("path"), 0, result.getInt("goal"), false));
            }

            for (int i = 0; i < achBooleans.length; i++) {
                AchievementClass achievement = achievements.get(i);

                int progress = getProgress(userID, achQueries[i]);
                achievement.progress = progress;

                if (achBooleans[i]) {
                    achievement.achieved = true;
                    continue;
                }

                if ((i == 6 || i == 10 || i ==  11) && progress <= achievement.goal) {
                    achBooleans[i] = true;
                    achievement.achieved = true;
                } else if (progress >= achievement.goal) {
                    achBooleans[i] = true;
                    achievement.achieved = true;
                }
            }

            updateAchievementsArray(userID, achBooleans);

            return ServerApp.gson.toJson(achievements);
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    private int getProgress(int userID, PreparedStatement query) {
        try {
            query.setInt(1, userID);
            ResultSet result = query.executeQuery();
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    private boolean[] getAchievementsArray(int userID) {
        try {
            insertUserAchievements.setInt(1, userID);
            insertUserAchievements.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            selectUserAchievements.setInt(1, userID);
            ResultSet result = selectUserAchievements.executeQuery();
            result.next();
            boolean[] achBooleans = new boolean[15];
            for (int i = 0; i < achBooleans.length; i++) {
                achBooleans[i] = result.getBoolean("ach" + (i + 1));
            }
            return achBooleans;
        } catch (SQLException e) {
            e.printStackTrace();
            return new boolean[0];
        }
    }

    private void updateAchievementsArray(int userID, boolean[] achBooleans) {
        try {
            for (int i = 0; i < achBooleans.length; i++) {
                updateUserAchievements.setBoolean(i + 1, achBooleans[i]);
            }
            updateUserAchievements.setInt(16, userID);
            updateUserAchievements.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
