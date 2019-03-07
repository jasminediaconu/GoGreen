package client.user;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientUserTest {

    ClientUser user;
    ClientUser user2;

    @BeforeEach
    void setUp() {
//        user = new ClientUser("Ricardo", "Netherlands", 1200, "Mini", "Electric", 3, false, false, 21);
//        user2 = new ClientUser("Ricardo", "Netherlands", 1200, "Mini", "Electric", 3, false, false, 21)
        user = new ClientUser();
        user2 = new ClientUser();
        user.setCar(new Car(0, 3));
        user2.setCar(new Car(0, 1));


    }

    @Test
    void getCarType() {
        Assert.assertEquals("Compact Car", user.getCar().getCarName());
    }

    @Test
    void getCarEmissionType() {
        Assert.assertEquals("Electric", user.getCar().getEmissionName());
    }

    @Test
    void getStreakLength() {
        Assert.assertEquals(3, user.getStreakLength());
    }

    @Test
    void hasSolarPower() {
        Assert.assertEquals(false, user.hasSolarPower());
    }

    @Test
    void hasLEDs() {
        Assert.assertEquals(false, user.hasLEDs());
    }

    @Test
    void getRoomTemp() {
        Assert.assertEquals(21, user.getRoomTemp());
    }

    @Test
    void setCarType() {
        user.getCar().setCarType("SUV");
        Assert.assertEquals("SUV", user.getCar().getCarName());
    }

    @Test
    void setCarEmisionType() {
        user.getCar().setEmissionType("Gas");
        Assert.assertEquals("Gas", user.getCar().getEmissionType());
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
        user.setLEDs(true);
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
        Assert.assertEquals(user.equals(user2), true);
    }

    @Test
    void equalsNotSame() {
        user2.setStreakLength(25);
        Assert.assertEquals(user.equals(user2), false);
    }

}