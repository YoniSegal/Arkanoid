package gameobjects;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Method holds a list of Sprited.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor assigns an array list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList();
    }

    /**
     * Method returns the lost of Sprites.
     *
     * @return SpriteCollection.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Method adds a gameobjects.Sprite.
     *
     * @param s gameobjects.Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Method calls timePassed() on all sprites.
     *
     * @param dt change in time.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < sprites.size(); i++) {
            //Inform all Sprites that time has passed.
            sprites.get(i).timePassed(dt);
        }
    }

    /**
     * Method calls drawOn(d) on all sprites.
     *
     * @param d DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            //Call all sprites drawOn methods.
            sprites.get(i).drawOn(d);
        }
    }
}
