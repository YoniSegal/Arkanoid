package tasks;

import gamelogic.Animation;
import gamelogic.AnimationRunner;
import gameobjects.HighScoresAnimation;
import gameobjects.HighScoresTable;

import java.io.File;
import java.io.IOException;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * Method runs a highscores task.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation hsa;
    private HighScoresTable highScoresTable;

    /**
     * Constructor.
     *
     * @param runner AnimationRunner.
     */
    public ShowHiScoresTask(AnimationRunner runner) {
        this.runner = runner;
        File file = new File("highscores.txt");
        this.highScoresTable = new HighScoresTable(5);
        if (file.exists() && !file.isDirectory()) {
            try {
                this.highScoresTable.load(file);
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
        this.hsa = new HighScoresAnimation(highScoresTable, SPACE_KEY, runner.getGui().getKeyboardSensor(), runner);
    }

    /**
     * run method.
     *
     * @return null.
     */
    public Void run() {
        this.runner.run(this.hsa);
        return null;
    }

    /**
     * get method.
     *
     * @return Animation.
     */
    public Animation getHsa() {
        return hsa;
    }
}
