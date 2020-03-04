package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamelogic.MyGUI;

/**
 * Class checks if a key has been pressed to stop its animation.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class KeyPressStoppableAnimation extends StoppableAnimation {
    private KeyboardSensor sensor;
    private String key;
    private boolean isAlreadyPressed;

    /**
     * Constructor assigns a keyboardsensor, key, and booleans.
     *
     * @param key    string.
     */
    public KeyPressStoppableAnimation(String key) {
        this.sensor = MyGUI.getInstance(null, 0, 0).getKeyboardSensor();
        this.key = key;
        this.isAlreadyPressed = true;
    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        isAlreadyPressed = sensor.isPressed(key);
    }

    @Override
    public boolean shouldStop() {
        return this.isAlreadyPressed;
    }
}

