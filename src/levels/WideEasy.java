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
 * Class creates the information for the "Wide Easy" level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class WideEasy extends Level {
    private static final int BLOCKS_TO_REMOVE = 15;
    private static final int NUM_OF_BALLS = 8;
    private static final int PADDLE_SPEED = 100;
    private static final int RECT_WIDTH = 79;
    private static final int RECT_HEIGHT = 40;
    private static final int MAX_WIDTH = 800 - 20;
    private static final int MAX_HEIGHT = 600;
    private static final int BORDER_GAP = 10;
    private static final double ERROR = 0.0001;
    private static final String LEVEL_NAME = "Wide Easy";
    private static final Visitable COLOUR_BACKGROUND = new ColourBackground(Color.WHITE);
    private static final int START_X = 25;
    private static final int START_Y = 80;
    private static final int ROW_HEIGHT = 25;


    public WideEasy() {
        super(PADDLE_SPEED, MAX_WIDTH - 200, LEVEL_NAME, COLOUR_BACKGROUND, START_X, START_Y, ROW_HEIGHT);
    }

    /**
     * Method returns the initial velocity of each ball. Note that
     * initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return new ArrayList<Velocity>() {{
            add(Velocity.fromAngleAndSpeed(-60, 500));
            add(Velocity.fromAngleAndSpeed(-45, 500));
            add(Velocity.fromAngleAndSpeed(-30, 500));
            add(Velocity.fromAngleAndSpeed(-15, 500));
            add(Velocity.fromAngleAndSpeed(15, 500));
            add(Velocity.fromAngleAndSpeed(30, 500));
            add(Velocity.fromAngleAndSpeed(45, 500));
            add(Velocity.fromAngleAndSpeed(60, 500));
        }};
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
        for (int i = 0; i < BLOCKS_TO_REMOVE; i++) {
            double x = BORDER_GAP + i * ((MAX_WIDTH / BLOCKS_TO_REMOVE) + ERROR);
            double y = MAX_HEIGHT / 3;
            Point point = new Point(x, y);
            Block block = new Block(point, MAX_WIDTH / BLOCKS_TO_REMOVE, RECT_HEIGHT / 2);
            Color c = Color.BLACK;
            if (i < 2) {
                c = Color.RED;
            } else if (i < 4) {
                c = Color.ORANGE;
            } else if (i < 6) {
                c = Color.YELLOW;
            } else if (i < 9) {
                c = Color.GREEN;
            } else if (i < 11) {
                c = Color.BLUE;
            } else if (i < 13) {
                c = Color.PINK;
            } else if (i < 15) {
                c = Color.CYAN;
            }
            block.setHits(1);
            Color finalC = c;
            block.setFill(new HashMap<Integer, Background>() {{
                put(1, new ColourBackground(finalC));
            }});
            block.setStroke(Color.BLACK);
            block.setColour(c);
            blocks.add(block);
        }
        return blocks;
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
