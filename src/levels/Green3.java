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
 * Class creates the information for the "Green 3" level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Green3 extends Level {
    private static final int BLOCKS_TO_REMOVE = 40;
    private static final int NUM_OF_BALLS = 2;
    //    private static final int NUM_OF_LIVES = 4;
    private static final int PADDLE_SPEED = 100;
    private static final int BALL_SPEED = 300;
    private static final int RECT_WIDTH = 50;
    private static final int RECT_HEIGHT = 25;
    //    private static final int BLOCK_LIFE = 1;
//    private Color color = Color.BLACK;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;
    private static final int BORDER_GAP = 10;
    private static final double ERROR = 0.0001;
    private static final int START_X = 25;
    private static final int START_Y = 80;
    private static final int ROW_HEIGHT = 25;
    private static final String LEVEL_NAME = "Green 3";
    private static final Visitable COLOUR_BACKGROUND = new ColourBackground(Color.GREEN.darker().darker());


    public Green3() {
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
        velocities.add(0, Velocity.fromAngleAndSpeed(45, BALL_SPEED));
        velocities.add(1, Velocity.fromAngleAndSpeed(-45, BALL_SPEED));
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
                put(1, new ColourBackground(Color.BLUE));
            }});
        } else if (i < 5) {
            block.setFill(new HashMap<Integer, Background>() {{
                put(1, new ColourBackground(Color.WHITE));
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
