package tasks;

import animations.AnimationRunner;

public class BaseTask implements Task<Void> {
    protected AnimationRunner runner;
    protected String filePath;


    public BaseTask(AnimationRunner runner, String filePath) {
        this.runner = runner;
        this.filePath = filePath;
    }

    @Override
    public Void run() {
        return null;
    }
}
