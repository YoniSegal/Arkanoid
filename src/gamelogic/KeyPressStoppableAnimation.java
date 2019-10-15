package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class checks if a key has been pressed to stop its animation.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private boolean isAlreadypressed;
    private boolean stop;

    /**
     * Constructor assigns a keyboardsensor, key, and booleans.
     *
     * @param sensor keyboard.
     * @param key    string.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key) {
        this.sensor = sensor;
        this.key = key;
        this.isAlreadypressed = true;
        this.stop = false;
    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (!sensor.isPressed(key)) {
            isAlreadypressed = false;
        } else {
            isAlreadypressed = true;
        }

    }

    /**
     * Method determines the stopping condition.
     *
     * @return boolean.
     */
    public boolean shouldStop() {
        return isAlreadypressed;
    }

    /**
     * Method assigns stopping condition for animation.
     *
     * @param s - boolean.
     */
    public void setStop(boolean s) {

    }
}

