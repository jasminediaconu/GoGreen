package client.user;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientUserTest {

    ClientUser user;
    ClientUser user2;

    @BeforeEach
    void setUp() {
        user = new ClientUser("username", "country", 10.0, 3, true, false, 21, "email", "default", "SUV", "Gas");
        user2 = user.deepCopy();
    }


    @Test
    void getStreakLength() {
        user.setStreakLength(3);
        Assert.assertEquals(3, user.getStreakLength());
    }

    @Test
    void hasSolarPower() {
        user.setSolarPower(false);
        Assert.assertEquals(false, user.hasSolarPower());
    }

    @Test
    void hasLEDs() {
        user.setLEDs(false);
        Assert.assertEquals(false, user.hasLEDs());
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
        user.setSolarPower(true);
        Assert.assertEquals(true, user.hasSolarPower());
    }

    @Test
    void setLEDs() {
        user.setSolarPower(true);
        Assert.assertEquals(true, user.hasSolarPower());
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
    void equalsSimilar(){
        ClientUser user3 = new ClientUser("username", "country", 10.0, 3, true, false, 21, "email", "default", "SUV", "Gas");
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
        user3.setSolarPower(false);
        Assert.assertEquals(user.equals(user3), false);
        user3.setSolarPower(true);
        user3.setLEDs(true);
        Assert.assertEquals(user.equals(user3), false);
        user3.setLEDs(false);
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