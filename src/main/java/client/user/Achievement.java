package client.user;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The type Achievement.
 */
public class Achievement {

    private int id;
    private String title;
    private String description;
    private String path;
    private BufferedImage image;
    private int progress;
    private int goal;
    private boolean achieved;

    /**
     * Instantiates a new Achievement.
     *
     * @param title the title
     * @param path  the path
     */
    public Achievement(int id, String title, String description, String path,
                       int progress, int goal, boolean achieved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.path = path;
        this.image = loadImage(path);
        this.progress = progress;
        this.goal = goal;
        this.progress = progress;
        this.achieved = achieved;
    }

    public Achievement() {
    }

    /**
     * Load image buffered image.
     *
     * @param path the path
     * @return the buffered image
     */
    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Achievement.class.getResourceAsStream(
                    "/client/windows/images/badges/"
                            + path + ".png"));
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
    public int getId() {
        return id;
    }

    /**
     * Sets title.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
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
     * Gets description.
     *
     * @return the title
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * This function will get the path String.
     *
     * @return the path to the image
     */
    public String getPath() {
        return path;
    }

    /**
     * This function will set the path of the image.
     *
     * @param path String type
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * This function will get the goal a user has to hit to achieve the achievement.
     *
     * @return the goal as an Integer
     */
    public int getGoal() {
        return goal;
    }

    /**
     * This function will set the goal of the achievement.
     *
     * @param goal int type
     */
    public void setGoal(int goal) {
        this.goal = goal;
    }

    /**
     * This function will set whether the achievement has been achieved.
     *
     * @param achieved boolean type
     */
    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    /**
     * Sets image path.
     *
     * @param path the path
     */
    public void setImageWithPath(String path) {
        setImage(loadImage(path));
    }

    /**
     * This function will get the progress of a user to achieve the achievement.
     *
     * @return the progress as an Integer
     */
    public int getProgress() {
        return progress;
    }

    /**
     * This function will set the progress of the achievement.
     *
     * @param progress int type
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }

    /**
     * This function will check wether the achievement is achieved or not.
     *
     * @return the achieved as an Boolean
     */
    public boolean isAchieved() {
        return achieved;
    }
}

