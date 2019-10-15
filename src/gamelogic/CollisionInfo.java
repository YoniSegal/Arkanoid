package gamelogic;

import gameobjects.Collidable;
import geometry.Point;

/**
 * Class holds point of collision and collidables.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Constructor assigns collision point and collidable to object.
     *
     * @param collisionPoint geometry.Point of collision.
     * @param collidable     collidable object,
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collidable = collidable;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Method returns point of collision.
     *
     * @return collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Method returns the collidable object involved in the collision.
     *
     * @return collidable.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}
