package gameobjects;

import gamelogic.Animation;
import gamelogic.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamelogic.KeyPressStoppableAnimation;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * Class creates the information for the final screen.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class FinalScreenAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean win;
    private int score;
    private AnimationRunner animationRunner;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;
    private static final int RECT_WIDTH = 80;
    private static final int RECT_HEIGHT = 40;
    private static final int BORDER_GAP = 10;

    /**
     * Constructor assigns a keyboardSensor, whether there occurred a win or loss,
     * a score and an animation runner.
     *
     * @param k     KeyboardSensor.
     * @param win   boolean.
     * @param score int.
     * @param ar    AnimationRunner.
     */
    public FinalScreenAnimation(KeyboardSensor k, boolean win, int score, AnimationRunner ar) {
        this.keyboard = k;
        this.stop = false;
        this.win = win;
        this.score = score;
        this.animationRunner = ar;
    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (win) {
            d.drawText((MAX_WIDTH / 2) - 100, MAX_HEIGHT / 2, "You Win!", 50);
        } else if (!win) {
            d.drawText((MAX_WIDTH / 2) - 100, MAX_HEIGHT / 2, "You Lost", 50);
        }
        d.drawText((MAX_WIDTH / 2) - 200, MAX_HEIGHT / 2 + 100, "Press space to continue", 40);
        d.drawText(50, 550, "Final Score: " + this.score, 20);
        KeyPressStoppableAnimation keyPress = new KeyPressStoppableAnimation(keyboard, SPACE_KEY);
        keyPress.doOneFrame(d, dt);
        this.stop = keyPress.shouldStop();
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
     * Method assigns stopping condition for animation.
     *
     * @param s - boolean.
     */
    public void setStop(boolean s) {

    }
}
