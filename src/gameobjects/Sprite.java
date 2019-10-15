package gameobjects;

import biuoop.DrawSurface;

/**
 * gameobjects.Sprite interface is used by objects that can be drawn and keep track of time.
 *
 * @author Yonatan Segal
 * @version 1
 */
public interface Sprite {
    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    void drawOn(DrawSurface d);

    /**
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    void timePassed(double dt);
}
