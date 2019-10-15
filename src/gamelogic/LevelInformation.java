package gamelogic;

import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * The GameLogic.HitNotifier interface indicate that objects that implement it send
 * notifications when they are being hit.
 *
 * @author Yonatan Segal
 * @version 1
 */
public interface LevelInformation {
    /**
     * Method returns the number of balls in a given level.
     *
     * @return int number of balls in the level.
     */
    int numberOfBalls();

    /**
     * Method returns the initial velocity of each ball. Note that
     * initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Method returns the speed of the paddle.
     *
     * @return int - speed.
     */
    int paddleSpeed();

    /**
     * Method returns the width of a paddle.
     *
     * @return int - width of paddle.
     */
    int paddleWidth();

    /**
     * Method returns the level name. It will be displayed at the top of the screen.
     *
     * @return String - level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite - background.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     *
     * @return List of blocks.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed before the level is considered
     * to be "cleared". This number should be <= blocks.size();
     *
     * @return int - number of blocks to remove.
     */
    int numberOfBlocksToRemove();

    /**
     * Method returns the number of lives in a given level.
     *
     * @return int - number of lives.
     */
    int numOfLives();

    /**
     * Method returns a list of balls.
     *
     * @return list of balls.
     */
    List<Ball> balls();

    /**
     * Method returns colour for countdown.
     *
     * @return Colour.
     */
    Color countDownColour();
}
