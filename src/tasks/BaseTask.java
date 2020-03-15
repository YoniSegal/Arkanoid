package tasks;

import animations.AnimationRunner;

public abstract class BaseTask implements Task<Void> {
    protected AnimationRunner runner;
    protected String filePath;


    public BaseTask(AnimationRunner runner, String filePath) {
        this.runner = runner;
        this.filePath = filePath;
    }
}
