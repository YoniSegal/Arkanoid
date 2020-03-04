package animations;

import biuoop.DrawSurface;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * Class creates the information for the final screen.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class FinalScreenAnimation extends StoppableAnimation {
    private boolean win;
    private int score;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;

    /**
     * Constructor assigns a keyboardSensor, whether there occurred a win or loss,
     * a score and an animation runner.
     * <p>
     *
     * @param win   boolean.
     * @param score int.
     */
    public FinalScreenAnimation(boolean win, int score) {
        this.win = win;
        this.score = score;
    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (win) {
            d.drawText((MAX_WIDTH / 2) - 100, MAX_HEIGHT / 2, "You Win!", 50);
        } else if (!win) {
            d.drawText((MAX_WIDTH / 2) - 100, MAX_HEIGHT / 2, "You Lost", 50);
        }
        d.drawText((MAX_WIDTH / 2) - 200, MAX_HEIGHT / 2 + 100, "Press space to continue", 40);
        d.drawText(50, 550, "Final Score: " + this.score, 20);
        KeyPressStoppableAnimation keyPress = new KeyPressStoppableAnimation(SPACE_KEY);
        keyPress.doOneFrame(d, dt);
        this.shouldStop = keyPress.shouldStop();
    }
}
