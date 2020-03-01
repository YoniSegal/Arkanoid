package gameobjects;

import gamelogic.*;
import geometry.Line;
import geometry.Point;
import levels.GameLevel;

import java.awt.Color;

/**
 * Creates a ball with a centre point, radius, velocity, colour, and movement
 * bounds.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Ball implements Visitable {
    //Declare object parameters.
    private Point centre;
    private int r;
    private Velocity velocity;
    private Color colour;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor assigns centre point, radius, velocity and colour.
     *
     * @param centre   ball's centre point.
     * @param r        ball's radius.
     * @param velocity ball's velocity.
     * @param colour   ball's colour.
     */
    public Ball(Point centre, int r, Velocity velocity, Color colour) {
        //Assign ball's centre point, radius, colour and velocity.
        this.centre = centre;
        this.r = r;
        this.colour = colour;
        this.velocity = velocity;
    }

    /**
     * Constructor assigns x-coordinate and y-coordinate of centre point,
     * ball's radius and colour.
     *
     * @param x      x-coordinate of centre point.
     * @param y      y-coordinate of centre point.
     * @param r      ball's radius.
     * @param colour ball's colour.
     */
    public Ball(int x, int y, int r, Color colour) {
        //Assign ball's centre point, radius and colour.
        this.centre = new Point(x, y);
        this.r = r;
        this.colour = colour;
    }

    /**
     * Method removes a sprite form a game.
     *
     * @param g Game level.
     */
    public void removeFromGame(GameLevel g) {
        g.removeVisitable(this);
    }

    /**
     * Method adds gameobjects.Sprite to gameLevel.
     *
     * @param gameLevel GameLogic.GameLevel being played.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addVisitable(this);
    }

    /**
     * Method returns ball's radius.
     *
     * @return int r - radius.
     */
    public int getR() {
        return r;
    }

    /**
     * Method returns ball's centre.
     *
     * @return geometry.Point - centre.
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Method sets balls velocity.
     *
     * @param v ball's velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Method assigns ball's velocity.
     *
     * @return velocity (gameobjects.Ball's velocity).
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Methdd sets the game environment.
     *
     * @param environment GameLogic.GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Method checks if ball will step outside its boundaries in next move. If
     * so, it changes it's direction before moving the ball according to its
     * velocity.
     *
     * @param dt change in time.
     */
    public void moveOneStep(double dt) {
        //Declare temporary new position.
        Point newPos = this.getVelocity().applyToPoint(this.centre, dt);
        //Check if block was hit.
        boolean hitBlock = checkTrajectory(newPos, dt);
        if (!hitBlock) {
            //If block wasn't hit, assign new position.
            this.centre = newPos;
        }
    }

    /**
     * Method checks if a ball collides with anything and returns true if it does.
     *
     * @param newPos geometry.Point - ball's next position.
     * @param dt     change in time.
     * @return boolean - true if ball collides.
     */
    public boolean checkTrajectory(Point newPos, double dt) {
        Velocity speed = this.getVelocity();
        //Calculate trajectory of object.
        Line trajectory = new Line(this.getCentre(), newPos);
        //Get collision points.
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        //If ball collided.
        if (collisionInfo != null) {
            Collidable c = collisionInfo.collisionObject();
            //Set velocity accordingly.
            this.setVelocity(c.hit(this, collisionInfo.collisionPoint(), speed));
            return true;
        }
        return false;
    }

    /**
     * Method returns x-coordinate of centre point.
     *
     * @return int (x-coordinate of centre point).
     */
    public double getX() {
        return this.centre.getX();
    }

    /**
     * Method returns y-coordinate of centre point.
     *
     * @return int (y-coordinate of centre point).
     */
    public double getY() {
        return this.centre.getY();
    }

    /**
     * Method returns radius of a ball.
     *
     * @return int (radius of ball).
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Method returns colour of a ball.
     *
     * @return colour (ball's colour).
     */
    public Color getColor() {
        return this.colour;
    }

    /**
     * If time has passed, move ball accordingly.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
