package tasks;

/**
 * Interfaces contains run method.
 *
 * @param <T> generic task.
 */
public interface Task<T> {
    /**
     * Method carries out a particular action and returns a generic value.
     *
     * @return T - generic value.
     */
    T run();


}
