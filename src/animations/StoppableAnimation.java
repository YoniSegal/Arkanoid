package animations;

import biuoop.DrawSurface;

public class StoppableAnimation implements Animation {
    protected boolean shouldStop = false;

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
    }

    /**
     * Method determines the stopping condition.
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }

    /**
     * Method assigns stopping condition for animation.
     *
     * @param stop - boolean.
     */
    @Override
    public void setStop(boolean stop) {
        this.shouldStop = stop;
    }
}
