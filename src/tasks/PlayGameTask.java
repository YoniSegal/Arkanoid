package tasks;

import gamelogic.AnimationRunner;
import gamelogic.GameFlow;
import gamelogic.LevelInformation;
import gamelogic.LevelSpecificationReader;

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
public class PlayGameTask implements Task<Void> {
    private AnimationRunner runner;
    private String filePath;
    private Reader fileReader;

    /**
     * Constructor sets an animationRunner and a filePath.
     *
     * @param runner     AnimationRunner.
     * @param filePath   String.
     * @param fileReader Reader.
     */
    public PlayGameTask(AnimationRunner runner, String filePath, Reader fileReader) {
        this.runner = runner;
        this.filePath = filePath;
        this.fileReader = fileReader;
    }

    /**
     * Method runs gametask.
     *
     * @return null.
     */
    public Void run() {
        Reader reader = null;
        List<LevelInformation> levelInformations = new ArrayList<>();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        reader = inputStreamReader;

        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        levelInformations = levelSpecificationReader.fromReader(reader);
        GameFlow gameFlow = new GameFlow(runner, runner.getGui().getKeyboardSensor(), runner.getGui());
        gameFlow.runLevels(levelInformations);
        return null;
    }
}
