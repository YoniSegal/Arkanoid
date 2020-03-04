package tasks;

import animations.Animation;
import animations.AnimationRunner;
import animations.HighScoresAnimation;
import gameobjects.HighScoresTable;

import java.io.File;
import java.io.IOException;

/**
 * Method runs a highscores task.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ShowHighScoresTask extends BaseTask {
    private Animation highScoreAnimation;

    /**
     * Constructor.
     *
     * @param runner AnimationRunner.
     */
    public ShowHighScoresTask(AnimationRunner runner, String filePath) {
        super(runner, filePath);
        initialiseHighScoreAnimation(new File(filePath));
    }

    private void initialiseHighScoreAnimation(File file) {
        HighScoresTable highScoresTable = new HighScoresTable(5);
        if (file.exists() && !file.isDirectory()) {
            try {
                highScoresTable.load(file);
            } catch (IOException e) {
                System.err.println("failed to load file");
                e.printStackTrace(System.err);
                return;
            }
        } else {
            try {
                highScoresTable.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.highScoreAnimation = new HighScoresAnimation(highScoresTable);
    }

    /**
     * run method.
     *
     * @return null.
     */
    public Void run() {
        this.runner.run(this.highScoreAnimation);
        return null;
    }

    /**
     * get method.
     *
     * @return Animation.
     */
    public Animation getHighScoreAnimation() {
        return highScoreAnimation;
    }
}
