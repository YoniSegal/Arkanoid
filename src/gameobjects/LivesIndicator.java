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
public class LivesIndicator implements Visitable {
    private Counter lives;
    private Block upperBlock;

    /**
     * Constructor assigns a counter for the number of lives, and the upper block.
     *
     * @param lives      Counter.
     * @param upperBlock Block.
     */
    public LivesIndicator(Counter lives, Block upperBlock) {
        this.lives = lives;
        this.upperBlock = upperBlock;
    }

    public Counter getLives() {
        return lives;
    }

    public Block getUpperBlock() {
        return upperBlock;
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
