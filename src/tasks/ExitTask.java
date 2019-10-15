package tasks;

/**
 * Method runs an Exit task.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ExitTask implements Task<Void> {
    /**
     * Method carries out a particular action and returns a generic value.
     *
     * @return T - generic value.
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}
