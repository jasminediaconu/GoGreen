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

@RestController
public class ActivityController {

    private static PreparedStatement addActivity;
    private static PreparedStatement removeActivity;
    private static PreparedStatement retrieveActivities;

    static {
        try{
            addActivity = ServerApp.dbConnection.prepareStatement("INSERT INTO user_activities (\"userid\", \"itemid\", \"amount\", \"date\") VALUES (?, ?, ?, ?) SELECT SCOPE_IDENTITY()");
            removeActivity = ServerApp.dbConnection.prepareStatement("DELETE FROM user_activities WHERE activityid = ?");
            retrieveActivities = ServerApp.dbConnection.prepareStatement("SELECT * FROM user_activities WHERE userid = ? AND date < now() AND date > ?");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

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

    @RequestMapping(value="/removeActivity", method=RequestMethod.POST)
    public String removeActivity(@RequestBody int id){
        try{
            removeActivity.setInt(1, id);
            removeActivity.executeUpdate();

            return "ok";
        }catch(Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value="/retrieveActivites", method=RequestMethod.POST)
    public String retrieveActivities(@RequestBody String term){
        try{
            int userID = Integer.parseInt(term.split(" ")[0]);
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

    private LocalDate selectDomain(String term){
        String w = term.split(" ")[1];
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
