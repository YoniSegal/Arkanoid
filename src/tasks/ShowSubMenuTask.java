package tasks;

import animations.AnimationRunner;
import gameobjects.Menu;
import animations.MenuAnimation;

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
public class ShowSubMenuTask extends BaseTask {

    /**
     * Constructor sets a keyboard animationRunner and filePath.
     *
     * @param runner   AnimationRunner.
     * @param filePath String.
     */
    public ShowSubMenuTask(AnimationRunner runner, String filePath) {
        super(runner, filePath);
    }

    /**
     * Method runs levels.
     *
     * @return null.
     */
    public Void run() {
        try {
            readLevels();
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
    private void readLevels() throws FileNotFoundException {
        Menu<Task<Void>> menu = new MenuAnimation<>();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.filePath);
        Reader reader = new InputStreamReader(is);
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
                    menu.addSelection(key, message, new PlayGameTask(runner, path));
                    keyAndMessageChecked = false;
                    definitionsChecked = false;
                }
            }

            while (true) {
                menu.setStop(false);
                runner.run(menu);
                // wait for user selection
                Task<Void> task = menu.getStatus();
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
