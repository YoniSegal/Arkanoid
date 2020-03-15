package tasks;

import animations.AnimationRunner;
import gamelogic.GameFlow;
import levels.LevelInformation;
import levels.LevelSpecificationReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Method runs a PlayGame task.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class PlayGameTask extends BaseTask {
    /**
     * Constructor sets an animationRunner and a filePath.
     *
     * @param runner   AnimationRunner.
     * @param filePath String.
     */
    public PlayGameTask(AnimationRunner runner, String filePath) {
        super(runner, filePath);
    }

    /**
     * Method runs gametask.
     *
     * @return null.
     */
    @Override
    public Void run() {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.filePath);
        try {
            Reader reader = new InputStreamReader(is);
            LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
            List<LevelInformation> levelInformations = levelSpecificationReader.fromReader(reader);
            GameFlow gameFlow = new GameFlow(runner);
            gameFlow.runLevels(levelInformations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
