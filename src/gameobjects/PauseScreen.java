package gameobjects;

import animations.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import animations.KeyPressStoppableAnimation;

import java.awt.Color;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * Class creates the information for the "Pause" screen.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;

    /**
     * Constructor assigns a keyboard sensor and determines whether the
     * animation should continue.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText((MAX_WIDTH / 2) - 210, MAX_HEIGHT / 2 + 100, "paused -- press space to continue", 32);
        d.setColor(Color.BLACK);
        d.drawCircle((MAX_WIDTH / 2), MAX_HEIGHT - 450 + 100, 120);
        d.setColor(Color.BLUE.brighter().brighter().brighter());
        d.fillCircle((MAX_WIDTH / 2), MAX_HEIGHT - 450 + 100, 110);
        d.setColor(Color.BLACK);
        d.fillCircle((MAX_WIDTH / 2), MAX_HEIGHT - 450 + 100, 100);
        d.setColor(Color.BLUE.brighter().brighter().brighter());
        d.fillRectangle((MAX_WIDTH / 2) + 5, MAX_HEIGHT - 400, 30, 100);
        d.fillRectangle((MAX_WIDTH / 2) - 35, MAX_HEIGHT - 400, 30, 100);
        KeyPressStoppableAnimation keyPress = new KeyPressStoppableAnimation(SPACE_KEY);
        keyPress.doOneFrame(d, dt);
        this.stop = keyPress.shouldStop();
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

    }
}
