package client.user;

import java.awt.image.BufferedImage;

public abstract class Achievement {

    protected String title = "";
    protected BufferedImage image = null;

    public Achievement(String title, String iamgePath) {
        this.title = title;

        //todo load imaga

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
