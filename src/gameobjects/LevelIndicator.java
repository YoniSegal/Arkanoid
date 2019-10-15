package gameobjects;

import gamelogic.Counter;
import gamelogic.GameLevel;
import gamelogic.LevelInformation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class keeps track of and displays the current level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class LevelIndicator implements Sprite {
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

    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    public void drawOn(DrawSurface d) {
        //Draw level.
        String hit = "Level: " + levelInformation.levelName();
        int x = (int) this.upperBlock.getCollisionRectangle().getRight().middle().getX() - 180;
        int y = (int) this.upperBlock.getCollisionRectangle().getRight().middle().getY() + 10;
        d.setColor(Color.BLACK);
        d.drawText(x, y, hit, 20);
    }

    /**
     * Method adds block as a gameobjects.Sprite and a gameobjects.Collidable to the gameLevel.
     *
     * @param gameLevel GameLogic.GameLevel - gameLevel being played.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {

    }
}
