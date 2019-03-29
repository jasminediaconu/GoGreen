package server.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.ServerApp;
import server.helper.ClientUserClass;
import server.helper.UserClass;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private static PreparedStatement createClientUser;
    private static PreparedStatement selectClientUser;
    private static PreparedStatement updateClientUserProfile;
    private static PreparedStatement updateClientUserLogin;
    private static PreparedStatement updateStreak;
    private static PreparedStatement selectFollowing;
    private static PreparedStatement selectGlobalBest;
    private static PreparedStatement followUser;
    private static PreparedStatement unFollowUser;

    static {
        try {
            createClientUser = ServerApp.dbConnection.prepareStatement(
                    "INSERT INTO user_profile (\"userid\") VALUES (?)"
            );

            selectClientUser = ServerApp.dbConnection.prepareStatement(
                    "SELECT username, countryname, email, imageurl, totalco2, cartype, "
                            + "caremissiontype, lastonline, streaklength, solarpower, leds, "
                            + "roomtemp FROM user_login AS ul JOIN user_profile "
                            + "AS up ON ul.userid = up.userid WHERE ul.userid = ?;"
            );

            updateClientUserProfile = ServerApp.dbConnection.prepareStatement(
                    "UPDATE user_profile SET countryname = ?, imageurl = ?, totalco2 = ?, "
                            + "cartype = ?, caremissiontype = ?, solarpower = ?, leds = ?, "
                            + "roomtemp = ? WHERE userid = ?;"
            );

            updateClientUserLogin = ServerApp.dbConnection.prepareStatement(
                    "UPDATE user_login SET email = ? WHERE userid = ?;"
            );

            updateStreak = ServerApp.dbConnection.prepareStatement(
                    "UPDATE user_profile SET streaklength = ?, lastonline = ? WHERE userid = ?;"
            );

            selectFollowing = ServerApp.dbConnection.prepareStatement(
                    "SELECT username, countryname, totalco2 FROM user_profile AS up "
                            + "JOIN (SELECT followingid FROM user_follows WHERE userid = ?) "
                            + "AS uf ON up.userid = uf.followingid "
                            + "JOIN user_login AS ul ON up.userid = ul.userid;"
            );

            selectGlobalBest = ServerApp.dbConnection.prepareStatement(
                    "SELECT username, countryname, totalco2 FROM user_profile AS up "
                            + "JOIN user_login AS ul ON up.userid = ul.userid "
                            + "ORDER BY totalco2 DESC LIMIT 10;"
            );

            followUser = ServerApp.dbConnection.prepareStatement(
                    "INSERT INTO user_follows (\"userid\", \"followingid\") VALUES (?, (SELECT userid FROM user_login WHERE username = ?));"
            );

            unFollowUser = ServerApp.dbConnection.prepareStatement(
                    "DELETE FROM user_follows WHERE userid = ? AND followingid = (SELECT userid FROM user_login WHERE username = ?);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function will get the users profile, associated with a sessionID.
     *
     * @param sessionID String type
     * @return a ClientUser prepared JSON
     */
    @SuppressWarnings("naming") // Abstraction of parameter name for security reasons
    @RequestMapping(value = "/getUserProfile", method = RequestMethod.POST)
    public ClientUserClass getUserProfile(@RequestParam String sessionID) {
        int userID = ServerApp.getUserIDfromSession(sessionID);
        if (userID == -1) {
            return null;
        }

        try {
            createClientUser.setInt(1, userID);
            createClientUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {

            selectClientUser.setInt(1, userID);
            ResultSet result = selectClientUser.executeQuery();

            while (result.next()) {
                LocalDate date = result.getDate("lastonline").toLocalDate();
                int streakLength = result.getInt("streaklength");
                if (LocalDate.now().minusDays(1).equals(date)) {
                    streakLength++;
                } else if (!LocalDate.now().equals(date)) {
                    streakLength = 0;
                }

                updateStreak.setInt(1, streakLength);
                updateStreak.setDate(2, new Date(new SimpleDateFormat("yyyy-MM-dd")
                        .parse(LocalDate.now().toString()).getTime()));
                updateStreak.setInt(3, userID);
                updateStreak.executeUpdate();

                ClientUserClass clientUser = new ClientUserClass(result.getString("username"),
                        result.getString("countryname"), result.getString("email"),
                        result.getString("imageurl"), result.getDouble("totalco2"),
                        result.getString("cartype"), result.getString("caremissiontype"),
                        streakLength, result.getInt("solarpower"), result.getInt("leds"),
                        result.getInt("roomtemp")
                );

                return clientUser;
            }

            return null;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function updates the users profile.
     *
     * @param sessionID String type
     * @param client    ClientUserClass type
     * @return a String that is says either success or fail
     */
    @RequestMapping(value = "/updateUserProfile", method = RequestMethod.POST)
    public String updateUserProfile(@RequestParam String sessionID, @RequestBody ClientUserClass client) {
        int userID = ServerApp.getUserIDfromSession(sessionID);
        if (userID == -1) {
            return null;
        }

        try {
            updateClientUserProfile.setString(1, client.country);
            updateClientUserProfile.setString(2, client.imageUrl);
            updateClientUserProfile.setDouble(3, client.totalCo2);
            updateClientUserProfile.setString(4, client.carType);
            updateClientUserProfile.setString(5, client.carEmissionType);
            updateClientUserProfile.setInt(6, client.solarPower);
            updateClientUserProfile.setInt(7, client.leds);
            updateClientUserProfile.setInt(8, client.roomTemp);
            updateClientUserProfile.setInt(9, userID);
            updateClientUserProfile.executeUpdate();

            updateClientUserLogin.setString(1, client.email);
            updateClientUserLogin.setInt(2, userID);
            updateClientUserLogin.executeUpdate();

            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * This function will get the users a ClientUser is following, associated with its sessionID.
     *
     * @param sessionID String type
     * @return a list of Users
     */
    @RequestMapping(value = "/getFollowingProfile", method = RequestMethod.POST)
    public List<UserClass> getFollowingProfile(@RequestParam String sessionID) {
        int userID = ServerApp.getUserIDfromSession(sessionID);
        if (userID == -1) {
            return null;
        }
        return getUsers(userID, selectFollowing);
    }

    /**
     * This function will get the global best users, it uses the sessionID to valid the request.
     *
     * @param sessionID String type
     * @return a list of Users
     */
    @RequestMapping(value = "/getGlobalBestProfile", method = RequestMethod.POST)
    public List<UserClass> getGlobalBestProfile(@RequestParam String sessionID) {
        int userID = ServerApp.getUserIDfromSession(sessionID);
        if (userID == -1) {
            return null;
        }
        return getUsers(-1, selectGlobalBest);
    }

    @RequestMapping(value = "/followUser", method = RequestMethod.POST)
    public String followUser(@RequestParam String sessionID, @RequestBody String username) {
        return unfollowOrFollowUser(sessionID, username, followUser);
    }

    @RequestMapping(value = "/unFollowUser", method = RequestMethod.POST)
    public String unFollowUser(@RequestParam String sessionID, @RequestBody String username) {
        return unfollowOrFollowUser(sessionID, username, unFollowUser);
    }

    /**
     * This is a helper function that generalizes fetching user data from the database.
     *
     * @param userID int type
     * @param query  PreparedStatement type
     * @return a list of users, returned given a specific query
     */
    private List<UserClass> getUsers(int userID, PreparedStatement query) {
        try {
            List<UserClass> users = new ArrayList<UserClass>();
            if (userID != -1) {
                query.setInt(1, userID);
            }
            ResultSet result = query.executeQuery();
            while (result.next()) {
                users.add(new UserClass(result.getString(1),
                        result.getString(2), result.getDouble(3)));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String unfollowOrFollowUser(String sessionID, String username, PreparedStatement query) {
        int userID = ServerApp.getUserIDfromSession(sessionID);
        if (userID == -1) {
            return null;
        }

        try {
            username = username.substring(1, username.length() - 1);
            query.setInt(1, userID);
            query.setString(2, username);
            query.executeUpdate();

            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
