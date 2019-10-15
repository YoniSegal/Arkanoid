package gameobjects;

import biuoop.DrawSurface;
import gamelogic.LevelInformation;
import gamelogic.Velocity;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

/**
 * Class a creates a general level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Level implements LevelInformation, Sprite {
    private int numOfBalls;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelname;
    private Background background;
    private List<Block> blocks;
    private int blockstoRemove;
    private List<Ball> balls;
    private Color color;
    private int rowHeight;
    private int startX;
    private int startY;
    private int levelNumber;

    /**
     * Blank constructor.
     */
    public Level() {

    }

    /**
     * Method returns the number of balls in a given level.
     *
     * @return int number of balls in the level.
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * Method returns the initial velocity of each ball. Note that
     * initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * Method returns the speed of the paddle.
     *
     * @return int - speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Method returns the width of a paddle.
     *
     * @return int - width of paddle.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Method returns the level name. It will be displayed at the top of the screen.
     *
     * @return String - level name.
     */
    public String levelName() {
        return this.levelname;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite - background.
     */
    public Sprite getBackground() {
        return (Sprite) this.background;
    }

    /**
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     *
     * @return List of blocks.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Number of levels that should be removed before the level is considered
     * to be "cleared". This number should be <= blocks.size();
     *
     * @return int - number of blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.blockstoRemove;
    }

    /**
     * Method returns the number of lives in a given level.
     *
     * @return int - number of lives.
     */
    public int numOfLives() {
        return 0;
    }

    /**
     * Method returns a list of balls.
     *
     * @return list of balls.
     */
    public List<Ball> balls() {
        return this.balls;
    }

    /**
     * Method returns colour for countdown.
     *
     * @return Colour.
     */
    public Color countDownColour() {
        return this.color;
    }

    /**
     * Method returns row height.
     *
     * @return int.
     */
    public int rowHeight() {
        return this.rowHeight;
    }

    /**
     * Method returns point of starting block.
     *
     * @return Point.
     */
    public Point startingBlock() {
        return new Point(startX, startY);
    }

    /**
     * Method sets the startX.
     *
     * @param x int.
     */
    public void setStartX(int x) {
        this.startX = x;
    }

    /**
     * Method set the startY.
     *
     * @param y int.
     */
    public void setStartY(int y) {
        this.startY = y;
    }

    /**
     * Methd sets row height.
     *
     * @param r int.
     */
    public void setRowHeight(int r) {
        this.rowHeight = r;
    }

    /**
     * Methd sets balls.
     *
     * @param b list.
     */
    public void setBalls(List<Ball> b) {
        this.balls = b;
    }

    /**
     * Method sets background.
     *
     * @param b background.
     */
    public void setBackground(Background b) {
        this.background = b;
    }

    /**
     * Methd sets ball Velocities.
     *
     * @param b b
     */
    public void setBallVelocities(List<Velocity> b) {
        this.ballVelocities = b;
    }

    /**
     * Method sets blocks.
     *
     * @param b list.
     */
    public void setBlocks(List<Block> b) {
        this.blocks = b;
    }

    /**
     * Method sets blocks to be read.
     *
     * @param b int.
     */
    public void setBlockstoRemove(int b) {
        this.blockstoRemove = b;
    }

    /**
     * Method sets colour.
     *
     * @param c color.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Method sets level name.
     *
     * @param l String.
     */
    public void setLevelname(String l) {
        this.levelname = l;
    }

    /**
     * Method set the number of balls.
     *
     * @param num int.
     */
    public void setNumOfBalls(int num) {
        this.numOfBalls = num;
    }

    /**
     * Method sets the paddle speed.
     *
     * @param speed int.
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * Method sets the paddle width.
     *
     * @param width int.
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * Method sets the level number.
     *
     * @param level int.
     */
    public void setLevelNumber(int level) {
        this.levelNumber = level;
    }

    /**
     * Method gets the start x.
     *
     * @return int.
     */
    public int getStartX() {
        return startX;
    }

    /**
     * Method returns the start y.
     *
     * @return int.
     */
    public int getStartY() {
        return startY;
    }

    /**
     * Method returns a list of velocities.
     *
     * @return List.
     */
    public List<Velocity> getBallVelocities() {
        return ballVelocities;
    }

    /**
     * MEthod returns a list of balls.
     *
     * @return List.
     */
    public List<Ball> getBalls() {
        return balls;
    }

    /**
     * Method draws the sprite to the screen.
     *
     * @param d drawSrurface.
     */
    public void drawOn(DrawSurface d) {

    }

    /**
     * Method notifies the sprite that time has passed.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {

    }
}
