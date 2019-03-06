package server.objects;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import server.ServerApp;
import server.helper.ActivityClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the REST controlling for everything Activity related
 * It will handle adding, removing and retrieving activities.
 * @author wouthaakman
 *
 */
@RestController
public class ActivityController {

    private static PreparedStatement addActivity;
    private static PreparedStatement removeActivity;
    private static PreparedStatement retrieveActivities;

    static {
        try{
            addActivity = ServerApp.dbConnection.prepareStatement("INSERT INTO user_activities (\"userid\", \"itemid\", \"amount\", \"date\") VALUES (?, ?, ?, ?) SELECT SCOPE_IDENTITY()");
            removeActivity = ServerApp.dbConnection.prepareStatement("DELETE FROM user_activities WHERE  userid = ? AND activityid = ?");
            retrieveActivities = ServerApp.dbConnection.prepareStatement("SELECT * FROM user_activities WHERE userid = ? AND date < now() AND date > ?");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This function will handle adding an Activity to the database, that belongs to a given sessionid.
     * @param s String type sessionID
     * @param a ActivityClass type
     * @return int containing the id of the activity
     */
    @RequestMapping(value="/addActivity", method= RequestMethod.POST)
    public int addActivity(@RequestParam String s, @RequestBody ActivityClass a) {
        try{
            int userID = ServerApp.getUserIDFromSession(s);
            addActivity.setInt(1, userID);
            addActivity.setInt(2, a.itemID);
            addActivity.setDouble(3, a.amount);
            addActivity.setObject(4, a.date);

            return addActivity.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * This function will remove an Activity with a given id and corresponding user
     * @param s String type sessionID
     * @param term String type
     * @return a String telling the client whether the transaction succeeded
     */
    @RequestMapping(value="/removeActivity", method=RequestMethod.POST)
    public String removeActivity(@RequestParam String s, @RequestBody String term){
        try{
            int userID = ServerApp.getUserIDFromSession(s);
            int activityID = Integer.parseInt(term.split(" ")[1]);
            removeActivity.setInt(1, userID);
            removeActivity.setInt(2, activityID);
            removeActivity.executeUpdate();

            return "ok";
        }catch(Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * This function will handle all retrieve Activity requests.
     * It will retrieve all activities that a given user has on their profile.
     * @param s String type sessionID
     * @param term A String consisting of the sessionID and domain type, split by a whitespace character
     * @return a JSON with a list of activities
     */
    @RequestMapping(value="/retrieveActivites", method=RequestMethod.POST)
    public String retrieveActivities(@RequestParam String s, @RequestBody String term){
        try{
            int userID = ServerApp.getUserIDFromSession(s);
            LocalDate domain = selectDomain(term);

            retrieveActivities.setInt(1, userID);
            retrieveActivities.setObject(2, domain);

            List<ActivityClass> activities = new ArrayList<ActivityClass>();
            ResultSet result = retrieveActivities.executeQuery();
            while(result.next()){
                ActivityClass activity = new ActivityClass(
                        result.getInt(1), result.getInt(2),
                        result.getDouble(3), result.getDate(4).toLocalDate());
                activities.add(activity);
            }

            return new Gson().toJson(activities);
        }catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

    }

    /**
     * This function will take a String w, that is a width/domain of date that will return the domain between Now
     * and a date w removed from Now.
     * w can be either m=month, h=half a year, y=year or else=week
     * @param w String type
     * @return a LocalDate that is w size removed from the current date
     */
    private LocalDate selectDomain(String w){
        LocalDate now = LocalDate.now();
        LocalDate domain = now.minusWeeks(1);
        if(w.equals("m")){
            domain = now.minusMonths(1);
        }else if(w.equals("h")){
            domain = now.minusMonths(6);
        }else if(w.equals("y")){
            domain = now.minusYears(1);
        }
        return domain;
    }

}
