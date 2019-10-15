package gameobjects;

import gamelogic.LevelInformation;
import gamelogic.Velocity;
import geometry.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class creates the information for the "Wide Easy" level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class WideEasy implements LevelInformation, Sprite {
    private static final int NUM_OF_BLOCKS = 15;
    private static final int NUM_OF_BALLS = 10;
    private static final int NUM_OF_LIVES = 4;
    private static final int PADDLE_SPEED = 2;
    private static final int BALL_SPEED = 10;
    private static final int RECT_WIDTH = 79;
    private static final int RECT_HEIGHT = 40;
    private Color color = Color.BLACK;
    private static final int MAX_WIDTH = 800 - 20;
    private static final int MAX_HEIGHT = 600;
    private static final int BORDER_GAP = 10;
    private static final double ERROR = 0.0001;

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
        int num = 11;
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            velocities.add(Velocity.fromAngleAndSpeed((310 + i * num) % 360, BALL_SPEED));
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
        return MAX_WIDTH - 200;
    }

    /**
     * Method returns the level name. It will be displayed at the top of the screen.
     *
     * @return String - level name.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite - background.
     */
    public Sprite getBackground() {
        return new ColourBackground(Color.WHITE);
    }

    /**
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     *
     * @return List of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            double x = BORDER_GAP + i * ((MAX_WIDTH / NUM_OF_BLOCKS) + ERROR);
            double y = MAX_HEIGHT / 3;
            Point point = new Point(x, y);
            Block block = new Block(point, MAX_WIDTH / NUM_OF_BLOCKS, RECT_HEIGHT / 2);
            Color colour = Color.BLACK;
            if (i < 2) {
                colour = Color.RED;
            } else if (i < 4) {
                colour = Color.ORANGE;
            } else if (i < 6) {
                colour = Color.YELLOW;
            } else if (i < 9) {
                colour = Color.GREEN;
            } else if (i < 11) {
                colour = Color.BLUE;
            } else if (i < 13) {
                colour = Color.PINK;
            } else if (i < 15) {
                colour = Color.CYAN;
            }
            block.setHits(1);
            block.setColour(colour);
            blocks.add(block);
        }
        return blocks;
    }

    /**
     * Number of levels that should be removed before the level is considered
     * to be "cleared". This number should be <= blocks.size();
     *
     * @return int - number of blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
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
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            Ball ball = new Ball(MAX_WIDTH / 2, MAX_HEIGHT - RECT_WIDTH, 5, Color.WHITE);
            balls.add(ball);
        }
        return balls;
    }

    /**
     * Method returns colour for countdown.
     *
     * @return Colour.
     */
    public Color countDownColour() {
        return Color.BLACK;
    }


    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        for (int i = 0; i < 1100; i += 10) {
            d.drawLine(MAX_WIDTH / 8, MAX_HEIGHT / 5, i, MAX_HEIGHT / 3);
        }
        Color colour = Color.yellow;
        d.setColor(Color.ORANGE);
        d.fillCircle(MAX_WIDTH / 8, MAX_HEIGHT / 5, 40);
        d.setColor(Color.ORANGE.brighter().brighter());
        d.fillCircle(MAX_WIDTH / 8, MAX_HEIGHT / 5, 30);
        d.setColor(colour.brighter().brighter());
        d.fillCircle(MAX_WIDTH / 8, MAX_HEIGHT / 5, 20);

    }

    /**
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {

    }
}
