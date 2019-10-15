package gameobjects;

import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class holds a colour and creates a background for a level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ColourBackground implements Sprite, Background {
    private Color color;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;
    private static final int BORDER_GAP = 10;

    /**
     * constructor  assigns a colour and level information to the background.
     *
     * @param color Colour.
     */
    public ColourBackground(Color color) {
        this.color = color;
    }

    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(BORDER_GAP, 3 * BORDER_GAP, MAX_WIDTH, MAX_HEIGHT);
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
        return this.color;
    }

    /**
     * Method returns image used in the background and null if none was used.
     *
     * @return BufferedImage.
     */
    public BufferedImage getImage() {
        return null;
    }
}
