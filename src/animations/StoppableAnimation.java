package animations;


public abstract class StoppableAnimation implements Animation {
    protected boolean shouldStop = false;


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
