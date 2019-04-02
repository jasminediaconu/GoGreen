package client.user;

import client.objects.Activity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientUserMockitoTest {

    public List<Activity> activityList = new ArrayList<>();

    @Mock
    ClientUser mockedClientUser;

    @Before
    public void setUp() {
        Activity activity1 = new Activity(1, 100, LocalDate.now());
        Activity activity2 = new Activity(2, 140, LocalDate.now().minusDays(1));
        Activity activity3 = new Activity(3, 50, LocalDate.now().minusDays(2));

        activityList.add(activity1);
        activityList.add(activity2);
        activityList.add(activity3);

        MockitoAnnotations.initMocks(this);
        when(mockedClientUser.getUsername()).thenReturn("Jasmine");
        when(mockedClientUser.getCountry()).thenReturn("Italy");
        when(mockedClientUser.getTotalCo2()).thenReturn(200.00);
        when(mockedClientUser.getCarType()).thenReturn("Electric Car");
        when(mockedClientUser.getCarEmissionType()).thenReturn("Gasoline");
        when(mockedClientUser.getStreakLength()).thenReturn(5);
        when(mockedClientUser.getSolarPower()).thenReturn(0);
        when(mockedClientUser.getLeds()).thenReturn(0);
        when(mockedClientUser.getRoomTemp()).thenReturn(21);
        when(mockedClientUser.getImageUrl()).thenReturn("img.png");
        when(mockedClientUser.getEmail()).thenReturn("test@greenly.com");
        when(mockedClientUser.getActivityList()).thenReturn(activityList);
    }


    @Test
    public void testGetCarType() {
        assertEquals(mockedClientUser.getCarType(), "Electric Car");
    }

    @Test
    public void setCarType() {
        doCallRealMethod().when(mockedClientUser).setCarType(eq("SUV"));
        mockedClientUser.setCarType("SUV");
        verify(mockedClientUser).setCarType("SUV");
    }

    @Test
    public void getCarEmissionType() {
        assertEquals(mockedClientUser.getCarEmissionType(), "Gasoline");
    }

    @Test
    public void setCarEmissionType() {
        doCallRealMethod().when(mockedClientUser).setCarEmissionType(eq("Diesel"));
        mockedClientUser.setCarEmissionType("Diesel");
        verify(mockedClientUser).setCarEmissionType("Diesel");
    }

    @Test
    public void getStreakLength() {
        assertEquals(mockedClientUser.getStreakLength(),5);
    }

    @Test
    public void setStreakLength() {
        doCallRealMethod().when(mockedClientUser).setStreakLength(eq(5));
        mockedClientUser.setStreakLength(5);
        verify(mockedClientUser).setStreakLength(5);
    }

    @Test
    public void hasSolarPower() {
        assertEquals(mockedClientUser.getSolarPower(), 0);
    }

    @Test
    public void hasLEDs() {
        assertEquals(mockedClientUser.getLeds(), 0);
    }

    @Test
    public void getRoomTemp() {
        assertEquals(mockedClientUser.getRoomTemp(), 21);
    }

    @Test
    public void setRoomTemp() {
        doCallRealMethod().when(mockedClientUser).setRoomTemp(eq(25));
        mockedClientUser.setRoomTemp(25);
        verify(mockedClientUser).setRoomTemp(25);
    }

    @Test
    public void setSolarPower() {
        doCallRealMethod().when(mockedClientUser).setSolarPower(eq(10));
        mockedClientUser.setSolarPower(10);
        verify(mockedClientUser).setSolarPower(10);
    }

    @Test
    public void getProfileImage() {
       // Image img = new Image("aaa");
       // assertEquals(mockedClientUser.getProfileImage(), img);
    }

    @Test
    public void setProfileImage() {
    }

    @Test
    public void getImageURL() {
        assertEquals(mockedClientUser.getImageUrl(), "img.png");
    }

    @Test
    public void setImageURL() {
        doCallRealMethod().when(mockedClientUser).setImageUrl(eq("proPic.jpg"));
        mockedClientUser.setImageUrl("proPic.jpg");
        verify(mockedClientUser).setImageUrl("proPic.jpg");
    }

    @Test
    public void setLEDs() {
        doCallRealMethod().when(mockedClientUser).setLeds(eq(10));
        mockedClientUser.setLeds(10);
        verify(mockedClientUser).setLeds(10);
    }

    @Test
    public void getEmail() {
        assertEquals(mockedClientUser.getEmail(), "test@greenly.com");
    }

    @Test
    public void setEmail() {
        doCallRealMethod().when(mockedClientUser).setEmail(eq("jasmine@greenly.com"));
        mockedClientUser.setEmail("jasmine@greenly.com");
        verify(mockedClientUser).setEmail("jasmine@greenly.com");
    }

    @Test
    public void getActivityList() {
        assertEquals(mockedClientUser.getActivityList(), activityList);

    }

    @Test
    public void setActivityList() {
        Activity activity = new Activity(1, 349, LocalDate.now());
        activityList.add(activity);

        doCallRealMethod().when(mockedClientUser).setActivityList(eq(activityList));
        mockedClientUser.setActivityList(activityList);
        verify(mockedClientUser).setActivityList(activityList);
    }

    @Test
    public void addToActivityList() {
        List<Activity> activityList2 = new ArrayList<>();
        Activity activity1 = new Activity(1, 100, LocalDate.now());
        Activity activity2 = new Activity(2, 140, LocalDate.now().minusDays(1));
        Activity activity3 = new Activity(3, 50, LocalDate.now().minusDays(2));
        Activity activity4 = new Activity(4, 232, LocalDate.now());

        activityList.add(activity4);

        activityList2.add(activity1);
        activityList2.add(activity2);
        activityList2.add(activity3);
        activityList2.add(activity4);

        doCallRealMethod().when(mockedClientUser).addToActivityList(eq(activity4));
        when(mockedClientUser.getActivityList()).thenReturn(activityList);
        assertEquals(mockedClientUser.getActivityList(), activityList2);
    }

    @Test
    public void equalsNull() {
        assertNotNull(mockedClientUser);
    }

    @Test
    public void equalsOtherType() {
        assertFalse(mockedClientUser.equals(3));
    }

    @Test
    public void equalsSame() {
        assertTrue(mockedClientUser.equals(mockedClientUser));
    }

    @Test
    public void equalsSameType_DifferentValues() {
        ClientUser user = new ClientUser("Admin", "Belgium", 30.00, 15, 0, 0, 23, "SUV", "Diesel", "aaa", "aaa");
        assertFalse(mockedClientUser.equals(user));
    }

    @Test
    public void deepCopy() {
        ClientUser user1 = new ClientUser("Admin", "France", 123.94, 0, 0, 0, 17, "aaa@greenly.com", "img.url", "SUV", "Diesel");
        assertEquals(user1.deepCopy(), user1);
    }

}