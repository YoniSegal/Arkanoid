package gameobjects;

import gamelogic.LevelInformation;
import gamelogic.Velocity;
import geometry.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class creates the information for the "Green 3" level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Green3 implements LevelInformation, Sprite {
    private static final int NUM_OF_BLOCKS = 40;
    private static final int NUM_OF_BALLS = 2;
    private static final int NUM_OF_LIVES = 4;
    private static final int PADDLE_SPEED = 10;
    private static final int BALL_SPEED = 8;
    private static final int RECT_WIDTH = 50;
    private static final int RECT_HEIGHT = 25;
    private static final int BLOCK_LIFE = 1;
    private Color color = Color.BLACK;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;
    private static final int BORDER_GAP = 10;
    private static final double ERROR = 0.0001;
    private static final int W_HEIGHT = 40;
    private static final int W_WIDTH = 15;
    private static final int FIRST_ROW_BLOCKS = 10;

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
        velocities.add(0, Velocity.fromAngleAndSpeed(45, BALL_SPEED));
        velocities.add(1, Velocity.fromAngleAndSpeed(-45, BALL_SPEED));
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
        return "Green 3";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite - background.
     */
    public Sprite getBackground() {
        return new ColourBackground(Color.GREEN.darker().darker());
    }

    /**
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     *
     * @return List of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int ballsRowOne = 10;
        //Loop through each row.
        for (int i = 0; i < 5; i++) {
            //Loop through each block in each row.
            for (int j = 0; j < ballsRowOne; j++) {
                double x = (MAX_WIDTH - ((j + 1) * RECT_WIDTH) + ERROR) - BORDER_GAP;
                double y = MAX_HEIGHT - 500 + i * RECT_HEIGHT;
                Point point = new Point(x, y);
                Block block = new Block(point, RECT_WIDTH, RECT_HEIGHT);
                if (i == 0) {
                    block.setHits(2);
                } else {
                    block.setHits(1);
                }
                setColor(block, i);
                blocks.add(block);
            }
            ballsRowOne--;
        }
        return blocks;
    }

    /**
     * Method sets the colour for a block.
     *
     * @param block Block.
     * @param i     int - row.
     */
    public void setColor(Block block, int i) {
        if (i < 1) {
            block.setColour(Color.GRAY);
        } else if (i < 2) {
            block.setColour(Color.RED);
        } else if (i < 3) {
            block.setColour(Color.YELLOW);
        } else if (i < 4) {
            block.setColour(Color.BLUE);
        } else if (i < 5) {
            block.setColour(Color.WHITE);
        }
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
        return Color.WHITE;
    }

    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(100, 400, 125, 250);
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(115 + (W_WIDTH + 5) * i, 415 + j * (W_HEIGHT + 5), W_WIDTH, W_HEIGHT);
            }
        }
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(145, 340, 35, 60);
        d.setColor(Color.GRAY.darker());
        d.fillRectangle(157, 140, 10, 200);
        d.setColor(Color.ORANGE);
        d.fillCircle(161, 130, 12);
        d.setColor(Color.RED);
        d.fillCircle(161, 130, 7);
        d.setColor(Color.white);
        d.fillCircle(161, 130, 3);
    }

    /**
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {

    }
}
