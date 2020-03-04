package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gameobjects.VisitableCollection;

import java.awt.Color;

/**
 * Class creates an animation which counts down until the start of a level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class CountdownAnimation extends StoppableAnimation {
    private double numOfSeconds;
    private int countFrom;
    private int temp;
    private Color color;
    private VisitableCollection visitableCollection;

    /**
     * Constructor assigns the number of seconds to count, where to count from,
     * a SpriteCollection and a colour.
     *
     * @param numOfSeconds        double.
     * @param countFrom           int.
     * @param color               Colour.
     * @param visitableCollection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, Color color, VisitableCollection visitableCollection) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.visitableCollection = visitableCollection;
        this.temp = countFrom;
        this.color = color;
    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        Sleeper sleeper = new Sleeper();
        long sleepLength = (long) (1000 * numOfSeconds / temp);
        if (!shouldStop) {
            if (countFrom > 0) {
                visitableCollection.visitAll(d);
                d.setColor(Color.BLACK);
                d.drawText((d.getWidth() / 2) - 1, (d.getHeight() / 2) - 1, Integer.toString(countFrom - 1), 36);
                if (this.color == null) {
                    d.setColor(Color.WHITE);
                } else {
                    d.setColor(this.color);
                }
                d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom - 1), 32);
                d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom - 1), 32);
                if (temp > countFrom) {
                    sleeper.sleepFor(sleepLength);
                }
                this.countFrom--;
            } else {
                this.shouldStop = true;
            }
        }
    }
}
