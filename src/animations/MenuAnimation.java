package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamelogic.MyGUI;
import gamelogic.SpriteVisitor;
import gameobjects.ColourBackground;
import gameobjects.Menu;

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
public class MenuAnimation<T> extends StoppableAnimation implements Menu<T> {
    private KeyboardSensor keyboard;
    private List<String> keys;
    private List<String> messages;
    private List<T> returnVals;
    private ArrayList<KeyPressStoppableAnimation> keydetect;
    private int hitkey;

    /**
     * Constructor sets arrayLists and keyboard.
     */
    public MenuAnimation() {
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.returnVals = new ArrayList<>();
        this.keyboard = MyGUI.getInstance(null, 0, 0).getKeyboardSensor();
        this.keydetect = new ArrayList<>();
    }

    /**
     * Method adds options for menu selection.
     *
     * @param key       String.
     * @param message   String.
     * @param returnVal T - our return value.
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVals.add(returnVal);
        this.keydetect.add(new KeyPressStoppableAnimation(key));
    }

    /**
     * Method returns the option that's been selected.
     *
     * @return T - status.
     */
    @Override
    public T getStatus() {
        for (int i = 0; i < keydetect.size(); i++) {
            if (this.shouldStop) {
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
                this.shouldStop = true;
                this.hitkey = j;
            }
            //keydetect.get(j).doOneFrame(d, dt);
            //this.stop = keydetect.get(j).shouldStop();
        }
    }
}
