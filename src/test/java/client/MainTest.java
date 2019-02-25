package client;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void testHashNull(){
        Assert.assertEquals(null, Main.hashString(null));
    }

    @Test
    public void testHashPassword(){
        Assert.assertEquals("5f4dcc3b5aa765d61d8327deb882cf99", Main.hashString("password"));
    }


}
