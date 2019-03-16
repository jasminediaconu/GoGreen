package client.objects;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ActivityTest {

    Activity activity1;
    Activity activity2;

    @BeforeEach
    void setUp() {
        activity1 = new Activity(1, 2, LocalDate.now());
        activity2 = new Activity(2, 5, LocalDate.now().minusDays(2));
    }

    @Test
    void getActivityID() {
        Assert.assertEquals(activity1.getActivityID(), -1);
        Assert.assertEquals(activity2.getActivityID(), -1);
    }

    @Test
    void setActivityID() {
        activity1.setActivityID(2);
        Assert.assertNotEquals(activity1.getActivityID(), activity2.getActivityID());
        activity2.setActivityID(2);
        Assert.assertEquals(activity1.getActivityID(), activity2.getActivityID());
        Assert.assertEquals(activity1.getActivityID(), 2);
    }

    @Test
    void getItemID() {
        Assert.assertEquals(activity1.getItemID(), 1);
        Assert.assertEquals(activity2.getItemID(), 2);
        Assert.assertNotEquals(activity1.getItemID(), activity2.getItemID());
    }

    @Test
    void getAmount() {
        Assert.assertEquals(activity1.getAmount(), 2, 0);
        Assert.assertEquals(activity2.getAmount(), 5, 0);
        Assert.assertNotEquals(activity1.getAmount(), activity2.getAmount());
    }

    @Test
    void getDate() {
        Assert.assertEquals(activity1.getDate(), LocalDate.now());
        Assert.assertEquals(activity2.getDate(), LocalDate.now().minusDays(2));
        Assert.assertNotEquals(activity1.getDate(), activity2.getDate());
    }

    @Test
    void equals() {
        Assert.assertNotEquals(activity1, activity2);
        Activity activity = new Activity(1, 2, LocalDate.now());
        Assert.assertEquals(activity1, activity);
        Assert.assertNotEquals(activity, null);
        int number = 1;
        Assert.assertNotEquals(activity, number);
    }
}