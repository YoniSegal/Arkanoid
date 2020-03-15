package levels;

import gamelogic.Velocity;
import gamelogic.Visitable;
import gameobjects.Background;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.ColourBackground;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class creates the information for the "Final Four" level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class FinalFour extends Level {
    private static final int NUM_OF_BALLS = 3;
    private static final int PADDLE_SPEED = 100;
    private static final int BALL_SPEED = 350;
    private static final int RECT_WIDTH = 79;
    private static final int RECT_HEIGHT = 40;
    private static final int MAX_WIDTH = 800 - 20;
    private static final int MAX_HEIGHT = 600;
    private static final double ERROR = 0.0001;
    private static final int START_X = 25;
    private static final int START_Y = 80;
    private static final int ROW_HEIGHT = 25;
    private static final String LEVEL_NAME = "Final Four";
    private static final Visitable COLOUR_BACKGROUND = new ColourBackground(Color.BLUE.brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter());

    public FinalFour() {
        super(PADDLE_SPEED, 2 * RECT_WIDTH, LEVEL_NAME, COLOUR_BACKGROUND, START_X, START_Y, ROW_HEIGHT);
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
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     *
     * @return List of blocks.
     */
    @Override
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
    private void setColor(Block block, int i) {
        block.setStroke(Color.BLACK);
        if (i < 1) {
            block.setFill(new HashMap<Integer, Background>() {{
                put(1, new ColourBackground(Color.RED));
                put(2, new ColourBackground(Color.GRAY));
            }});
        } else if (i < 2) {
            block.setFill(new HashMap<Integer, Background>() {{
                put(1, new ColourBackground(Color.RED));
            }});
        } else if (i < 3) {
            block.setFill(new HashMap<Integer, Background>() {{
                put(1, new ColourBackground(Color.YELLOW));
            }});
        } else if (i < 4) {
            block.setFill(new HashMap<Integer, Background>() {{
                put(1, new ColourBackground(Color.GREEN));
            }});
        } else if (i < 5) {
            block.setFill(new HashMap<Integer, Background>() {{
                put(1, new ColourBackground(Color.WHITE));
            }});
        } else if (i < 6) {
            block.setFill(new HashMap<Integer, Background>() {{
                put(1, new ColourBackground(Color.PINK));
            }});
        } else if (i < 7) {
            block.setFill(new HashMap<Integer, Background>() {{
                put(1, new ColourBackground(Color.CYAN));
            }});
        }
    }

    /**
     * Method returns a list of balls.
     *
     * @return list of balls.
     */
    @Override
    public List<Ball> balls() {
        List<Velocity> ballVelocities = initialBallVelocities();
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            Ball ball = new Ball(MAX_WIDTH / 2, MAX_HEIGHT - RECT_WIDTH, 5, Color.WHITE);
            ball.setVelocity(ballVelocities.get(i));
            balls.add(ball);
        }
        return balls;

    }
}
