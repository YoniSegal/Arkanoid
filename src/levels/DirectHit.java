package levels;

import gamelogic.Velocity;
import gamelogic.Visitable;
import gamelogic.Visitor;
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
 * Class creates the information for the "Direct Hit" level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class DirectHit extends Level {
    private static final int PADDLE_SPEED = 650;
    private static final int BALL_SPEED = 500;
    private static final int RECT_WIDTH = 160;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;
    private static final int START_X = 25;
    private static final int START_Y = 80;
    private static final int ROW_HEIGHT = 25;
    private static final int NUM_BALLS = 1;
    private static final String LEVEL_NAME = "Direct Hit";
    private static final Visitable COLOUR_BACKGROUND = new ColourBackground(Color.BLACK);

    public DirectHit() {
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
        for (int i = 0; i < NUM_BALLS; i++) {
            velocities.add(i, Velocity.fromAngleAndSpeed(0, BALL_SPEED));
        }
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
        Point p = new Point(MAX_WIDTH / 2 - (RECT_WIDTH / 2), (MAX_HEIGHT / 3) + 10);
        Block block = new Block(p, RECT_WIDTH, 25);
        block.setFill(new HashMap<Integer, Background>() {{
            put(1, new ColourBackground(Color.RED));
        }});
        block.setStroke(Color.BLACK);
        block.setHits(1);
        blocks.add(block);
        return blocks;
    }

    /**
     * Method returns a list of balls.
     *
     * @return list of balls.
     */
    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        Ball ball = new Ball(MAX_WIDTH / 2, MAX_HEIGHT - RECT_WIDTH, 5, Color.WHITE);
        ball.setVelocity(initialBallVelocities().get(0));
        balls.add(ball);
        return balls;
    }
}
