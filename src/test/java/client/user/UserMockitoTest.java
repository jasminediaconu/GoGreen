package client.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserMockitoTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(mockedUser.getUsername()).thenReturn("Jasmine");
        when(mockedUser.getCountry()).thenReturn("Italy");
        when(mockedUser.getTotalCo2()).thenReturn(200.00);
    }

    @Mock
    User mockedUser;

    @Test
    public void testGetUsername() {
        assertEquals(mockedUser.getUsername(), "Jasmine");
    }

    @Test
    public void testSetUsername() {
        doCallRealMethod().when(mockedUser).setUsername(eq("Admin"));
        mockedUser.setUsername("Admin");
        verify(mockedUser).setUsername("Admin");

    }

    @Test
    public void testGetCountry() {
        assertEquals(mockedUser.getCountry(), "Italy");
    }

    @Test
    public void testSetCountry() {
        doCallRealMethod().when(mockedUser).setCountry(eq("Netherlands"));
        mockedUser.setCountry("Netherlands");
        verify(mockedUser).setCountry("Netherlands");
    }

    @Test
    public void testGetTotalCo2() {
        assertEquals(mockedUser.getTotalCo2(), 200.00, 1);
    }

    @Test
    public void testSetTotalCo2() {
        doCallRealMethod().when(mockedUser).setTotalCo2(eq(203.38));
        mockedUser.setTotalCo2(203.38);
        verify(mockedUser).setTotalCo2(203.38);
    }

    @Test
    public void testIncreaseTotalCo2() {
        doCallRealMethod().when(mockedUser).increaseTotalCo2(eq(5.00));
        mockedUser.increaseTotalCo2(5.00);
        when(mockedUser.getTotalCo2()).thenReturn(205.00);
        assertEquals(mockedUser.getTotalCo2(), 205.00, 1);

    }

    @Test
    public void testEqualsNull() {
        assertEquals(mockedUser.equals(null), false);
    }

    @Test
    public void testEqualsOtherType() {
        assertEquals(mockedUser.equals(3), false);
    }

    @Test
    public void testEqualsSame() {
        assertEquals(mockedUser.equals(mockedUser), true);
    }

    @Test
    public void testEqualsSameType_DifferentValues() {
        User admin = new User("Admin", "France", 202.00);
        assertEquals(mockedUser.equals(admin), false);
    }
}
