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
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor receives a GameLevel and counter and assigns them to BlockRemover.
     *
     * @param gameLevel       GameLevel being played.
     * @param remainingBlocks number of blocks removed.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the
     * gameLevel. Remember to remove this listener from the block that is being
     * removed from the gameLevel.
     *
     * @param beingHit gameobjects.Block being hit.
     * @param hitter   gameobjects.Ball hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits().getValue() == 1) {
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this);
            this.gameLevel.getBlocksRemaining().decrease(1);
        }
    }
}
