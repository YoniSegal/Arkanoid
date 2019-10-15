package gameobjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamelogic.Animation;
import gamelogic.AnimationRunner;
import gamelogic.KeyPressStoppableAnimation;

import java.awt.Color;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * Class a creates a highscore animation.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private String endKey;
    private KeyboardSensor keyboard;
    private AnimationRunner ar;
    private boolean stop;
    private static final int MAX_WIDTH = 800;

    /**
     * Constructor assigns score, key, keyboard and an animation runner.
     *
     * @param scores HighScoresTable.
     * @param endKey endKey.
     * @param keyboard KeyboardSensor.
     * @param ar AnimationRunner.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor keyboard, AnimationRunner ar) {
        this.scores = scores;
        this.endKey = endKey;
        this.keyboard = keyboard;
        this.stop = false;
        this.ar = ar;
    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        ColourBackground colourBackground = new ColourBackground(Color.GRAY);
        colourBackground.drawOn(d);
        d.setColor(Color.YELLOW);
        d.drawText(100, 80, "High Scores", 40);
        d.setColor(Color.GREEN);
        d.drawText(150, 150, "Player Name", 40);
        d.drawText(500, 150, "Score", 40);
        d.fillRectangle(150, 160, 500, 5);
        d.setColor(Color.YELLOW.darker());
        int i;
        for (i = 0; i < scores.getHighScores().size(); i++) {
            d.drawText(150, 200 + 30 * i, scores.getHighScores().get(i).getName(), 20);
            d.drawText(500, 200 + 30 * i, Integer.toString(scores.getHighScores().get(i).getScore()), 20);
        }
        d.setColor(Color.BLUE.darker().darker());
        d.drawText((MAX_WIDTH / 2) - 250, 240 + 25 * i, "Press space to continue", 40);
        KeyPressStoppableAnimation keyPress = new KeyPressStoppableAnimation(keyboard, SPACE_KEY);
        keyPress.doOneFrame(d, dt);
        if (keyPress.shouldStop()) {
            this.stop = true;
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
     * Method assigns stopping condition for animation.
     *
     * @param s - boolean.
     */
    public void setStop(boolean s) {
        this.stop = s;
    }
}
