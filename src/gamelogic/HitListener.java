package gamelogic;

import gameobjects.Ball;
import gameobjects.Block;

/**
 * Objects that want to be notified of hit events, should implement the
 * GameLogic.HitListener interface.
 *
 * @author Yonatan Segal
 * @version 1
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the gameobjects.Ball that's doing the hitting.
     *
     * @param beingHit gameobjects.Block being hit.
     * @param hitter   gameobjects.Ball hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
