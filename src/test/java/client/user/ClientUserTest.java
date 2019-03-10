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

    }


    @Test
    void getCarType() {
        user.setCar(new Car("Compact Car", "Electric"));
        Assert.assertEquals("Compact Car", user.getCar().getCarName());
    }

    @Test
    void getCarEmissionType() {
        user.setCar(new Car("Sport Car", "Electric"));
        Assert.assertEquals("Electric", user.getCar().getEmissionName());
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
    void setCarType() {
        user.setCar(new Car(0, 0));
        user.getCar().setCarType("SUV");
        Assert.assertEquals("SUV", user.getCar().getCarName());
    }

    @Test
    void setCarEmisionType() {
        user.setCar(new Car(0, 0));
        user.getCar().setEmissionType("Gas");
        Assert.assertEquals("Gas", user.getCar().getEmissionName());
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
    void equalsNotSame() {
        user2.setStreakLength(25);
        Assert.assertEquals(user.equals(user2), false);
    }

}