package gameobjects;

import gamelogic.LevelInformation;
import gamelogic.Velocity;
import geometry.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class creates the information for the "Direct Hit" level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class DirectHit implements LevelInformation, Sprite {
    private static final int BLOCK_LIFE = 1;
    private static final int NUM_OF_BALLS = 1;
    private static final int NUM_OF_LIVES = 4;
    private static final int PADDLE_SPEED = 6;
    private static final int BALL_SPEED = 10;
    private static final int RECT_WIDTH = 40;
    private Color color = Color.BLACK;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;
    private static final int BORDER_GAP = 10;

    /**
     * Method returns the number of balls in a given level.
     *
     * @return int number of balls in the level.
     */
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     * Method returns the initial velocity of each ball. Note that
     * initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            velocities.add(i, Velocity.fromAngleAndSpeed(0, BALL_SPEED));
        }
        return velocities;
    }

    /**
     * Method returns the speed of the paddle.
     *
     * @return int - speed.
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Method returns the width of a paddle.
     *
     * @return int - width of paddle.
     */
    public int paddleWidth() {
        return 2 * RECT_WIDTH;
    }

    /**
     * Method returns the level name. It will be displayed at the top of the screen.
     *
     * @return String - level name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite - background.
     */
    public Sprite getBackground() {
        return new ColourBackground(Color.BLACK);
    }

    /**
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     *
     * @return List of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point p = new Point(MAX_WIDTH / 2 - (RECT_WIDTH / 2), (MAX_HEIGHT / 3) + 10);
        Block block = new Block(p, RECT_WIDTH, 40);
        block.setColour(Color.RED);
        block.setHits(1);
        blocks.add(block);
        return blocks;
    }

    /**
     * Number of blocks that should be removed before the level is considered
     * to be "cleared". This number should be <= blocks.size();
     *
     * @return int - number of blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }


    /**
     * Method returns the number of lives in a given level.
     *
     * @return int - number of lives.
     */
    public int numOfLives() {
        return NUM_OF_LIVES;
    }

    /**
     * Method returns a list of balls.
     *
     * @return list of balls.
     */
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        balls.add(new Ball(MAX_WIDTH / 2, MAX_HEIGHT - RECT_WIDTH, 5, Color.white));
        return balls;
    }

    /**
     * Method returns colour for countdown.
     *
     * @return Colour.
     */
    public Color countDownColour() {
        return Color.white;
    }

    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    public void drawOn(DrawSurface d) {
        int x = MAX_WIDTH / 2;
        int y = 235;
        int lineLength = 5 * RECT_WIDTH + 220;
        d.setColor(Color.BLUE);
        for (int i = 0; i < 3; i++) {
            d.drawCircle(x, y, (i + 2) * RECT_WIDTH);
        }
        d.drawLine(x, lineLength, x, 60);
        d.drawLine(225, y, 575, y);
    }

    /**
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {

    }
}
