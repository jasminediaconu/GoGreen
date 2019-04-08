package client.user;

import client.Main;
import client.objects.Activity;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ClientUserTest {

    ClientUser user;
    ClientUser user2;

    @BeforeEach
    void setUp() {
        user = new ClientUser("username", "country", 10.0, 3, 0, 0, 21, "email", "SUV", "Gas");
        User newuser = new User("admin", "Netherlands", 10);
        List<User> following = new ArrayList<>();
        user.addFollowing(newuser);
        user.setFollowing(following);
        user2 = user.deepCopy();
    }


    @Test
    void getStreakLength() {
        user.setStreakLength(3);
        Assert.assertEquals(3, user.getStreakLength());
    }

    @Test
    void hasSolarPower() {
        user.setSolarPower(10);
        Assert.assertEquals(10, user.getSolarPower());
    }

    @Test
    void hasLEDs() {
        user.setLeds(10);
        Assert.assertEquals(10, user.getLeds());
    }

    @Test
    void getRoomTemp() {
        user.setRoomTemp(21);
        Assert.assertEquals(21, user.getRoomTemp());
    }

    @Test
    void setStreakLength() {
        user.setStreakLength(12);
        Assert.assertEquals(12, user.getStreakLength());
    }

    @Test
    void setSolarPower() {
        user.setSolarPower(10);
        Assert.assertEquals(10, user.getSolarPower());
    }

    @Test
    void setLEDs() {
        user.setLeds(10);
        Assert.assertEquals(10, user.getLeds());
    }

    @Test
    void setRoomTemp() {
        user.setRoomTemp(25);
        Assert.assertEquals(25, user.getRoomTemp());
    }

    @Test
    void equalsNull() {
        Assert.assertEquals(user.equals(null), false);
    }

    @Test
    void equalsSelf() {
        Assert.assertEquals(user.equals(user), true);
    }

    @Test
    void equalsSame() {
        user2 = user.deepCopy();
        Assert.assertTrue(user.equals(user2));
    }

    @Test
    void equalsSimilar() {
        ClientUser user3 = new ClientUser("username", "country", 10.0, 3, 0, 0, 21, "email", "SUV", "Gas");
        user3.setUsername("usernaem");
        Assert.assertEquals(user.equals(user3), false);
        user3.setUsername("username");
        user3.setCountry("countries");
        Assert.assertEquals(user.equals(user3), false);
        user3.setCountry("country");
        user3.setTotalCo2(2.0);
        Assert.assertEquals(user.equals(user3), false);
        user3.setTotalCo2(10.0);
        user3.setStreakLength(4);
        Assert.assertEquals(user.equals(user3), false);
        user3.setStreakLength(3);
        user3.setSolarPower(20);
        Assert.assertEquals(user.equals(user3), false);
        user3.setSolarPower(0);
        user3.setLeds(10);
        Assert.assertEquals(user.equals(user3), false);
        user3.setLeds(0);
        user3.setRoomTemp(20);
        Assert.assertEquals(user.equals(user3), false);
        user3.setRoomTemp(21);
        user3.setEmail("gmail");
        Assert.assertEquals(user.equals(user3), false);
        user3.setEmail("email");
        user3.setCarType("Van");
        Assert.assertEquals(user.equals(user3), false);
        user3.setCarType("SUV");
        user3.setCarEmissionType("diesel");
        Assert.assertEquals(user.equals(user3), false);
        user3.setCarEmissionType("gas");
        User newuser = new User("admin","Netherlands", 10);
        User neweruser = new User("test","Netherlands", 5);
        ArrayList<User> userlist = new ArrayList<User>();
        userlist.add(newuser);
        userlist.add(neweruser);
        user3.setFollowing(userlist);
        Assert.assertEquals(user.equals(user3), false);
    }

    @Test
    void filteredListSucces(){
        Main.clientUser = new ClientUser();
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(new Activity(39, 32d, LocalDate.now()));
        activities.add(new Activity(34, 30d, LocalDate.of(2019, 1, 20)));
        Main.clientUser.setActivityList(activities);
        ArrayList<Activity> filteredActivities = new ArrayList<>();
        filteredActivities.add(new Activity(39, 32d, LocalDate.now()));
        Assert.assertArrayEquals(filteredActivities.toArray(), Main.clientUser.getFilteredList().toArray());
    }

    @Test
    void equalsNotSame() {
        user2.setStreakLength(25);
        Assert.assertEquals(user.equals(user2), false);
    }

    @Test
    void equalsOtherType() {
        int number = 1;
        Assert.assertEquals(user.equals(number), false);
    }
}