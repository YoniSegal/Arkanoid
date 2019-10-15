package gamelogic;

import biuoop.DrawSurface;

/**
 * Animation interface with doOneFrame and shouldStop methods.
 *
 * @author Yonatan Segal
 * @version 1
 */
public interface Animation {
    /**
     * Method determines the drawing logic.
     *
     * @param d DrawSurface.
     * @param dt change in time.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Method determines the stopping condition.
     *
     * @return boolean.
     */
    boolean shouldStop();

    /**
     * Method assigns stopping condition for animation.
     *
     * @param stop - boolean.
     */
    void setStop(boolean stop);
}
