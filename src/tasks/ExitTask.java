package tasks;

/**
 * Method runs an Exit task.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ExitTask extends BaseTask {
    public ExitTask() {
        super(null, null);
    }

    /**
     * Method carries out a particular action and returns a generic value.
     *
     * @return T - generic value.
     */
    @Override
    public Void run() {
        System.exit(0);
        return null;
    }
}
