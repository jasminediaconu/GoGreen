package client.user;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
<<<<<<< HEAD
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

class AchievementTest {

    Achievement achievement;

    @BeforeEach
    void setUp() {
        achievement = new Achievement("We got to go deeper", "mc_nether.png");
    }

    @Test
    void getTitle() {
        Assert.assertEquals("We got to go deeper", achievement.getTitle());
    }

    @Test
    void setTitle() {
        achievement.setTitle("This is not a reference");
        Assert.assertEquals("This is not a reference", achievement.getTitle());
    }

    @Test
    void getImage() {
        try {
            Assert.assertEquals(ImageIO.read(AchievementTest.class.getResourceAsStream("client/user/mc_nether.png")), achievement.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setImage() {
        achievement.setImagePath("blanc.jpg");
        try {
            Assert.assertEquals(ImageIO.read(AchievementTest.class.getResourceAsStream("client/user/blanc.jpg")), achievement.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
=======
//import org.junit.jupiter.api.Test;
//
//import javax.imageio.ImageIO;
//
//class AchievementTest {
//
//    Achievement achievement;
//
//    @BeforeEach
//    void setUp() {
//        achievement = new Achievement("We got to go deeper", "mc_nether.png");
//    }
//
//    @Test
//    void getTitle() {
//        Assert.assertEquals("We got to go deeper", achievement.getTitle());
//    }
//
//    @Test
//    void setTitle() {
//        achievement.setTitle("This is not a reference");
//        Assert.assertEquals("This is not a reference", achievement.getTitle());
//    }
//
//    @Test
//    void getImage() {
//        try{
//            Assert.assertEquals(ImageIO.read(AchievementTest.class.getResourceAsStream("client/user/mc_nether.png")), achievement.getImage());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void setImage() {
//        achievement.setImagePath("blanc.jpg");
//        try{
//            Assert.assertEquals(ImageIO.read(AchievementTest.class.getResourceAsStream("client/user/blanc.jpg")), achievement.getImage());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}
>>>>>>> 611c1dc2acb0a8f7f60307ea14e08788dd56807d
