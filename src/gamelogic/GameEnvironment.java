package gamelogic;

import gameobjects.Collidable;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Class hold a list of collidables.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor assigns an array list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList();
    }

    /**
     * Method returns list of collidables.
     *
     * @return List fo collidables.
     */
    public List getCollidables() {
        return collidables;
    }

    /**
     * Method adds the given collidable to the environment.
     *
     * @param c gameobjects.Collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end(). If this object
     * will not collide with any of the collidables in this collection, return
     * null. Else, return the information about the closest collision that is
     * going to occur.
     *
     * @param trajectory Objects trajectory.
     * @return GameLogic.CollisionInfo - collidables.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        for (int i = 0; i < this.collidables.size(); i++) {
            Collidable collidable = collidables.get(i);
            Point collisionPoint;
            //Get closest intersection point.
            collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint == null) {
                continue;
            } else {
                //Fill collision information.
                CollisionInfo newCollisionInfo = new CollisionInfo(collisionPoint, collidable);
                return newCollisionInfo;
            }
        }
        return null;
    }
}
