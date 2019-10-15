package tasks;

import biuoop.KeyboardSensor;
import gamelogic.AnimationRunner;
import gameobjects.Menu;
import gameobjects.MenuAnimation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * Method runs a subMenu task.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ShowSubMenuTask implements Task<Void> {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private String filePath;
    private Reader fileReader;

    /**
     * Constructor sets a keyboard animationRunner and filePath.
     *
     * @param runner         AnimationRunner.
     * @param keyboardSensor KeyboardSensor.
     * @param filePath       String.
     * @param fileReader     Reader.
     */
    public ShowSubMenuTask(AnimationRunner runner, KeyboardSensor keyboardSensor, String filePath, Reader fileReader) {
        this.keyboardSensor = keyboardSensor;
        this.runner = runner;
        this.filePath = filePath;
        this.fileReader = fileReader;
    }

    /**
     * Method runs levels.
     *
     * @return null.
     */
    public Void run() {
        try {
            readLeveles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method reads level sets from a file.
     *
     * @throws FileNotFoundException exception.
     */
    public void readLeveles() throws FileNotFoundException {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboardSensor);
        ShowHiScoresTask showHiScoresTask = new ShowHiScoresTask(runner);

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        Reader reader = inputStreamReader;
        LineNumberReader lnr = new LineNumberReader(reader);

        try {
            String line;
            String key = null;
            String message = null;
            String path = null;
            boolean keyAndMessageChecked = false;
            boolean definitionsChecked = false;
            while ((line = lnr.readLine()) != null) {
                if (line.contains(":")) {
                    String[] split = line.split(":");
                    key = split[0];
                    message = split[1];
                    keyAndMessageChecked = true;
                }
                if (line.contains("definitions")) {
                    String[] split = line.split("\n");
                    path = split[0];
                    definitionsChecked = true;
                }
                if (keyAndMessageChecked && definitionsChecked) {
                    menu.addSelection(key, message, new PlayGameTask(runner, path, reader));
                    keyAndMessageChecked = false;
                    definitionsChecked = false;
                }
            }

            while (true) {
                menu.setStop(false);
                runner.run(menu);
                // wait for user selection
                Task<Void> task = menu.getStatus();
                showHiScoresTask.getHsa().setStop(false);
                task.run();
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lnr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
