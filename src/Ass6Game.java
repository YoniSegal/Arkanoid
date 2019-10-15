import biuoop.GUI;
import gamelogic.AnimationRunner;
import gameobjects.Menu;
import gameobjects.MenuAnimation;
import tasks.ExitTask;
import tasks.ShowHiScoresTask;
import tasks.ShowSubMenuTask;
import tasks.Task;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Class initialises and runs game.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Ass6Game {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;

    /**
     * Main creates new game, initialises it and runs it.
     *
     * @param args String.
     * @throws FileNotFoundException if file can't be found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        GUI gui = new GUI("Arkanoid", MAX_WIDTH, MAX_HEIGHT);
        AnimationRunner runner = new AnimationRunner(60, gui);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(gui.getKeyboardSensor());
        ShowHiScoresTask showHiScoresTask = new ShowHiScoresTask(runner);

        //Get path
        String filePath = checkArgs(args);

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        Reader reader = inputStreamReader;


        //Assign menu.
        menu.addSelection("s", "Start game", new ShowSubMenuTask(runner, gui.getKeyboardSensor(), filePath, reader));
        menu.addSelection("h", "Hi scores", showHiScoresTask);
        menu.addSelection("q", "Quit", new ExitTask());
        //Loop through menu.
        while (true) {
            menu.setStop(false);
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            showHiScoresTask.getHsa().setStop(false);
            task.run();
        }
    }

    /**
     * Method checks for arguments and returns a new reader with a filepath.
     *
     * @param args String.
     * @return Reader.
     * @throws FileNotFoundException - if file can't be found.
     */
    public static String checkArgs(String[] args) throws FileNotFoundException {
        String filePath = null;
        if (args.length != 0) {
            filePath = args[0];
        } else {
            filePath = "level_sets.txt";
        }
        return filePath;
    }
}

