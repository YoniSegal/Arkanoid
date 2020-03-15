package gameobjects;

import biuoop.DrawSurface;
import gamelogic.Visitable;
import gamelogic.Visitor;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class holds a colour and creates a background for a level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ColourBackground implements Visitable, Background {
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

    public static int getMaxWidth() {
        return MAX_WIDTH;
    }

    public static int getMaxHeight() {
        return MAX_HEIGHT;
    }

    public static int getBorderGap() {
        return BORDER_GAP;
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
