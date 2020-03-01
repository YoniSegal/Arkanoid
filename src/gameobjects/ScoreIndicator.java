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
public class ScoreIndicator implements Visitable {
    private Counter score;
    private Block upperBlock;

    /**
     * Contructor assigns a counter for the score and the upperBlock.
     *
     * @param score      Counter.
     * @param upperBlock Block.
     */
    public ScoreIndicator(Counter score, Block upperBlock) {
        this.score = score;
        this.upperBlock = upperBlock;
    }

    public Block getUpperBlock() {
        return upperBlock;
    }

    public Counter getScore() {
        return score;
    }


    /**
     * Method adds block as a gameobjects.Sprite and a gameobjects.Collidable to the gameLevel.
     *
     * @param gameLevel GameLogic.GameLevel - gameLevel being played.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addVisitable(this);
    }


    /**
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
