package gamelogic;

import gameobjects.Ball;
import gameobjects.Block;
import levels.GameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * ScoreTrackingListener determines when to update the score.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class ScoreTrackingListener extends CounterHitListener {
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructor assigns a counter to keep track of the score and a game level.
     *
     * @param currentScore Counter.
     * @param gameLevel    GameLevel.
     */
    public ScoreTrackingListener(Counter currentScore, GameLevel gameLevel) {
        super(currentScore, gameLevel);
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the gameobjects.Ball that's doing the hitting.
     *
     * @param beingHit gameobjects.Block being hit.
     * @param hitter   gameobjects.Ball hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //hitting block - 5 points
        //destroying block - 10 points
        if (beingHit.getHits().getValue() > 0) {
            this.counter.increase(5);
            beingHit.addHit();
        }
        if (beingHit.getHits().getValue() == 0) {
            this.counter.increase(10);
            removeHitListener(this);
        }
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl GameLogic.HitListener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
