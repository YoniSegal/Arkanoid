package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import gamelogic.MyGUI;

/**
 * AnimationRunner class creates a gui and runs an animation.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class AnimationRunner {
    private int framesPerSecond;
    private Sleeper sleeper;
    private double dt;

    /**
     * Constructor creates a new gui and sleeper and declares how many frames
     * per second to be shown.
     *
     * @param framesPerSecond int - frames/second.
     */
    public AnimationRunner(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
        this.dt = 1.0 / this.framesPerSecond;
    }

    /**
     * Method loops though an animation and displays a gui's contents after one frame.
     *
     * @param animation Animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        GUI gui = MyGUI.getInstance(null, 0, 0);
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, this.dt);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
