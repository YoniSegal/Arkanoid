package gameobjects;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class a creates an image background.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ImageBackground implements Sprite, Background {
    private BufferedImage image;

    /**
     * Method returns an image background.
     *
     * @param filePath File.
     */
    public ImageBackground(String filePath) {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
            this.image = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Image not found");
        }
    }

    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    public void drawOn(DrawSurface d) {
        // Draw the image on a DrawSurface
        d.drawImage(10, 20, this.image); // draw the image at location 10, 20.
    }

    /**
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {

    }

    /**
     * Method returns the colour used in the background and null if none was used.
     *
     * @return Colour.
     */
    public Color getColour() {
        return null;
    }

    /**
     * Method returns the image used in the background and null if none was used.
     *
     * @return BufferedImage.
     */
    public BufferedImage getImage() {
        return image;
    }
}
