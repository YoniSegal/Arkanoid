package gameobjects;

import gamelogic.*;
import levels.LevelInformation;

/**
 * Class keeps track of and displays the current level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class LevelIndicator extends Indicator {
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
        super(lives, upperBlock);
        this.levelInformation = levelInformation;
    }


    public LevelInformation getLevelInformation() {
        return levelInformation;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
