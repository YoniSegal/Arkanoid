package gameobjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamelogic.Animation;
import gamelogic.KeyPressStoppableAnimation;
import gamelogic.SpriteVisitor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class runs a menuAnimation.
 *
 * @param <T> generic task.
 * @author Yonatan Segal
 * @version 1
 */
public class MenuAnimation<T> implements Menu<T>, Animation {
    private boolean stop;
    private KeyboardSensor keyboard;
    //private String message;
    private List<String> keys;
    private List<String> messages;
    private List<T> returnVals;
    private ArrayList<KeyPressStoppableAnimation> keydetect;
    private int hitkey;

    /**
     * Constructor sets arrayLists and keyboard.
     *
     * @param keyboard KeyboardSensor.
     */
    public MenuAnimation(KeyboardSensor keyboard) {
        this.stop = false;
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.returnVals = new ArrayList<>();
        this.keyboard = keyboard;
        this.keydetect = new ArrayList<>();
    }

    /**
     * Method adds options for menu selection.
     *
     * @param key       String.
     * @param message   String.
     * @param returnVal T - our return value.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVals.add(returnVal);
        this.keydetect.add(new KeyPressStoppableAnimation(keyboard, key));
    }

    /**
     * Method returns the option that's been selected.
     *
     * @return T - status.
     */
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
    public void addSubMenu(String key, String message, Menu<T> subMenu) {

    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
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
            //keydetect.get(j).doOneFrame(d, dt);
            //this.stop = keydetect.get(j).shouldStop();
        }

    }

    /**
     * Method determines the stopping condition.
     *
     * @return boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Method sets the stop boolean.
     *
     * @param s - boolean.
     */
    public void setStop(boolean s) {
        this.stop = s;
    }
}
