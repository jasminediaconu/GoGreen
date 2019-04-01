package server.objects;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.ServerApp;
import server.helper.ActivityClass;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the REST controlling for everything Activity related
 * It will handle adding, removing and retrieving activities.
 *
 * @author wouthaakman
 */
@RestController
public class ActivityController {

    private static PreparedStatement addActivity;
    private static PreparedStatement removeActivity;
    private static PreparedStatement retrieveActivities;

    static {
        try {
            addActivity = ServerApp.dbConnection.prepareStatement("INSERT INTO "
                    + "user_activities(\"userid\", \"itemid\", \"amount\", \"date\") "
                    + "VALUES (?, ?, ?, ?) RETURNING activityid");
            removeActivity = ServerApp.dbConnection.prepareStatement("DELETE FROM user_activities "
                    + "WHERE  userid = ? AND activityid = ?");
            retrieveActivities = ServerApp.dbConnection.prepareStatement("SELECT * "
                    + "FROM user_activities WHERE userid = ? "
                    + "AND date < now() AND date > ? ORDER BY date ASC");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function will handle adding an Activity to the database,
     * that belongs to a given sessionid.
     *
     * @param sessionID String type sessionID
     * @param activity  ActivityClass type
     * @return int containing the id of the activity
     */
    @SuppressWarnings("naming") // Abstraction of parameter name for security reasons
    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    public int addActivity(@RequestParam String sessionID, @RequestBody ActivityClass activity) {
        try {
            int userID = ServerApp.getUserIDfromSession(sessionID);
            addActivity.setInt(1, userID);
            addActivity.setInt(2, activity.itemID);
            addActivity.setDouble(3, activity.amount);
            addActivity.setDate(4,
                    new Date(new SimpleDateFormat("yyyy-MM-dd").parse(activity.date).getTime()));

            ResultSet result = addActivity.executeQuery();
            result.next();
            return result.getInt(1);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * This function will remove an Activity with a given id and corresponding user.
     *
     * @param sessionID  String type sessionID.
     * @param activityID int type.
     * @return a String telling the client whether the transaction succeeded.
     */
    @SuppressWarnings("naming") // Abstraction of parameter name for security reasons
    @RequestMapping(value = "/removeActivity", method = RequestMethod.POST)
    public String removeActivity(@RequestParam String sessionID, @RequestBody int activityID) {
        try {
            int userID = ServerApp.getUserIDfromSession(sessionID);
            removeActivity.setInt(1, userID);
            removeActivity.setInt(2, activityID);
            removeActivity.executeUpdate();

            return "ok";
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * This function will handle all retrieve Activity requests.
     * It will retrieve all activities that a given user has on their profile.
     *
     * @param sessionID String type sessionID
     * @param period    A String consisting of the sessionID and domain type,
     *                  split by a whitespace character
     * @return a JSON with a list of activities
     */
    @SuppressWarnings("naming") // Abstraction of parameter name for security reasons
    @RequestMapping(value = "/retrieveActivities", method = RequestMethod.POST)
    public String retrieveActivities(@RequestParam String sessionID, @RequestBody String period) {
        try {
            int userID = ServerApp.getUserIDfromSession(sessionID);
            LocalDate domain = selectDomain(period);

            retrieveActivities.setInt(1, userID);
            retrieveActivities.setObject(2, domain);

            List<ActivityClass> activities = new ArrayList<ActivityClass>();
            ResultSet result = retrieveActivities.executeQuery();
            while (result.next()) {
                ActivityClass activity = new ActivityClass(
                        result.getInt(1), result.getInt(3),
                        result.getDouble(4), result.getString(5));
                activities.add(activity);
            }

            return ServerApp.gson.toJson(activities);
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }

    }

    /**
     * This function will take a String w, that is a width/domain of date
     * that will return the domain between Now and a date w removed from Now.
     * w can be either m=month, h=half a year, y=year or else=week
     *
     * @param period String type
     * @return a LocalDate that is w size removed from the current date
     */
    @SuppressWarnings("naming") // Abstraction of parameter name for security reasons
    private LocalDate selectDomain(String period) {
        LocalDate now = LocalDate.now();
        LocalDate domain = now.minusWeeks(1);
        if (period.equals("m")) {
            domain = now.minusMonths(1);
        } else if (period.equals("h")) {
            domain = now.minusMonths(6);
        } else if (period.equals("y")) {
            domain = now.minusYears(1);
        }
        return domain;
    }

}
