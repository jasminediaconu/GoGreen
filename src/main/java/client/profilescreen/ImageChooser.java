package client.profilescreen;

import client.windows.MainScreen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javafx.stage.FileChooser;

/**
 * The type Image chooser.
 */
public class ImageChooser {

    private static void configureFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }


    /**
     * Gets buffered image.
     *
     * @return the buffered image
     */
    public BufferedImage getBufferedImage() {

        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        File file = fileChooser.showOpenDialog(MainScreen.getPrimaryStage());

        if (file == null) {
            return null;
        }

        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
