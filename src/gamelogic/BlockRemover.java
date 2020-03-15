package gamelogic;

import gameobjects.Ball;
import gameobjects.Block;
import levels.GameLevel;

/**
 * Class implements a hitListener to determine if a block is to be removed from a game.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class BlockRemover extends CounterHitListener {
    /**
     * Constructor receives a GameLevel and counter and assigns them to BlockRemover.
     *
     * @param remainingBlocks number of blocks removed.
     * @param gameLevel       GameLevel being played.
     */
    public BlockRemover(Counter remainingBlocks, GameLevel gameLevel) {
        super(remainingBlocks, gameLevel);
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the
     * gameLevel. Remember to remove this listener from the block that is being
     * removed from the gameLevel.
     *
     * @param beingHit gameobjects.Block being hit.
     * @param hitter   gameobjects.Ball hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits().getValue() == 1) {
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this);
            this.gameLevel.getBlocksRemaining().decrease(1);
        }
    }
}
