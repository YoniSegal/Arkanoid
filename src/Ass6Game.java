import animations.AnimationRunner;
import biuoop.GUI;
import gamelogic.MyGUI;
import gameobjects.Menu;
import animations.MenuAnimation;
import tasks.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

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
        GUI gui = MyGUI.getInstance("Arkanoid", MAX_WIDTH, MAX_HEIGHT);
        AnimationRunner runner = new AnimationRunner(60);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>();
        ShowHighScoresTask showHighScoresTask = new ShowHighScoresTask(runner, "highscores.txt");


        //Get path
        String filePath = checkArgs(args);

        Map<Tasks, BaseTask> map = new HashMap<Tasks, BaseTask>() {{
            put(Tasks.EXIT, new ExitTask());
            put(Tasks.PLAY_GAME, new PlayGameTask(runner, filePath));
            put(Tasks.SHOW_HIGH_SCORES, new ShowHighScoresTask(runner, "highscores.txt"));
            put(Tasks.SHOW_SUB_MENU, new ShowSubMenuTask(runner, filePath));
        }};

        //Assign menu.
        menu.addSelection("s", "Start game", map.get(Tasks.SHOW_SUB_MENU));
        menu.addSelection("h", "Hi scores", showHighScoresTask);
        menu.addSelection("q", "Quit", new ExitTask());
        //Loop through menu.
        while (true) {
            menu.setStop(false);
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            showHighScoresTask.getHighScoreAnimation().setStop(false);
            task.run();
        }
    }

    /**
     * Method checks for arguments and returns a new reader with a filepath.
     *
     * @param args String.
     * @return Reader.
     */
    private static String checkArgs(String[] args) {
        String filePath = null;
        if (args.length != 0) {
            filePath = args[0];
        } else {
            filePath = "level_sets.txt";
        }
        return filePath;
    }
}

