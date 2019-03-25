package client.objects;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class ActivityMockitoTest{

    Activity activity1;
    Activity activity2;

    @Mock
    LocalDate now1;
    @Mock
    LocalDate now2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        activity1 = new Activity(1, 2, now1);
        activity2 = new Activity(2, 5, now2);
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
        Assert.assertEquals(activity1.getDate(),now1);
        Assert.assertEquals(activity2.getDate(), now2);
        Assert.assertNotEquals(activity1.getDate(), now2);
    }

    @Test
    void equals() {
        Assert.assertEquals(activity1, activity1);
        Assert.assertNotEquals(activity1, activity2);
        Activity activity = new Activity(1, 2, now1);
        Assert.assertEquals(activity1, activity);
        Assert.assertNotEquals(activity, null);
        int number = 1;
        Assert.assertNotEquals(activity, number);
        activity.setItemID(2);
        Assert.assertNotEquals(activity1, activity);
        activity.setItemID(1);
        activity.setAmount(5);
        Assert.assertNotEquals(activity1, activity);
        activity.setAmount(2);
        activity.setDate(now2);
        Assert.assertNotEquals(activity1, activity);
        activity.setDate(now1);
        activity.setActivityID(1);
        Assert.assertNotEquals(activity1, activity);
    }
}
