package gameobjects;

import gamelogic.LevelInformation;
import gamelogic.Velocity;
import geometry.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class creates the information for the "Final Four" level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class FinalFour implements LevelInformation, Sprite {
    private static final int NUM_OF_BLOCKS = 105;
    private static final int NUM_OF_BALLS = 3;
    private static final int PADDLE_SPEED = 10;
    private static final int BALL_SPEED = 8;
    private static final int RECT_WIDTH = 79;
    private static final int RECT_HEIGHT = 40;
    private static final int NUM_OF_LIVES = 4;
    private Color color = Color.BLACK;
    private static final int MAX_WIDTH = 800 - 20;
    private static final int MAX_HEIGHT = 600;
    private static final int BORDER_GAP = 10;
    private static final double ERROR = 0.0001;
    private static final int WINDOW_HEIGHT = 40;
    private static final int WINDOW_WIDTH = 15;
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
        velocities.add(Velocity.fromAngleAndSpeed(35, BALL_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(-35, BALL_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(0, BALL_SPEED));
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
        return RECT_WIDTH;
    }

    /**
     * Method returns the level name. It will be displayed at the top of the screen.
     *
     * @return String - level name.
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite - background.
     */
    public Sprite getBackground() {
        Color colour = Color.BLUE.brighter().brighter().brighter().brighter().brighter().brighter();
        return new ColourBackground(colour.brighter().brighter().brighter().brighter());
    }

    /**
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     *
     * @return List of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                double x = (MAX_WIDTH - (j + 1) * (MAX_WIDTH / 15 + ERROR)) + 10;
                double y = MAX_HEIGHT - 500 + i * (RECT_HEIGHT / 2);
                geometry.Point point = new Point(x, y);
                Block block = new Block(point, MAX_WIDTH / 15, RECT_HEIGHT / 2);
                if (i == 0) {
                    block.setHits(2);
                } else {
                    block.setHits(1);
                }
                setColor(block, i);
                blocks.add(block);
            }
        }
        return blocks;
    }

    /**
     * Method sets the colour for a block.
     *
     * @param block Block.
     * @param i     Row.
     */
    public void setColor(Block block, int i) {
        if (i < 1) {
            block.setColour(Color.GRAY);
        } else if (i < 2) {
            block.setColour(Color.RED);
        } else if (i < 3) {
            block.setColour(Color.YELLOW);
        } else if (i < 4) {
            block.setColour(Color.GREEN);
        } else if (i < 5) {
            block.setColour(Color.WHITE);
        } else if (i < 6) {
            block.setColour(Color.PINK);
        } else if (i < 7) {
            block.setColour(Color.CYAN);
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
        d.setColor(Color.WHITE);
        for (int i = 0; i < 11; i++) {
            d.drawLine(120 + 8 * i, 400, 70 + 8 * i, 800);
        }
        for (int i = 0; i < 11; i++) {
            d.drawLine(520 + 8 * i, 400, 470 + 8 * i, 800);
        }
        for (int j = 0; j < 2; j++) {
            d.setColor(Color.GRAY);
            d.fillCircle(130 + 400 * j, 420 + 30 * j, 30);
            d.setColor(Color.GRAY.brighter());
            d.fillCircle(120 + 400 * j, 390 + 30 * j, 25);
            d.setColor(Color.GRAY);
            d.fillCircle(150 + 400 * j, 385 + 30 * j, 30);
            d.setColor(Color.GRAY.brighter());
            d.fillCircle(160 + 400 * j, 430 + 30 * j, 20);
            d.setColor(Color.GRAY);
            d.fillCircle(185 + 400 * j, 395 + 30 * j, 35);
        }
    }

    /**
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {

    }
}
