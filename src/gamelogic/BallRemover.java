package gamelogic;

import gameobjects.Ball;
import gameobjects.Block;
import levels.GameLevel;

/**
 * Class implements a hitListener to determine if a ball is to be removed from a game.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class BallRemover extends CounterHitListener {
    /**
     * Constructor assigns the number of balls and a game level.
     *
     * @param numBalls  Counter.
     * @param gameLevel GameLevel.
     */
    public BallRemover(Counter numBalls, GameLevel gameLevel) {
        super(numBalls, gameLevel);
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
        if (counter.getValue() > 0) {
            hitter.removeFromGame(this.gameLevel);
            this.gameLevel.getBallsRemaining().decrease(1);
        }
    }
}
