//package client.user;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//
//public class Achievement {
//
//    private String title;
//    private BufferedImage image;
//
//    public Achievement(String title, String path) {
//        this.title = title;
//        this.image = loadImage(path);
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public BufferedImage getImage() {
//        return image;
//    }
//
//    public void setImage(BufferedImage image) {
//        this.image = image;
//    }
//
//    public void setImagePath(String path){
//        setImage(loadImage(path));
//    }
//
//    public static BufferedImage loadImage(String path){
//        try {
//            return ImageIO.read(Achievement.class.getResourceAsStream("client/user/" + path));
//        }catch(Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
