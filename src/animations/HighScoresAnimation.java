package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import animations.Animation;
import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import gamelogic.SpriteVisitor;
import gameobjects.ColourBackground;
import gameobjects.HighScoresTable;

import java.awt.Color;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * Class a creates a highscore animation.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class HighScoresAnimation extends StoppableAnimation {
    private HighScoresTable scores;
    private static final int MAX_WIDTH = 800;

    /**
     * Constructor assigns score, key, keyboard and an animation runner.
     *
     * @param scores HighScoresTable.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
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
        KeyPressStoppableAnimation keyPress = new KeyPressStoppableAnimation(SPACE_KEY);
        keyPress.doOneFrame(d, dt);
        if (keyPress.shouldStop()) {
            this.shouldStop = true;
        }
    }
}
