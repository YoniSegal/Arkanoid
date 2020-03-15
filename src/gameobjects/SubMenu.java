package gameobjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import animations.KeyPressStoppableAnimation;
import gamelogic.SpriteVisitor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class runs a submenu.
 *
 * @param <T> generic task.
 * @author Yonatan Segal
 * @version 1
 */
public class SubMenu<T> implements Menu<T> {
    private boolean stop;
    private KeyboardSensor keyboard;
    private java.util.List<String> keys;
    private java.util.List<String> messages;
    private List<T> returnVals;
    private ArrayList<KeyPressStoppableAnimation> keydetect;
    private int hitkey;

    /**
     * Method adds options for menu selection.
     *
     * @param key       String.
     * @param message   String.
     * @param returnVal T - our return value.
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {

    }

    /**
     * Method returns the option that's been selected.
     *
     * @return T - status.
     */
    @Override
    public T getStatus() {
        for (int i = 0; i < keydetect.size(); i++) {
            if (this.stop) {
                return returnVals.get(this.hitkey);
            }
        }
        return null;
    }

    /**
     * Method adds a submenu to select from.
     *
     * @param key     String.
     * @param message String.
     * @param subMenu Menu - submenu.
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {

    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        ColourBackground colourBackground = new ColourBackground(Color.GRAY);
        colourBackground.accept(new SpriteVisitor(d));
        d.setColor(Color.YELLOW.brighter());
        d.drawText(100, 80, "Arkanoid", 40);
        d.setColor(Color.GREEN);
        for (int i = 0; i < keys.size(); i++) {
            d.drawText(150, 130 + 40 * i, "(" + keys.get(i) + ")" + " " + messages.get(i), 35);
        }
        for (int j = 0; j < keys.size(); j++) {
            if (keyboard.isPressed(keys.get(j))) {
                this.stop = true;
                this.hitkey = j;
            }
        }
    }

    /**
     * Method determines the stopping condition.
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Method assigns stopping condition for animation.
     *
     * @param s - boolean.
     */
    @Override
    public void setStop(boolean s) {
        this.stop = s;
    }
}
