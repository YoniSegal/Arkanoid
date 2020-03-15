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
public class ScoreIndicator extends Indicator {

    /**
     * Contructor assigns a counter for the score and the upperBlock.
     *
     * @param score      Counter.
     * @param upperBlock Block.
     */
    public ScoreIndicator(Counter score, Block upperBlock) {
        super(score, upperBlock);
    }

    public Counter getScore() {
        return counter;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
