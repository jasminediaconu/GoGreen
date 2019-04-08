package client.user;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;

public class AchievementMockitoTest {

    @Mock
    BufferedImage mockImage1;
    @Mock
    BufferedImage mockImage2;

    Achievement achievement1;
    Achievement achievement2;


    @BeforeEach
    public void setup()  throws Exception{
        MockitoAnnotations.initMocks(this);

        achievement1 = new Achievement();

        achievement2 = new Achievement();
    }

    @Test
    public void imageTestEqual(){
        achievement1.setImage(mockImage1);
        Assert.assertEquals(mockImage1, achievement1.getImage());

    }

    @Test
    public void imageTestUnEqual(){
        achievement1.setImage(mockImage1);
        Assert.assertNotEquals(mockImage2, achievement1.getImage());
    }

    @Test
    public void discriptionTestEqual(){
        achievement1.setDescription("new desc");
        Assert.assertEquals("new desc", achievement1.getDescription());
    }

    @Test
    public void discriptionTestUnEqual(){
        achievement1.setDescription("new desc");
        Assert.assertNotEquals("new desc2", achievement1.getDescription());
    }

    @Test
    public void goalTestEqual(){
        achievement1.setGoal(666);
        Assert.assertEquals(666, achievement1.getGoal());
    }

    @Test
    public void goalTestUnEqual(){
        achievement1.setGoal(666);
        Assert.assertNotEquals(766, achievement1.getGoal());
    }

    @Test
    public void idTestEqual(){
        achievement1.setId(6667);
        Assert.assertEquals(6667, achievement1.getId());
    }

    @Test
    public void idTestUnEqual(){
        achievement1.setId(6667);
        Assert.assertNotEquals(7667, achievement1.getId());
    }

    @Test
    public void titleTestEqual(){
        achievement1.setTitle("tile2");
        Assert.assertEquals("tile2", achievement1.getTitle());
    }

    @Test
    public void titleTestUnEqual(){
        achievement1.setTitle("tile1");
        Assert.assertNotEquals("tile2", achievement1.getTitle());
    }
}
