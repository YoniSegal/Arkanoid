package gameobjects;

import gamelogic.Visitable;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Interface hold methods to return images and colours.
 *
 * @author Yonatan Segal
 * @version 1
 */
public interface Background extends Visitable {

    /**
     * Method returns the colour used in the background and null if none was used.
     *
     * @return Colour.
     */
    Color getColour();

    /**
     * Method returns image used in the background and null if none was used.
     *
     * @return BufferedImage.
     */
    BufferedImage getImage();
}
