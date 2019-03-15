
package client.user;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * The type Achievement.
 */
public class Achievement {

    private String title;
    private BufferedImage image;

    /**
     * Instantiates a new Achievement.
     *
     * @param title the title
     * @param path  the path
     */
    public Achievement(String title, String path) {
        this.title = title;
        this.image = loadImage(path);
    }

    /**
     * Load image buffered image.
     *
     * @param path the path
     * @return the buffered image
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Achievement.class.getResourceAsStream("client/user/" + path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Sets image path.
     *
     * @param path the path
     */
    public void setImagePath(String path) {
        setImage(loadImage(path));
    }
}

