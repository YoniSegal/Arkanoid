package gameobjects;

import gamelogic.Counter;
import gamelogic.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class keeps track of and displays the current number of lives remaining.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class LivesIndicator implements Sprite {
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

    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    public void drawOn(DrawSurface d) {
        //Draw score.
        String hit = "Lives: " + Integer.toString(this.lives.getValue());
        int x = (int) this.upperBlock.getCollisionRectangle().getLeft().middle().getX();
        int y = (int) this.upperBlock.getCollisionRectangle().getLeft().middle().getY() + 10;
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
