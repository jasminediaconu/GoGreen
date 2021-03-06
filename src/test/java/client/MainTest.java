package client;

import com.google.common.hash.Hashing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class MainTest {


    @Test
    public void testConstructor(){
//        Main.main(new String[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRoundSmaller(){

       Main.round(100, -1);
    }

    @Test
    public void testRoundGreater(){
        Assert.assertEquals(0, Double.compare(0.97, Main.round(0.967, 2)));
    }

    @Test
    public void testHashNull() {
        Assert.assertEquals(null, Main.hashString(null));
    }

    @Test
    public void testHashPasswordRaw() {
        Assert.assertEquals("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", Main.hashString("password"));
    }

    @Test
    public void testHashPasswordLib() {
        Assert.assertEquals(Hashing.sha256().hashString("password", StandardCharsets.UTF_8).toString(), Main.hashString("password"));
    }

}
