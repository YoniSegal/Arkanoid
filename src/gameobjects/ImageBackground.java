package gameobjects;

import gamelogic.Visitable;
import gamelogic.Visitor;

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
public class ImageBackground implements Visitable, Background {
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
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * Method returns the colour used in the background and null if none was used.
     *
     * @return Colour.
     */
    @Override
    public Color getColour() {
        return null;
    }

    /**
     * Method returns the image used in the background and null if none was used.
     *
     * @return BufferedImage.
     */
    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
