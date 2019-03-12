package server.user;

import org.springframework.web.bind.annotation.*;
import server.ServerApp;
import server.helper.ClientUserClass;
import server.helper.UserClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private static PreparedStatement createClientUser;
    private static PreparedStatement selectClientUser;
    private static PreparedStatement updateStreak;
    private static PreparedStatement selectFollowing;
    private static PreparedStatement selectGlobalBest;

    static {
        try {
            createClientUser = ServerApp.dbConnection.prepareStatement(
                    "INSERT INTO user_profile (\"userid\") VALUES (?)"
            );

            selectClientUser = ServerApp.dbConnection.prepareStatement(
                    "SELECT username, countryname, totalco2, cartype, caremissiontype, lastonline, streaklength, solarpower, leds, roomtemp " +
                            "FROM user_login AS ul JOIN user_profile AS up ON ul.userid = up.userid " +
                            "WHERE ul.userid = ?;"
            );

            updateStreak = ServerApp.dbConnection.prepareStatement(
                    "UPDATE user_profile SET streaklength = ? WHERE userid = ?;"
            );

            selectFollowing = ServerApp.dbConnection.prepareStatement(
                    "SELECT username, countryname, totalco2 FROM user_profile AS up " +
                            "JOIN (SELECT followingid FROM user_follows WHERE userid = ?) AS uf ON up.userid = uf.followingid;"
            );

            selectGlobalBest = ServerApp.dbConnection.prepareStatement(
                    "SELECT username, countryname, totalco2 FROM user_profile ORDER BY totalco2 LIMIT 10;"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function will get the users profile, associated with a sessionID.
     * @param s String type
     * @return a ClientUser prepared JSON
     */
    @RequestMapping(value = "/getUserProfile", method = RequestMethod.POST)
    public ClientUserClass getUserProfile(@RequestParam String s) {
        int userID = ServerApp.getUserIDFromSession(s);
        if (userID == -1)
            return null;
        try {
            createClientUser.setInt(1, userID);
            createClientUser.executeUpdate();
        } catch (Exception e) {
        }
        try {

            selectClientUser.setInt(1, userID);
            ResultSet result = selectClientUser.executeQuery();

            while (result.next()) {
                LocalDate date = result.getDate(6).toLocalDate();
                int streakLength = result.getInt(7);
                if (LocalDate.now().minusDays(1).equals(date)) {
                    streakLength++;
                } else if (!LocalDate.now().equals(date)) {
                    streakLength = 0;
                }

                ClientUserClass clientUser = new ClientUserClass(result.getString(1), result.getString(2), result.getDouble(3),
                        result.getString(4), result.getString(5), streakLength, result.getBoolean(8),
                        result.getBoolean(9), result.getInt(10));

                updateStreak.setInt(1, streakLength);
                updateStreak.setInt(2, userID);
                updateStreak.executeUpdate();

                return clientUser;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value ="/updateUserProfile", method = RequestMethod.POST)
    public String updateUserProfile(@RequestParam String s, @RequestBody ClientUserClass client) {

    }

    /**
     * This function will get the users a ClientUser is following, associated with its sessionID
     * @param s String type
     * @return a list of Users
     */
    @RequestMapping(value = "/getFollowingProfile", method = RequestMethod.POST)
    public List<UserClass> getFollowingProfile(@RequestParam String s) {
        int userID = ServerApp.getUserIDFromSession(s);
        if (userID == -1)
            return null;
        return getUsers(-1, selectFollowing);
    }

    /**
     * This function will get the global best users, it uses the sessionID to valid the request
     * @param s String type
     * @return a list of Users
     */
    @RequestMapping(value = "/getGlobalBestProfile", method = RequestMethod.POST)
    public List<UserClass> getGlobalBestProfile(@RequestParam String s) {
        int userID = ServerApp.getUserIDFromSession(s);
        if (userID == -1)
            return null;
        return getUsers(-1, selectGlobalBest);
    }

    /**
     * This is a helper function that generalizes fetching user data from the database.
     * @param userID int type
     * @param query PreparedStatement type
     * @return a list of users, returned given a specific query
     */
    private List<UserClass> getUsers(int userID, PreparedStatement query) {
        try {
            List<UserClass> users = new ArrayList<UserClass>();
            if (userID != -1)
                query.setInt(1, userID);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                users.add(new UserClass(result.getString(1), result.getString(2), result.getDouble(3)));
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
