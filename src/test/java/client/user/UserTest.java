package client.user;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    User user;
    User user2;

    @BeforeEach
    void setUp() {
        user = new User("admin", "Netherlands", 2000);
        user2 = new User("admin", "Netherlands", 2000);
    }

    @Test
    void getCountry() {
        Assert.assertEquals(user.getCountry().equals("Netherlands"), true);
    }

    @Test
    void getUsername() {
        Assert.assertEquals(user.getUsername().equals("admin"), true);
    }

    @Test
    void getTotalCo2() {
        Assert.assertEquals(user.getTotalCo2() == 2000,true);
    }

    @Test
    void setTotalCo2() {
        user.setTotalCo2(31415);
        Assert.assertEquals(user.getTotalCo2() == 31415, true);
    }

    @Test
    void increaseTotalCo2() {
        double total = user.getTotalCo2() + 1000;
        user.increaseTotalCo2(1000);
        Assert.assertEquals(user.getTotalCo2() == total, true);
    }

    @Test
    void equalsNull() { Assert.assertEquals(user.equals(null), false); }

    @Test
    void equalsOtherType() {
        int number = 2;
        Assert.assertEquals(user.equals(number), false);
    }

    @Test
    void equalsSelf() {Assert.assertEquals(user.equals(user),true);}

    @Test
    void equalsSame() {Assert.assertEquals(user.equals(user2), true);}

    @Test
    void equalsNotSame() {
        User user3 = new User("test","Germany",5);
        Assert.assertEquals(user.equals(user3), false);
    }

}