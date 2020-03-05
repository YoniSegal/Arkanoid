package gamelogic;

import animations.*;
import biuoop.DialogManager;
import gameobjects.HighScoresTable;
import levels.GameLevel;
import levels.LevelInformation;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class calls for levels to be initialised and loops through each level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class GameFlow {
    private AnimationRunner ar;
    private Counter score;
    private Counter lives;
    private HighScoresTable highScoresTable;
    private boolean win;

    /**
     * Constructor creates new counter holding the number of lives and score. It
     * also assigns a new keyboard sensor and animation runner.
     *
     * @param ar AnimationRunner.
     */
    public GameFlow(AnimationRunner ar) {
        this.ar = ar;
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.highScoresTable = new HighScoresTable(10);
        this.win = true;

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
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, ar, score, lives);
            level.initialize();
            while (level.getBlocksRemaining().getValue() != 0 && level.getLives().getValue() != 0) {
                level.playOneTurn();
            }
            if (level.getLives().getValue() == 0) {
                this.win = false;
                break;
            }
            this.score.increase(100);
        }

        this.ar.run(new FinalScreenAnimation(win, score.getValue()));
        if (this.score.getValue() >= 0 && this.highScoresTable.size() <= 5) {
            saveScoreToFile(this.score.getValue());
        }
        this.ar.run(new HighScoresAnimation(highScoresTable));

    }

    /**
     * Method saves current score to a highscores file.
     *
     * @param s int.
     */
    public void saveScoreToFile(int s) {
        DialogManager dialog = MyGUI.getInstance(null, 0, 0).getDialogManager();
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
