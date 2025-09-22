// 212831325 Aya Shmoish
package collections;
import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the environment in which the game takes place.
 * It keeps track of all collidable objects in the game.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * The Collections.GameEnvironment object constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to add
     */

    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * Removes the given collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Assumes an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of the moving object
     * @return the Collision.CollisionInfo of the closest collision, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestCollision = null;
        Collidable collisionObject = null;
        double minDistance = Double.MAX_VALUE;
        for (Collidable c : this.collidables) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (collisionPoint != null) {
                double distance = trajectory.start().distance(collisionPoint);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCollision = collisionPoint;
                    collisionObject = c;
                }
            }
        }
        if (closestCollision == null) {
            return null;
        }
        return new CollisionInfo(closestCollision, collisionObject);
    }
}
