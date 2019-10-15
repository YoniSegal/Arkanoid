package gamelogic;

import geometry.Point;

/**
 * GameLogic.Velocity class assigns the change in position on the `x` and the `y` axes.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Velocity {
    private double dx;
    private double dy;
    private double velocity;

    /**
     * Constructor assigns a change in the x and y axis and calculates velocity
     * as the change in the x axis divided by the change in the y axis.
     *
     * @param dx change in x axis.
     * @param dy change in y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        this.velocity = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Method takes a point with position (x,y) and return a new point with
     * position (x+dx, y+dy).
     *
     * @param p  given point.
     * @param dt change in time.
     * @return new point after change in x and y axis.
     */
    public Point applyToPoint(Point p, double dt) {
        Point newPoint = new Point(p.getX() + dx * dt, p.getY() + dy * dt);
        return newPoint;
    }

    /**
     * Method assigns velocity based on angle and velocity instead of change in
     * x and y axis.
     *
     * @param angle angle to which a ball moves.
     * @param speed velocity of ball.
     * @return velocity calculated through angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Method gets object's velocity.
     *
     * @return GameLogic.Velocity.
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * Method accesses change in horizontal velocity.
     *
     * @return (double dx) change in horizontal velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Method accesses change in vertical velocity.
     *
     * @return (double dx) change in vertical velocity.
     */
    public double getDy() {
        return this.dy;
    }
}