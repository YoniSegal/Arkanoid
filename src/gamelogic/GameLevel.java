package gamelogic;

import gameobjects.Background;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Collidable;
import gameobjects.CountdownAnimation;
import gameobjects.LevelIndicator;
import gameobjects.LivesIndicator;
import gameobjects.Paddle;
import gameobjects.PauseScreen;
import gameobjects.ScoreIndicator;
import gameobjects.Sprite;
import gameobjects.SpriteCollection;
import geometry.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class initialises and runs a level.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class GameLevel implements HitNotifier, Animation {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;
    private static final int RECT_HEIGHT = 40;
    private static final int BORDER_GAP = 10;
    private int numOfBalls;
    private int paddleWidth;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocksRemaining;
    private Counter ballsRemaining;
    private Counter score;
    private Counter lives;
    private Block deathRegion;
    private Block upperBlock;
    private Paddle paddle;
    private List<HitListener> hitListeners = new ArrayList();
    private AnimationRunner runner;
    private boolean running;
    private Ball[] balls;
    private LevelInformation levelInformation;
    private Color countDownColour;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;

    /**
     * Constructor assigns level information, a keyboard sensor, and anumation runner, a score counter
     * and a lives counter to the level.
     *
     * @param li    Level information.
     * @param ks    keyboard sensor.
     * @param ar    animation runner.
     * @param score score counter.
     * @param lives lives counter.
     */
    public GameLevel(LevelInformation li, KeyboardSensor ks, AnimationRunner ar, Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInformation = li;
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.lives = lives;
        this.score = score;
    }

    /**
     * Method assign the number of blocks remaining in a level.
     *
     * @param blocks Counter.
     */
    public void setBlocksRemaining(Counter blocks) {
        this.blocksRemaining = blocks;
    }

    /**
     * Method returns the number of blocks remaining in a level.
     *
     * @return Counter.
     */
    public Counter getBlocksRemaining() {
        return blocksRemaining;
    }

    /**
     * Method returns the number of balls remaining in a game.
     *
     * @return Counter.
     */
    public Counter getBallsRemaining() {
        return ballsRemaining;
    }

    /**
     * Method assigns the number of balls remaining in a level.
     *
     * @param numBalls Counter.
     */
    public void setBallsRemaining(Counter numBalls) {
        this.ballsRemaining = numBalls;
    }

    /**
     * Constructor assigns GUI to game.
     *
     * @param gui GUI used.
     */
    public GameLevel(GUI gui) {
        this.gui = gui;
    }

    /**
     * Method adds a collidable.
     *
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Method removes a collidable from the game.
     *
     * @param c Collidable.
     */
    public void removeCollidable(Collidable c) {
        List<Collidable> collidables = this.environment.getCollidables();
        for (Collidable collidable : collidables) {
            collidables.remove(c);
            return;
        }
    }

    /**
     * Method returns the block which removes balls from the game.
     *
     * @return Block.
     */
    public Block getDeathRegion() {
        return deathRegion;
    }

    /**
     * Method sets the score of a given level.
     *
     * @param points Counter.
     */
    public void setScore(Counter points) {
        this.score = points;
    }

    /**
     * Method sets the lives left in a given level.
     *
     * @param l Counter.
     */
    public void setLives(Counter l) {
        this.lives = l;
    }

    /**
     * Method returns the lives left in a given level.
     *
     * @return Counter.
     */
    public Counter getLives() {
        return lives;
    }

    /**
     * Method adds a gameobjects.Sprite.
     *
     * @param s gameobjects.Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Method removes sprite from a game.
     *
     * @param s Sprite.
     */
    public void removeSprite(Sprite s) {
        for (Sprite i : this.sprites.getSprites()) {
            this.sprites.getSprites().remove(s);
            return;
        }
    }

    /**
     * Method returns a SpriteCollection.
     *
     * @return SpriteColleciton.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl GameLogic.HitListener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl GameLogic.HitListener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Initialize a new game: create the Blocks and gameobjects.Ball (and gameobjects.Paddle) and add them to the game.
     */
    public void initialize() {
        //Set countdown colour.
        this.countDownColour = levelInformation.countDownColour();
        //Set background.
        addBackground((Background) levelInformation.getBackground());
        //Set animation runner.
        this.runner = animationRunner;
        //Set number of balls.
        this.numOfBalls = levelInformation.numberOfBalls();
        //Create GUI.
        this.gui = this.runner.getGui();
        //Create colour selection.
        this.blocksRemaining = new Counter(levelInformation.numberOfBlocksToRemove());
        //Set score.
        HitListener scoreTrackingListener = new ScoreTrackingListener(this.score, this);
        //Add blocks to game.
        for (Block s : levelInformation.blocks()) {
            BlockRemover blockRemover = new BlockRemover(this, s.getHits());
            s.addHitListener(blockRemover);
            s.addHitListener(scoreTrackingListener);
            s.addToGame(this);
        }
        setBlocksRemaining(new Counter(levelInformation.numberOfBlocksToRemove()));
        //Create walls.
        List<Block> walls = createWalls();
        //Assign top and bottom blocks.
        this.upperBlock = new Block(new Point(0, 0), MAX_WIDTH, 3 * BORDER_GAP);
        this.deathRegion = createWalls().get(1);
        //Add score indicator.
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, this.upperBlock);
        scoreIndicator.addToGame(this);
        //Add lives indicator.
        LivesIndicator livesIndicator = new LivesIndicator(this.lives, this.upperBlock);
        livesIndicator.addToGame(this);
        //Add level indicator.
        LevelIndicator levelIndicator = new LevelIndicator(this.lives, this.upperBlock, levelInformation);
        levelIndicator.addToGame(this);
        //Add walls to game.
        for (int j = 0; j < walls.size(); j++) {
            walls.get(j).addToGame(this);
        }
    }

    /**
     * Method determines the stopping condition.
     *
     * @return boolean.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Method assigns stopping condition for animation.
     *
     * @param stop - boolean.
     */
    public void setStop(boolean stop) {

    }

    /**
     * Method determines the drawing logic.
     *
     * @param d  DrawSurface.
     * @param dt change in time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        if (this.blocksRemaining.getValue() == 0) {
            removePaddleAndBalls();
            this.running = false;
        }
        if (this.ballsRemaining.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
            removePaddleAndBalls();
        }
        if (this.lives.getValue() == 0) {
            this.running = false;
        }
        if (this.gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(new PauseScreen(this.gui.getKeyboardSensor()));
        }
        sprites.notifyAllTimePassed(dt);
    }


    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        newBallsAndPaddle();
        if (this.lives.getValue() != 0) {
            this.runner.run(new CountdownAnimation(2, 4, sprites, this.countDownColour));
            this.running = true;
        }
        if (this.lives.getValue() == 0) {
            this.running = false;
        }
        this.runner.run(this);
    }

    /**
     * Method removes the paddle and balls from the game.
     */
    public void removePaddleAndBalls() {
        removeSprite(this.paddle);
        removeCollidable(this.paddle);
        for (int i = 0; i < this.balls.length; i++) {
            removeSprite(this.balls[i]);
        }
    }

    /**
     * Method creates new balls and a new paddle.
     */
    public void newBallsAndPaddle() {
        //Create balls.
        createBall();
        for (int k = 0; k < numOfBalls; k++) {
            this.balls[k].addToGame(this);
        }
        setBallsRemaining(new Counter(numOfBalls));
        //Create gameobjects.Paddle
        this.paddle = createPaddle();
        this.paddle.addToGame(this);
    }

    /**
     * Method assigns a colourBackground to the level.
     *
     * @param colourBackground ColourBackground.
     */
    public void addBackground(Background colourBackground) {
        this.addSprite(colourBackground);
        this.addSprite((Sprite) levelInformation);
    }

    /**
     * Method creates games walls.
     *
     * @return List of walls.
     */
    public List<Block> createWalls() {
        List<Block> walls = new ArrayList<Block>();
        Point topBlock = new Point(0, 30);
        Point lowerBlock = new Point(BORDER_GAP, MAX_HEIGHT - BORDER_GAP);
        Point rightBlock = new Point(MAX_WIDTH - BORDER_GAP, 3 * BORDER_GAP);
        Point leftBlock = new Point(0, 3 * BORDER_GAP);
        walls.add(new Block(topBlock, MAX_WIDTH, 2 * BORDER_GAP));
        walls.add(new Block(lowerBlock, MAX_WIDTH, BORDER_GAP));
        walls.add(new Block(rightBlock, BORDER_GAP, MAX_HEIGHT));
        walls.add(new Block(leftBlock, BORDER_GAP, MAX_HEIGHT));
        for (int j = 0; j < walls.size(); j++) {
            walls.get(j).setHits(0);
            walls.get(j).setColour(Color.GRAY);
            if (j == 1) {
                walls.get(j).addHitListener(new BallRemover(new Counter(levelInformation.numberOfBalls()), this));
            }
        }
        return walls;
    }

    /**
     * Method creates game's balls.
     */
    public void createBall() {
        //Create new gameobjects.Ball.
        this.balls = new Ball[numOfBalls];
        for (int i = 0; i < this.numOfBalls; i++) {
            int x = (int) levelInformation.balls().get(i).getX();
            int y = (int) levelInformation.balls().get(i).getY();
            int r = levelInformation.balls().get(i).getR();
            Color color = levelInformation.balls().get(i).getColor();
            this.balls[i] = new Ball(x, y, r, color);
            Velocity v = levelInformation.initialBallVelocities().get(i);
            //Set ball's velocity.
            this.balls[i].setVelocity(v);
            this.balls[i].setGameEnvironment(environment);
        }
    }

    /**
     * Method creates paddle.
     *
     * @return gameobjects.Paddle.
     */
    public Paddle createPaddle() {
        //Assign keyboard sensor
        this.paddleWidth = levelInformation.paddleWidth();
        Point point = new Point((double) MAX_WIDTH / 2 - (paddleWidth / 2), MAX_HEIGHT - (RECT_HEIGHT / 2));
        //Create paddle.
        Paddle p = new Paddle(this.keyboardSensor, point, this.paddleWidth, RECT_HEIGHT / 4);
        p.setDistance(levelInformation.paddleSpeed());
        return p;
    }
}
