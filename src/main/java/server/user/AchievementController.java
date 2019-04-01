package server.user;

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

    private static PreparedStatement select;

    static {
        try {
            select = ServerApp.dbConnection.prepareStatement(
                    "SELECT achievementid, title, description, path, goal FROM achievements;"
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
            ResultSet result = select.executeQuery();
            while (result.next()) {
                achievements.add(new AchievementClass(result.getInt("achievementid"),
                        result.getString("title"), result.getString("description"),
                        result.getString("path"), result.getInt("goal"), result.getInt("progress"),
                        result.getBoolean("achieved")));
            }

            return ServerApp.gson.toJson(achievements);
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
