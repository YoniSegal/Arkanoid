package gameobjects;

import gamelogic.Counter;
import levels.GameLevel;
import gamelogic.Visitable;
import gamelogic.Visitor;

/**
 * Class keeps track of and displays the current number of lives remaining.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class LivesIndicator extends Indicator {

    /**
     * Constructor assigns a counter for the number of lives, and the upper block.
     *
     * @param lives      Counter.
     * @param upperBlock Block.
     */
    public LivesIndicator(Counter lives, Block upperBlock) {
        super(lives, upperBlock);
    }

    public Counter getLives() {
        return counter;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
