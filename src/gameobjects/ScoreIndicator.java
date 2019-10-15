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
public class ScoreIndicator implements Sprite {
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

    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    public void drawOn(DrawSurface d) {
        int upperleftX = (int) this.upperBlock.getCollisionRectangle().getUpperLeft().getX();
        int upperleftY = (int) this.upperBlock.getCollisionRectangle().getUpperLeft().getY();
        int width = (int) this.upperBlock.getCollisionRectangle().getWidth();
        int height = (int) this.upperBlock.getCollisionRectangle().getHeight();
        //Check if colour has been initialised.
        if (this.upperBlock.getColour() == null) {
            d.setColor(Color.LIGHT_GRAY);
        } else {
            d.setColor(this.upperBlock.getColour());
        }
        //Draw rectangles.
        d.fillRectangle(upperleftX, upperleftY, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(upperleftX, upperleftY, width, height);
        //Draw score.
        String hit = "Score: " + Integer.toString(this.score.getValue());
        int x = (int) this.upperBlock.getCollisionRectangle().getTop().middle().getX() - 30;
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
