package gameobjects;

import gamelogic.*;
import levels.GameLevel;
import levels.LevelInformation;

/**
 * Class keeps track of and displays the current level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class LevelIndicator implements Visitable {
    private Counter lives;
    private Block upperBlock;
    private LevelInformation levelInformation;

    /**
     * Constructor assigns a counter for the number of lives, the upperBlock, and
     * LevelInformation.
     *
     * @param lives            Counter.
     * @param upperBlock       Block.
     * @param levelInformation LevelInformation.
     */
    public LevelIndicator(Counter lives, Block upperBlock, LevelInformation levelInformation) {
        this.lives = lives;
        this.upperBlock = upperBlock;
        this.levelInformation = levelInformation;
    }


    public LevelInformation getLevelInformation() {
        return levelInformation;
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
//        gameLevel.addSprite(this);
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
