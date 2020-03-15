package gameobjects;

import levels.GameLevel;
import gamelogic.Velocity;
import gamelogic.Visitable;
import gamelogic.Visitor;
import geometry.Line;
import geometry.LineCompare;
import geometry.Point;
import geometry.Rectangle;
import biuoop.KeyboardSensor;

/**
 * Class implements gameobjects.Sprite and gameobjects.Collidable interfaces.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Paddle implements Visitable, Collidable {
    private Rectangle rectangle;
    private KeyboardSensor keyboard;
    private double distance;
    private static final int MAX_WIDTH = 800;

    /**
     * Constructor assigns Keyboard sensor, upper left point, width and height of paddle.
     *
     * @param keyboard  Keyboard sensor.
     * @param upperLeft upper left point of paddle.
     * @param width     width of paddle.
     * @param height    Height of paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Point upperLeft, double width, double height) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(upperLeft, width, height);
    }

    /**
     * Method determines how far the paddle can move.
     *
     * @param displacement double.
     */
    public void setDistance(double displacement) {
        this.distance = displacement;
    }

    /**
     * Method moves paddle left.
     *
     * @param dt change in time.
     */
    public void moveLeft(double dt) {
        Point upperLeft = this.rectangle.getUpperLeft();
        Point p = new Point(upperLeft.getX() - distance * dt, upperLeft.getY());
        //Check of paddle has reached edge of screen.
        if (p.getX() > 5) {
            //Move paddle.
            this.rectangle = new Rectangle(p, this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * Method moves paddle right.
     *
     * @param dt change in time.
     */
    public void moveRight(double dt) {
        Point upperLeft = this.rectangle.getUpperLeft();
        Point p = new Point(upperLeft.getX() + distance * dt, upperLeft.getY());
        //Check of paddle has reached edge of screen.
        if (p.getX() + this.rectangle.getWidth() < MAX_WIDTH) {
            //Move paddle.
            this.rectangle = new Rectangle(p, this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * Method checks if time has passed and if keys have been pressed.
     *
     * @param dt change in time.
     */
    @Override
    public void timePassed(double dt) {
        //Check if left key was pressed.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
        //Check if right key was pressed.
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
    }

    /**
     * Method returns collision rectangle.
     *
     * @return geometry.Rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * Method assigns new velocity if paddle is hit.
     *
     * @param collisionPoint  geometry.Point of collision.
     * @param currentVelocity Current velocity.
     * @param hitter          Ball.
     * @return new GameLogic.Velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = getNewVelocity(collisionPoint, currentVelocity);
        return newVelocity;
    }

    /**
     * Method divides block into 5 sections and changes a ball's velocity
     * depending on where it hits.
     *
     * @param collisionPoint  geometry.Point of collision.
     * @param currentVelocity Current velocity.
     * @return New GameLogic.Velocity.
     */
    public Velocity getNewVelocity(Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        Line line = this.rectangle.getTop();
        Line left = this.rectangle.getLeft();
        Line right = this.rectangle.getRight();
        double len = this.rectangle.getWidth();
        //Define length of segment.
        double oneSection = len / 5.0;
        //Calculate distance between collision point and edge of line.
        double hitPoint = Math.abs(collisionPoint.getX() - line.getStart().getX());
        //Divide to get section value.
        int fracHit = (int) (hitPoint / oneSection);
        LineCompare lineCompare = new LineCompare();
        //If edges are hit, rebound them.
        if (left.pointOnLine(collisionPoint) || right.pointOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }
        //Return new velocity angle according to section.
        return Velocity.fromAngleAndSpeed((300 + fracHit * 30) % 360, currentVelocity.getVelocity());
    }

    /**
     * Method adds collidable and sprite to game.
     *
     * @param g GameLogic.GameLevel.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addVisitable(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
