package server.user;

import server.ServerApp;
import server.helper.AchievementClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AchiementController {

    private static PreparedStatement selectId;
    private static PreparedStatement selectTitle;
    private static PreparedStatement selectDescription;
    private static PreparedStatement selectPath;

    static {
        try {
            selectId = ServerApp.dbConnection.prepareStatement(
                    "SELECT id FROM achievements;"
            );

            selectTitle = ServerApp.dbConnection.prepareStatement(
                    "SELECT title FROM achievements;"
            );

            selectDescription = ServerApp.dbConnection.prepareStatement(
                    "SELECT description FROM achievements;"
            );

            selectPath = ServerApp.dbConnection.prepareStatement(
                    "SELECT path FROM achievements;"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is a helper function that generalizes fetching achievements data from the database.
     *
     * @param id int type
     * @param query  PreparedStatement type
     * @return a list of users, returned given a specific query
     */
    private List<AchievementClass> getAchievement(int id, PreparedStatement query) {
        try {
            List<AchievementClass> achievements = new ArrayList<AchievementClass>();
            if (id != -1) {
                query.setInt(1, id);
            }
            ResultSet result = query.executeQuery();
            while (result.next()) {
                achievements.add(new AchievementClass(result.getInt(1), result.getString(2),
                        result.getString(2), result.getString(3)));
            }
            return achievements;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
