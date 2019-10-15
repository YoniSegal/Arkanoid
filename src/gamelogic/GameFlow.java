package gamelogic;

import biuoop.DialogManager;
import biuoop.GUI;
import gameobjects.FinalScreenAnimation;
import biuoop.KeyboardSensor;
import gameobjects.HighScoresAnimation;
import gameobjects.HighScoresTable;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * Class calls for levels to be initialised and loops through each level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter score;
    private Counter lives;
    private HighScoresTable highScoresTable;
    private GUI gui;
    private boolean win;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;

    /**
     * Constructor creates new counter holding the number of lives and score. It
     * also assigns a new keyboard sensor and animation runner.
     *
     * @param ar  AnimationRunner.
     * @param ks  KeyboardSensor.
     * @param gui GUI.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.highScoresTable = new HighScoresTable(10);
        this.win = true;
        this.gui = gui;
        File file = new File("highscores.txt");
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
    }

    /**
     * Method loops through levels fo the game until victory or loss.
     *
     * @param levels List of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        int i = 0;
        for (LevelInformation levelInfo : levels) {
            if (i != 0) {
                this.score.increase(100);
            }
            GameLevel level = new GameLevel(levelInfo, ks, ar, score, lives);
            level.initialize();
            i++;

            while (level.getBlocksRemaining().getValue() != 0 && level.getLives().getValue() != 0) {
                level.playOneTurn();
            }
            if (level.getLives().getValue() == 0) {
                this.win = false;
                break;
            }
        }

        this.ar.run(new FinalScreenAnimation(ks, this.win, this.score.getValue(), ar));
        if (this.score.getValue() >= 0 && this.highScoresTable.size() <= 5) {
            saveScoreToFile(this.score.getValue());
        }
        this.ar.run(new HighScoresAnimation(highScoresTable, SPACE_KEY, ks, ar));

    }

    /**
     * Method saves current score to a highscores file.
     *
     * @param s int.
     */
    public void saveScoreToFile(int s) {
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");
        ScoreInfo scoreInfo = new ScoreInfo(name, s);
        highScoresTable.add(scoreInfo);
        try {
            highScoresTable.save(new File("highscores.txt"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
