package gameobjects;

import gamelogic.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * Interface is used by objects which can be collided with.
 *
 * @author Yonatan Segal
 * @version 1
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return nothing.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the
     * object inflicted on us).
     *
     * @param hitter          Ball.
     * @param collisionPoint  Point.
     * @param currentVelocity Velocity.
     * @return Velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
